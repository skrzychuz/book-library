package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import book.collections.homework.configuration.RepositoryNotFoundException;
import book.collections.homework.model.response.model.Book;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

public class BookLibraryServiceTest extends AbstractClassTest {

  @Test
  public void shouldGetBookByIsbn() throws Exception {

    //given
    Book expectedBook = bookBuilder
        .withIsbn("7tkN1CYzn2cC")
        .withTitle("A Hypervista of the Java Landscape")
        .withPublisher("InfoStrategist.com")
        .withPublishedDate(0L)
        .build();

    //when
    Book actualBook = bookLibraryService.getBookByIsbn("9788324677610");

    //then
    assertNotNull(actualBook);
    assertEquals(expectedBook.getSubtitle(), actualBook.getSubtitle());

  }

  @Test(expected = RepositoryNotFoundException.class)
  public void shouldReturnNotFoundExceptionForNoExistIsbn() throws Exception {

    bookLibraryService.getBookByIsbn("noExist");
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