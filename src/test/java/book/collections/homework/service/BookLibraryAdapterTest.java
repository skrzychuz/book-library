package book.collections.homework.service;

import static org.junit.Assert.*;

import book.collections.homework.model.mapped.model.BookLibrary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

public class BookLibraryAdapterTest extends AbstractClassTest {

  @Test
  public void shouldGetAllBooks() throws Exception {

    //when
    BookLibrary bookLibrary = bookLibraryAdapter.getBookLibrary();

    //then
    assertNotNull(bookLibrary);
    assertEquals(40, bookLibrary.getItems().size());
    assertEquals("id for test", bookLibrary.getItems().get(0).getId());
    assertEquals("T-Z8HKgIi5EC", bookLibrary.getItems().get(39).getId());
  }
}
