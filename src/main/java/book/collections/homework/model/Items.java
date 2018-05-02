package book.collections.homework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items implements Serializable {

  private String id;
  private String title;
//  private String subtitle;
//  private String publisher;
//  private long publishedDate;
//  private String description;
//  private int pageCount;
//  private String thumbnailUrl;
//  private String language;
//  private String previewLink;
//  private double averageRating;
//  private List<String> authorsSet;
//  private List<String> categories;


  public Items() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
//  public String getSubtitle() {
//    return subtitle;
//  }
//
//  public String getPublisher() {
//    return publisher;
//  }
//
//  public long getPublishedDate() {
//    return publishedDate;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public int getPageCount() {
//    return pageCount;
//  }
//
//  public String getThumbnailUrl() {
//    return thumbnailUrl;
//  }
//
//  public String getLanguage() {
//    return language;
//  }
//
//  public String getPreviewLink() {
//    return previewLink;
//  }
//
//  public double getAverageRating() {
//    return averageRating;
//  }
//
//  public List<String> getAuthors() {
//    return authorsSet;
//  }
//
//  public List<String> getCategories() {
//    return categories;
//  }
}
