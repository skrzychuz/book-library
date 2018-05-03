package book.collections.homework.service;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.mapped.model.IndustryIdentifiers;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookAdapter {


  private  DateAdapter dateAdapter;
  private  BookBuilder bookBuilder;

  public BookAdapter(DateAdapter dateAdapter, BookBuilder bookBuilder) {
    this.dateAdapter = dateAdapter;
    this.bookBuilder = bookBuilder;
  }

  public Book convertItemToBook(Item item) {

    return bookBuilder
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
      if (identifier.getType().equals("ISBN_13")) {
        isbn = identifier.getIdentifier();
      } else {
        isbn = item.getId();
      }
    }
    return isbn;
  }
}
