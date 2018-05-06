package book.collections.homework.rest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import book.collections.homework.model.response.model.AuthorRating;
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
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorsController.class)
public class AuthorsControllerTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(),
      Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookLibraryService bookLibraryService;

  @Test
  public void shouldGetBookListByCategory() throws Exception {
    AuthorRating authorRating1 = new AuthorRating("James", 4.5);
    AuthorRating authorRating2 = new AuthorRating("John", 4.0);
    AuthorRating authorRating3 = new AuthorRating("Jimmi", 3.8);

    List<AuthorRating> authorRatingsList = new ArrayList<>();
    authorRatingsList.add(authorRating1);
    authorRatingsList.add(authorRating2);
    authorRatingsList.add(authorRating3);

    when(bookLibraryService.getAuthorsRatings()).thenReturn(authorRatingsList);

    this.mockMvc.perform(get("/api/rating")
        .contentType(contentType)
        .content(String.valueOf(authorRatingsList)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].author", is("James")))
        .andExpect(jsonPath("$[0].averageRating", is(4.5)))
        .andExpect(jsonPath("$[1].author", is("John")))
        .andExpect(jsonPath("$[2].averageRating", is(3.8)));
  }
}
