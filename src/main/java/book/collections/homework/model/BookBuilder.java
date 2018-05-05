package book.collections.homework.model;

import book.collections.homework.model.response.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookBuilder {

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

  public BookBuilder withIsbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  public BookBuilder withTitle(String title) {
    this.title = title;
    return this;
  }

  public BookBuilder withSubtitle(String subtitle) {
    this.subtitle = subtitle;
    return this;
  }

  public BookBuilder withPublisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  public BookBuilder withPublishedDate(long publishedDate) {
    this.publishedDate = publishedDate;
    return this;
  }

  public BookBuilder withDescription(String description) {
    this.description = description;
    return this;
  }

  public BookBuilder withPageCount(int pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  public BookBuilder withThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  public BookBuilder withLanguage(String language) {
    this.language = language;
    return this;
  }

  public BookBuilder withPreviewLink(String previewLink) {
    this.previewLink = previewLink;
    return this;
  }

  public BookBuilder withAverageRating(double averageRating) {
    this.averageRating = averageRating;
    return this;
  }

  public BookBuilder withAuthors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public BookBuilder withCategories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public Book build() {
    return new Book(isbn, title, subtitle, publisher, publishedDate, description, pageCount,
        thumbnailUrl, language, previewLink, averageRating, authors, categories);
  }
}
