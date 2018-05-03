package book.collections.homework.service;

import static org.junit.Assert.*;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.mapped.model.BookLibrary;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;

public class BookAdapterTest {

  BookAdapter bookAdapter;
  BookLibraryAdapter bookLibraryAdapter;
  @Mock
  private DateAdapter dateAdapter;

  private BookBuilder bookBuilder;

  @Before
  public void setUp() {
    bookAdapter = new BookAdapter(new DateAdapter(), new BookBuilder());
    bookLibraryAdapter = new BookLibraryAdapter(new ObjectMapper(),
        "src/test/resources/books.json");
    bookBuilder = new BookBuilder();
  }

  @Test
  public void shouldComapreBookWithConvertedItem() throws Exception {
//given
    Collections.singletonList("John John");
    Item item = bookLibraryAdapter.getBookLibrary().getItems().get(2);
    Book book = bookBuilder
        .withTitle("TitleTest")
        .withAuthors(Collections.singletonList("John Johun"))
        .withPublishedDate(-4417981200000L)
        .build();

    //when

    Book actualBook = bookAdapter.convertItemToBook(item);

      //then
    assertEquals(book.getTitle(), actualBook.getTitle());
    assertEquals(book.getPublishedDate(),actualBook.getPublishedDate());

  }

  @Test
  public void shouldCheckBookIsbn() throws Exception {
  }

}