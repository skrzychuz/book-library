package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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


@RunWith(JUnitParamsRunner.class)
public class AuthorRatingTest {

  Book book;
  BookLibraryService bookLibraryService;
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
  @Parameters(method = "testsParamsWithName")
  public void getAuthorListOrderOfRating(List<String> authorRatingList) throws Exception {
    //given
    List<String> expectedName = authorRatingList;
    List<String> actualName = new ArrayList<>();

    //when
    List<AuthorRating> actualList = bookLibraryService.getAuthorListOrderOfRating();

    for (AuthorRating aaa : actualList) {
      actualName.add(aaa.getAuthor());
    }

    //then
    assertNotNull(actualList);
    assertEquals(43, actualList.size());

    for (String expName : expectedName) {
      assertTrue("Error with " + expName, actualName.contains(expName));
    }
  }

  @Test
  public void getAuthorsList() {

  }

  //  @Test
//  @Parameters(method = "containsParameters")
//  public void testContains_usingNamedMethodParameters(final List<String> list, final String a,
//      final boolean expectedResult) {
//    assertEquals(expectedResult, testSubject.contains(list, a));
//  }
  private Object[] testsParamswithAuthorRating() {
    return new Object[]{
        new Object[]{Arrays.asList(new AuthorRating("John", 4.5), new AuthorRating("Steven", 3.3),
            new AuthorRating("Bruce", 5)), 2.77},
        new Object[]{Arrays.asList(new AuthorRating("Joe", 1), new AuthorRating("Tom", 1),
            new AuthorRating("Ritchy", 1.1)) },
        new Object[]{Arrays.asList(new AuthorRating("Zakk", 4.5), new AuthorRating("Neil", 3),
            new AuthorRating("Mike", 5))}};
  }


  private Object[] testsParamsWithName() {
    return new Object[]{
        new Object[]{Arrays.asList("Gary Cornell", "Cay S. Horstmann", "James William Bayley Money",
            "Bruce Eckel", "Sundar Narasimhan", "Patrick Henry Winston", "J.F. Scheltema")}};

  }
}