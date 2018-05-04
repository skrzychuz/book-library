package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import book.collections.homework.configuration.RepositoryNotFoundException;
import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.Mock;


import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@RunWith(MockitoJUnitRunner.class)
public class BookLibraryServiceTest {

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
  public void shouldGetBookByIsbn() throws Exception {

    //given
    BookBuilder bookBuilder = new BookBuilder();

    //when

    Book actualBook = bookLibraryService.getBookByIsbn("9788324677610");

    //then
    assertNotNull(actualBook);
    assertEquals(expecteddBook.getSubtitle(), actualBook.getSubtitle());

  }

  @Test(expected = RepositoryNotFoundException.class)
  public void shouldReturnNotFoundExceptionForNoExistIsbn() throws Exception {

    Book actualBook = bookLibraryService.getBookByIsbn("noExist");
  }


  @Test
  public void shouldGetBookListByCategory() throws Exception {

    //when
    List<Book> bookList = bookLibraryService.getBookListByCategory("Java");

    //then
    assertNotNull(bookList);
    assertEquals(2, bookList.size());
    for (Book book : bookList) {
      assertTrue("Error in book " + book.getIsbn(), book.getCategories().contains("Java"));
    }
  }
}