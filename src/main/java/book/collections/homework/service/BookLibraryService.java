package book.collections.homework.service;

import book.collections.homework.model.BookLibraryBuilder;
import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import book.collections.homework.model.mapped.model.BookLibrary;
import book.collections.homework.model.mapped.model.IndustryIdentifiers;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.repository.BookLibraryRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Service
public class BookLibraryService implements BookLibraryRepo {

  @Autowired
  BookAdapter bookAdapter;
  @Autowired
  BookLibraryBuilder library;

  @Override
  public Book getBookByIsbn(String isbn) {
    library.getAllBooks();

//    BookLibrary bookLibrary = getAll();

    for (Item item : library.getAllBooks().getItems()) {
      for (IndustryIdentifiers isbnId : item.getVolumeInfo().getIndustryIdentifiers()) {
        if ((isbnId.getType().equals("ISBN_13") && isbnId.getIdentifier().equals(isbn)) || item
            .getId().equals(isbn)) {
          return bookAdapter.convertItemToBook(item);
        }
      }
    }
    return null;
  }


  @Override
  public List<Book> getBookListByCategory(String category) {

    List<Book> bookByCategory = new ArrayList<>();

//    BookLibrary bookLibrary = getAll();

    for (Item item : library.getAllBooks().getItems()) {
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

    Set<String> authorsList = authorsSet();
//    BookLibrary bookLibrary = getAll();
    List<AuthorRating> authorRating = new ArrayList<>();

    for (String uniqAuthor : authorsList) {
      double total = 0.0;
      int counter = 0;
      for (Item item : library.getAllBooks().getItems()) {
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


  public BookLibrary getAll() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    BookLibrary bookLibrary = null;
    try {
      bookLibrary = mapper
          .readValue(new File("src\\main\\resources\\books.json"), BookLibrary.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bookLibrary;
  }








  public Set<String> authorsSet() {

    BookLibrary bookLibrary = getAll();
    Set<String> authorsList = new TreeSet<>();

    for (Item item : bookLibrary.getItems()) {
      if (item.getVolumeInfo().getAuthors() != null) {
        authorsList.addAll(item.getVolumeInfo().getAuthors());
      }
    }
    return authorsList;
  }

}

