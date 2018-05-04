package book.collections.homework.service;

import static org.junit.Assert.*;

import book.collections.homework.model.mapped.model.BookLibrary;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookLibraryAdapterTest extends LibraryAbstractTest {

//  BookLibraryAdapter adapter;
//
//  @Before
//  public void setUp() {
//    adapter = new BookLibraryAdapter(new ObjectMapper(), "src/test/resources/books.json");
//  }

  @Test
  public void shouldGetAllBooks() throws Exception {
    //given
    //when
    BookLibrary bookLibrary = adapter.getBookLibrary();
    //then
    assertNotNull(bookLibrary);
    assertEquals(40, bookLibrary.getItems().size());
    assertEquals("id for test", bookLibrary.getItems().get(0).getId());

  }
}
