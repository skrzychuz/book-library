package book.collections.homework.rest;

import book.collections.homework.model.response.model.AuthorRating;
import book.collections.homework.model.response.model.Book;
import book.collections.homework.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class Controller {

  @Autowired
  private BookLibraryService bookLibraryService;

  @RequestMapping(value = "/api/book/{isbn}", method = RequestMethod.GET)
  public Book getBookByIsbn(@PathVariable String isbn) throws IOException, ParseException {
    return bookLibraryService.getBookByIsbn(isbn);
  }

  @RequestMapping(value = "/api/category/{categoryName}/books", method = RequestMethod.GET)
  public List<Book> getBookListByCategory(@PathVariable String categoryName)
      throws IOException, ParseException {
    return bookLibraryService.getBookListByCategory(categoryName);
  }

  @RequestMapping(value = "/api/rating", method = RequestMethod.GET)
  public List<AuthorRating> getAuthorListOrderOfRating() {
    return bookLibraryService.getAuthorListOrderOfRating();
  }
}
