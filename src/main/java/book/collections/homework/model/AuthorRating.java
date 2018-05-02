package book.collections.homework.model;

import java.util.Comparator;

public class AuthorRating implements Comparator<AuthorRating> {

  private String author;
  private double averageRating;

  public AuthorRating(String author, double averageRating)  {
    this.author = author;
    this.averageRating = averageRating;
  }

  public String getAuthor() {
    return author;
  }

  public double getAverageRating() {
    return averageRating;
  }


  @Override
  public int compare(AuthorRating a1, AuthorRating a2) {
    if(a1.getAverageRating() > a2.getAverageRating()){
      return 1;
    } else {
      return -1;
    }
  }
}
