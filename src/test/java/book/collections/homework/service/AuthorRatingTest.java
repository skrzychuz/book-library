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
  public void getAuthorListOrderOfRating(List<String> authorRatingList) throws Exception {
    //given
    List<String> actualName = new ArrayList<>();

    //when
    List<AuthorRating> actualList = bookLibraryService.getAuthorsRatings();
    for (AuthorRating aaa : actualList) {
      actualName.add(aaa.getAuthor());
    }

    //then
    assertNotNull(actualList);
    assertEquals(40, actualList.size());

    for (String expName : authorRatingList) {
      assertTrue("Error with " + expName, actualName.contains(expName));
    }
  }

  @Test
  @Parameters(method = "namesAndRating")
  public void shouldCheckRightAverageRating(String authorName, double avgrating)
      throws IOException {

    List<AuthorRating> authorRatingList = bookLibraryService.getAuthorsRatings();
    for (AuthorRating authorRating : authorRatingList) {
      if (authorRating.getAuthor().equals(authorName)) {
        assertEquals(avgrating, authorRating.getAverageRating(), 0);
      }
    }
  }

  private Object[] withNames() {
    return new Object[]{
        new Object[]{Arrays.asList("Gary Cornell", "Cay S. Horstmann", "James William Bayley Money",
            "Bruce Eckel", "Sundar Narasimhan", "Patrick Henry Winston", "J.F. Scheltema", "Geddy", "Bruce", "Jimmi")}};

  }

  private Object[] namesAndRating() {

    return new Object[]{
        new Object[]{"Geddy", 4.0},
        new Object[]{"Bruce", 1.0},
        new Object[]{"Jimmi", 4.3},

    };
  }
}