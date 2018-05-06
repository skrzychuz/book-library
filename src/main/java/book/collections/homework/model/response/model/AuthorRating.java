package book.collections.homework.model.response.model;

public class AuthorRating implements Comparable<AuthorRating> {

  private String author;
  private double averageRating;

  public AuthorRating(String author, double averageRating) {
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
  public int compareTo(AuthorRating a1) {
    return Double.compare(a1.averageRating, this.averageRating);
  }
}
