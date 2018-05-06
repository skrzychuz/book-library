package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import book.collections.homework.model.response.model.AuthorRating;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnitParamsRunner.class)
public class AuthorRatingTest extends AbstractClassTest {

  @Test
  @Parameters(method = "withNames")
  public void shouldReturnAuthorList(List<String> authorRatingList) throws Exception {
    //given
    List<String> actualName = new ArrayList<>();
    int expectedSize = 40;

    //when
    List<AuthorRating> actualList = bookLibraryService.getAuthorsRatings();
    for (AuthorRating authorRating : actualList) {
      actualName.add(authorRating.getAuthor());
    }

    //then
    assertNotNull(actualList);
    assertEquals(expectedSize, actualList.size());

    for (String expected : authorRatingList) {
      assertTrue("Error with " + expected, actualName.contains(expected));
    }
  }

  @Test
  @Parameters(method = "namesAndRating")
  public void shouldCheckRightAverageRating(String expectedName, double expectedAvgRating)
      throws IOException {

    List<AuthorRating> actualAuthorRatingList = bookLibraryService.getAuthorsRatings();
    for (AuthorRating authorRating : actualAuthorRatingList) {
      if (authorRating.getAuthor().equals(expectedName)) {
        assertEquals(expectedAvgRating, authorRating.getAverageRating(), 0);
      }
    }
  }

  @Test
  public void shouldCheckRightOrder() throws Exception {

    //when
    List<AuthorRating> actualList = bookLibraryService.getAuthorsRatings();

    //then
    for (int i = 0; i < actualList.size() - 1; i++) {
      assertTrue("Error in position nr " + i,
          checkOrderRating(actualList.get(i).getAverageRating(),
              actualList.get(i + 1).getAverageRating()));
    }
  }

  public boolean checkOrderRating(double rating1, double rating2) {
    return Double.compare(rating1, rating2) >= 0;
  }

  private Object[] withNames() {
    return new Object[]{
        new Object[]{Arrays.asList("Gary Cornell", "Cay S. Horstmann", "James William Bayley Money",
            "Bruce Eckel", "Sundar Narasimhan", "Patrick Henry Winston", "J.F. Scheltema", "Geddy",
            "Bruce", "Jimmi")}};
  }

  private Object[] namesAndRating() {

    return new Object[]{
        new Object[]{"Geddy", 4.0},
        new Object[]{"Bruce", 1.0},
        new Object[]{"Jimmi", 4.3},
    };
  }
}