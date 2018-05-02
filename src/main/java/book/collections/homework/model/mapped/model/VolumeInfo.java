package book.collections.homework.model.mapped.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_DEFAULT)
public class VolumeInfo {

  private String title;
  private String subtitle;
  private String publisher;
  private String publishedDate;
  private String description;
  private List<IndustryIdentifiers> industryIdentifiers;
  private int pageCount;
  private ImageLinks imageLinks;
  private String language;
  private String previewLink;
  private double averageRating;
  private List<String> authors;
  private List<String> categories;


  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getPublisher() {
    return publisher;
  }

  public String getPublishedDate() {
    return publishedDate;
  }

  public String getDescription() {
    return description;
  }

  public List<IndustryIdentifiers> getIndustryIdentifiers() {
    return industryIdentifiers;
  }

  public int getPageCount() {
    return pageCount;
  }

  public ImageLinks getImageLinks() {
    return imageLinks;
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
