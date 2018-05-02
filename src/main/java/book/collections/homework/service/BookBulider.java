package book.collections.homework.service;

import book.collections.homework.model.Book;

import java.util.List;

public class BookBulider {

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


  public BookBulider withIsbn(String isbn) {
    this.isbn = isbn;
    return this;
  }

  public BookBulider withTitle(String title) {
    this.title = title;
    return this;
  }

  public BookBulider withSubtitle(String subtitle) {
    this.subtitle = subtitle;
    return this;
  }

  public BookBulider withPublisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  public BookBulider withPublishedDate(long publishedDate) {
    this.publishedDate = publishedDate;
    return this;
  }

  public BookBulider withDescription(String description) {
    this.description = description;
    return this;
  }

  public BookBulider withPageCount(int pageCount) {
    this.pageCount = pageCount;
    return this;
  }

  public BookBulider withThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  public BookBulider withLanguage(String language) {
    this.language = language;
    return this;
  }

  public BookBulider withPreviewLink(String previewLink) {
    this.previewLink = previewLink;
    return this;
  }

  public BookBulider withAverageRating(double averageRating) {
    this.averageRating = averageRating;
    return this;
  }

  public BookBulider withAuthors(List<String> authors) {
    this.authors = authors;
    return this;
  }

  public BookBulider withCategories(List<String> categories) {
    this.categories = categories;
    return this;
  }

  public Book build() {
    return new Book(isbn, title, subtitle, publisher, publishedDate, description, pageCount,
        thumbnailUrl, language, previewLink, averageRating, authors, categories);
  }

}
