package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import book.collections.homework.configuration.RepositoryNotFoundException;
import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


@RunWith(JUnitParamsRunner.class)
public class AuthorRatingTest {

  Book book;
  private BookLibraryService bookLibraryService;
  DateAdapter dateAdapter;


  private BookLibraryAdapter libraryAdapter;
  private BookAdapter bookAdapter;
  private Book expecteddBook;


  @Before
  public void setUp() {

    bookLibraryService = new BookLibraryService("ISBN_13",
        new BookAdapter
            (new DateAdapter(), new BookBuilder()),
        new BookLibraryAdapter(new ObjectMapper(), "src/test/resources/books.json"));

    BookBuilder bookBuilder = new BookBuilder();
    expecteddBook = bookBuilder
        .withIsbn("7tkN1CYzn2cC")
        .withTitle("A Hypervista of the Java Landscape")
        .withPublisher("InfoStrategist.com")
        .withPublishedDate(0L)
        .build();
  }

  @Test
  @Parameters(method = "WithNames")
  public void getAuthorListOrderOfRating(List<String> authorRatingList) throws Exception {
    //given
    List<String> actualName = new ArrayList<>();

    //when
    List<AuthorRating> actualList = bookLibraryService.getAuthorListOrderOfRating();

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
  @Parameters(method = "NamesAndRating")
  public void shouldCheckRightAverageRating(String authorName, double avgrating) {

    List<AuthorRating> authorRatingList = bookLibraryService.getAuthorListOrderOfRating();
    for (AuthorRating authorRating : authorRatingList) {
      if (authorRating.getAuthor().equals(authorName)) {
        assertEquals(avgrating, authorRating.getAverageRating(), 0);
      }
    }
  }

  private Object[] WithNames() {
    return new Object[]{
        new Object[]{Arrays.asList("Gary Cornell", "Cay S. Horstmann", "James William Bayley Money",
            "Bruce Eckel", "Sundar Narasimhan", "Patrick Henry Winston", "J.F. Scheltema", "Geddy", "Bruce", "Jimmi")}};

  }

  private Object[] NamesAndRating() {

    return new Object[]{
        new Object[]{"Geddy", 4.0},
        new Object[]{"Bruce", 1.0},
        new Object[]{"Jimmi", 4.3},

    };
  }
}