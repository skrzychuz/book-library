package book.collections.homework.service;

import static org.assertj.core.util.Arrays.*;
import static org.mockito.Mockito.when;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.mapped.model.BookLibrary;
import book.collections.homework.model.mapped.model.Item;
import book.collections.homework.model.response.model.Book;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;


import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BookLibraryServiceTest {

  Book book;
  BookLibraryService bookLibraryService;
  @Mock
  private BookLibraryAdapter libraryAdapter;
  @Mock
  private BookAdapter bookAdapter;

  @Before
  public void setUp() {

    bookLibraryService = new BookLibraryService(bookAdapter, libraryAdapter);

    BookBuilder bookBuilder = new BookBuilder();
    book = bookBuilder
        .withIsbn("123")
        .withTitle("Book Title")
        .withSubtitle("Book Subtitle")
        .build();

  }

  @Test
  public void shouldGetBookByIsbn() throws Exception {
    //given


    //then

  }


  @Test
  public void shouldgetBookListByCategory() throws Exception {
    //given
    //when

    //then
  }

  @Test
  public void getAuthorListOrderOfRating() throws Exception {
    //given
    //when
    //then
  }

}