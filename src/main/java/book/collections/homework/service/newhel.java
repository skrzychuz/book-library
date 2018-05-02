package book.collections.homework.service;

import book.collections.homework.model.AuthorRating;
import book.collections.homework.model.Book;
import book.collections.homework.model.BookLibrary;
import book.collections.homework.model.IndustryIdentifiers;
import book.collections.homework.model.Item;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;


@Service
public class newhel {


  public Book GETISBN(String isbn) {

    Book book = new Book();
    BookLibraryImp bookLibraryImp = new BookLibraryImp();
    BookLibrary bookLibrary = bookLibraryImp.getAll();

    for (Item item : bookLibrary.getItems()) {
      for (IndustryIdentifiers isbnId : item.getVolumeInfo().getIndustryIdentifiers()) {
        if ((isbnId.getType().equals("ISBN_13") && isbnId.getIdentifier().equals(isbn)) || item
            .getId().equals(isbn)) {
          return bookadapter(item);
        }

      }
    }
    return null;
  }

  public Book bookadapter(Item item) {

    DateAdapter dateAdapter = new DateAdapter();
    BookBulider bookBulider = new BookBulider();

    dateAdapter.stringToTimestampAdapter(item.getVolumeInfo().getPublishedDate());

    Book book = bookBulider
        .withIsbn(getBookIsbn(item))
        .withTitle(item.getVolumeInfo().getTitle())
        .withSubtitle(item.getVolumeInfo().getSubtitle())
        .withPublisher(item.getVolumeInfo().getPublisher())
        .withPublishedDate(
            dateAdapter.stringToTimestampAdapter(item.getVolumeInfo().getPublishedDate()))
        .withDescription(item.getVolumeInfo().getDescription())
        .withPageCount(item.getVolumeInfo().getPageCount())
        .withThumbnailUrl(item.getVolumeInfo().getImageLinks().getThumbnail())
        .withLanguage(item.getVolumeInfo().getLanguage())
        .withPreviewLink(item.getVolumeInfo().getPreviewLink())
        .withAverageRating(item.getVolumeInfo().getAverageRating())
        .withAuthors(item.getVolumeInfo().getAuthors())
        .withCategories(item.getVolumeInfo().getCategories())
        .build();

    return book;

  }


  public String getBookIsbn(Item item) {

    String isbn = "";

    for (IndustryIdentifiers isbnId : item.getVolumeInfo().getIndustryIdentifiers()) {
      if (isbnId.getType().equals("ISBN_13")) {
        isbn = isbnId.getIdentifier();
      } else {
        isbn = item.getId();
      }
    }
    return isbn;
  }


  public List<Book> getListBookbyCategory(String category) {
    List<Book> bookByCategory = new ArrayList<>();
    BookLibraryImp bookLibraryImp = new BookLibraryImp();
    BookLibrary bookLibrary = bookLibraryImp.getAll();

    for (Item item : bookLibrary.getItems()) {
      if ((item.getVolumeInfo().getCategories() != null)) {
        for (String cat : item.getVolumeInfo().getCategories()) {
          if (cat.equals(category)) {
            bookByCategory.add(bookadapter(item));
          }
        }
      }
    }

    return bookByCategory;
  }

  public Set<String> authorsSet() {

    BookLibraryImp bookLibraryImp = new BookLibraryImp();
    BookLibrary bookLibrary = bookLibraryImp.getAll();
    Set<String> authorsList = new TreeSet<>();

    for (Item item : bookLibrary.getItems()) {
      if (item.getVolumeInfo().getAuthors() != null) {
        authorsList.addAll(item.getVolumeInfo().getAuthors());
      }
    }
    return authorsList;
  }

  public List<AuthorRating> getAverageAuthorsRatin() {

    DecimalFormat df = new DecimalFormat("#.##");
    df.format(0.912385);


    BookLibraryImp bookLibraryImp = new BookLibraryImp();
    Set<String> authorsList = authorsSet();
    BookLibrary bookLibrary = bookLibraryImp.getAll();
    List<AuthorRating> authorRating = new ArrayList<>();

    for (String uniqAuthor : authorsList) {
      double total = 0.0;
      int counter = 0;
      for (Item item : bookLibrary.getItems()) {
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
}

