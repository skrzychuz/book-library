package book.collections.homework.rest;

import static org.hamcrest.Matchers.is;
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

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookLibraryService bookLibraryService;

  @Test
  public void shouldAddInvoiceToDatabase() throws Exception {
    Book book = new BookBuilder()
        .withTitle("Robin Hood")
        .withPageCount(777).build();

    when(bookLibraryService.getBookByIsbn("123456")).thenReturn(book);

    this.mockMvc.perform(get("/api/book/{isbn}", 123456)
        .contentType(contentType)
        .content(String.valueOf(book)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title", is("Robin Hood")))
        .andExpect(jsonPath("$.pageCount", is(777)));
  }
}

