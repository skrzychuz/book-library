package book.collections.homework.service;

import book.collections.homework.configuration.RepositoryNotFoundException;
import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import book.collections.homework.model.mapped.model.IndustryIdentifiers;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.repository.BookLibraryRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class BookLibraryService implements BookLibraryRepo {

  @Value("${industryidentifiers.type}")
  private
  String isbnType;

  private BookAdapter bookAdapter;

  private BookLibraryAdapter library;

  public BookLibraryService(BookAdapter bookAdapter,
      BookLibraryAdapter library) {
    this.bookAdapter = bookAdapter;
    this.library = library;
  }

  @Override
  public Book getBookByIsbn(String isbn) {

    for (Item item : library.getBookLibrary().getItems()) {
      for (IndustryIdentifiers isbnId : item.getVolumeInfo().getIndustryIdentifiers()) {
        if ((isbnId.getType().equals(isbnType) && isbnId.getIdentifier().equals(isbn)) || item
            .getId().equals(isbn)) {
          return bookAdapter.convertItemToBook(item);
        }
      }
    }
    throw new RepositoryNotFoundException();
  }

  @Override
  public List<Book> getBookListByCategory(String category) {

    List<Book> bookByCategory = new ArrayList<>();

    for (Item item : library.getBookLibrary().getItems()) {
      if ((item.getVolumeInfo().getCategories() != null)) {
        for (String cat : item.getVolumeInfo().getCategories()) {
          if (cat.equals(category)) {
            bookByCategory.add(bookAdapter.convertItemToBook(item));
          }
        }
      }
    }
    return bookByCategory;
  }

  @Override
  public List<AuthorRating> getAuthorListOrderOfRating() {

    List<AuthorRating> authorRating = new ArrayList<>();

    for (String uniqAuthor : getAuthorsList()) {
      double total = 0.0;
      int counter = 0;
      for (Item item : library.getBookLibrary().getItems()) {
        if (item.getVolumeInfo().getAuthors() != null) {
          for (String author : item.getVolumeInfo().getAuthors()) {
            if (uniqAuthor.equals(author)) {
              if (item.getVolumeInfo().getAverageRating() != 0) {
                total += item.getVolumeInfo().getAverageRating();
                counter++;
              }
            }
          }
        }
      }
      if (counter != 0) {
        authorRating
            .add(new AuthorRating(uniqAuthor, Math.round((total / counter) * 100d) / 100.0d));
      } else {
        authorRating
            .add(new AuthorRating(uniqAuthor, 0.0));
      }
    }
    authorRating.sort((o1, o2) -> (int) (o2.getAverageRating() - o1.getAverageRating()));
    return authorRating;
  }


  private Set<String> getAuthorsList() {

    Set<String> authorsList = new TreeSet<>();

    for (Item item : library.getBookLibrary().getItems()) {
      if (item.getVolumeInfo().getAuthors() != null) {
        authorsList.addAll(item.getVolumeInfo().getAuthors());
      }
    }
    return authorsList;
  }
}

