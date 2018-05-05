package book.collections.homework.service;

import book.collections.homework.configuration.VariableConfig;
import book.collections.homework.model.mapped.model.IndustryIdentifiers;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookLibraryService  {


  private BookAdapter bookAdapter;
  private BookLibraryAdapter library;

  public BookLibraryService(
      BookAdapter bookAdapter,
      BookLibraryAdapter library) {
    this.bookAdapter = bookAdapter;
    this.library = library;

  }

  public Book getBookByIsbn(String isbn) throws IOException {

    for (Item item : library.getBookLibrary().getItems()) {
      for (IndustryIdentifiers type : item.getVolumeInfo().getIndustryIdentifiers()) {
        if ((type.getType().equals(VariableConfig.ISBN_TYPE) && type.getIdentifier().equals(isbn)) || item
            .getId().equals(isbn)) {
          return bookAdapter.convertItemToBook(item);
        }
      }
    }
    return null;
  }

  public List<Book> getBookListByCategory(String givenCategory) throws IOException {

    List<Book> bookByCategory = new ArrayList<>();

    for (Item item : library.getBookLibrary().getItems()) {
      if ((item.getVolumeInfo().getCategories() != null)) {
        for (String category : item.getVolumeInfo().getCategories()) {
          if (category.equals(givenCategory)) {
            bookByCategory.add(bookAdapter.convertItemToBook(item));
          }
        }
      }
    }
    return bookByCategory;
  }


  public List<AuthorRating> getAuthorsRatings() throws IOException {

    List<AuthorRating> authorRating = new ArrayList<>();

    for (String uniqAuthor : library.getAuthorsList()) {
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
//            .add(new AuthorRating(uniqAuthor, Math.round((total / counter) * 10d) / 10.0d));
                    .add(new AuthorRating(uniqAuthor, DoubleRounder.round(total / counter,1) ));

      } else {
        authorRating
            .add(new AuthorRating(uniqAuthor, 0.0));
      }
    }
    authorRating.sort((o1, o2) -> (int) (o2.getAverageRating() - o1.getAverageRating()));
    return authorRating;
  }
}

