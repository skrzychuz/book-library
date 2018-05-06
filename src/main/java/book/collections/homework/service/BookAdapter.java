package book.collections.homework.service;

import static book.collections.homework.configuration.VariableConfig.ISBN_TYPE;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.mapped.model.IndustryIdentifiers;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.Book;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class BookAdapter {

  private DateAdapter dateAdapter;


  public BookAdapter(DateAdapter dateAdapter) {
    this.dateAdapter = dateAdapter;
  }

  public Book convertItemToBook(Item item) throws ParseException {

    return new BookBuilder()
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
  }

  public String getBookIsbn(Item item) {

    String isbn = "";

    for (IndustryIdentifiers identifier : item.getVolumeInfo().getIndustryIdentifiers()) {
      isbn = identifier.getType().equals(ISBN_TYPE) ? identifier.getIdentifier()
          : item.getId();
    }
    return isbn;
  }

  public boolean isbnNumerMatches(Item item, IndustryIdentifiers isbnType, String isbn) {
    return ((isbnType.getType().equals(ISBN_TYPE) && isbnType.getIdentifier()
        .equals(isbn)) || item
        .getId().equals(isbn));
  }

}
