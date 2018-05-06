package book.collections.homework.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.response.model.Book;
import org.junit.Test;

import java.util.List;

public class BookLibraryServiceTest extends AbstractClassTest {

  @Test
  public void shouldGetBookByIsbn() throws Exception {

    //given
    Book expectedBook = new BookBuilder()
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

  @Test
  public void shouldReturnNullForNoExistIsbn() throws Exception {

    Book book = bookLibraryService.getBookByIsbn("noExist");
    assertNull(book);
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