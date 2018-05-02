package book.collections.homework.model.response.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_DEFAULT)
public class Book {

  private String isbn;
  private String title;
  private String subtitle;
  private String publisher;
  private long publishedDate;
  private String description;
  private int pageCount;
  private String thumbnailUrl;
  private String language;
  private String previewLink;
  private double averageRating;
  private List<String> authors;
  private List<String> categories;

  public Book() {
  }

  public Book(String isbn, String title, String subtitle, String publisher, long publishedDate,
      String description, int pageCount, String thumbnailUrl, String language,
      String previewLink, double averageRating, List<String> authors,
      List<String> categories) {
    this.isbn = isbn;
    this.title = title;
    this.subtitle = subtitle;
    this.publisher = publisher;
    this.publishedDate = publishedDate;
    this.description = description;
    this.pageCount = pageCount;
    this.thumbnailUrl = thumbnailUrl;
    this.language = language;
    this.previewLink = previewLink;
    this.averageRating = averageRating;
    this.authors = authors;
    this.categories = categories;
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getPublisher() {
    return publisher;
  }

  public long getPublishedDate() {
    return publishedDate;
  }

  public String getDescription() {
    return description;
  }

  public int getPageCount() {
    return pageCount;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public String getLanguage() {
    return language;
  }

  public String getPreviewLink() {
    return previewLink;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public List<String> getCategories() {
    return categories;
  }
}
