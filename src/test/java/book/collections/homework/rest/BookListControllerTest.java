package book.collections.homework.rest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import book.collections.homework.model.BookBuilder;
import book.collections.homework.model.response.model.Book;
import book.collections.homework.service.BookLibraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(BookListController.class)
public class BookListControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookLibraryService bookLibraryService;

  @Test
  public void shouldGetBookListByCategory() throws Exception {
    Book book1 = new BookBuilder()
        .withTitle("My dog")
        .withPageCount(550)
        .withCategories(Arrays.asList("Animals"))
        .build();

    Book book2 = new BookBuilder()
        .withTitle("My cat")
        .withPageCount(485)
        .withCategories(Arrays.asList("Animals"))
        .build();
    List<Book> bookList = new ArrayList<>();
    bookList.add(book1);
    bookList.add(book2);

    when(bookLibraryService.getBookListByCategory("Animals")).thenReturn(bookList);

    this.mockMvc.perform(get("/api/category/{categoryName}/books", "Animals")
        .contentType(contentType)
        .content(String.valueOf(bookList)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].title", is("My dog")))
        .andExpect(jsonPath("$[0].pageCount", is(550)))
        .andExpect(jsonPath("$[1].title", is("My cat")))
        .andExpect(jsonPath("$[1].pageCount", is(485)));
  }
}

