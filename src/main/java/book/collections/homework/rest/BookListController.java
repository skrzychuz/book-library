package book.collections.homework.rest;

import book.collections.homework.model.response.model.Book;
import book.collections.homework.service.BookLibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class BookListController {

  private final static Logger logger = LoggerFactory.getLogger(BookListController.class);

  private BookLibraryService bookLibraryService;

  public BookListController(BookLibraryService bookLibraryService) {
    this.bookLibraryService = bookLibraryService;
  }

  @GetMapping("/api/category/{categoryName}/books")
  public ResponseEntity getBookListByCategory(@PathVariable String categoryName) {
    List<Book> list = null;
    try {
      list = bookLibraryService.getBookListByCategory(categoryName);
    } catch (IOException | ParseException e) {
      logger.error(e.getMessage());
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(e.getMessage());
    }
    return ResponseEntity.ok(list);
  }
}
