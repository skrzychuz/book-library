package book.collections.homework.service;

import static org.junit.Assert.*;

import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.Book;
import org.junit.Test;

import java.util.Collections;

public class BookAdapterTest extends AbstractClassTest {

  @Test
  public void shouldComapreBookWithConvertedItem() throws Exception {

    //given
    Item item = bookLibraryAdapter.getBookLibrary().getItems().get(2);
    Book book = bookBuilder
        .withTitle("TitleTest")
        .withAuthors(Collections.singletonList("John John"))
        .withPublishedDate(-4417981200000L)
        .build();

    //when
    Book actualBook = bookAdapter.convertItemToBook(item);

    //then
    assertEquals(book.getTitle(), actualBook.getTitle());
    assertEquals(book.getPublishedDate(), actualBook.getPublishedDate());
    assertEquals(book.getAuthors(), actualBook.getAuthors());
  }

  @Test
  public void shouldCheckRightBookIsbn() throws Exception {

    //given
    Item itemWithIsbn13 = bookLibraryAdapter.getBookLibrary().getItems().get(1);
    Item itemWithOutIsbn13 = bookLibraryAdapter.getBookLibrary().getItems().get(2);

    //when
    String isbn13 = bookAdapter.getBookIsbn(itemWithIsbn13);
    String isbnId = bookAdapter.getBookIsbn(itemWithOutIsbn13);

    //then
    assertEquals("978022628isbn13", isbn13);
    assertEquals("97802262isbnId", isbnId);
  }
}