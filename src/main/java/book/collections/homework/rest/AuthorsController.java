package book.collections.homework.rest;

import book.collections.homework.service.BookLibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthorsController {

  private final static Logger logger = LoggerFactory.getLogger(AuthorsController.class);
  private BookLibraryService bookLibraryService;

  public AuthorsController(BookLibraryService bookLibraryService) {
    this.bookLibraryService = bookLibraryService;
  }

  @GetMapping("/api/rating")
  public ResponseEntity getAuthorListOrderOfRating() {

    try {
      return ResponseEntity.ok(bookLibraryService.getAuthorsRatings());
    } catch (IOException e) {
      logger.error(e.getMessage());
      return ResponseEntity
          .status(HttpStatus.NOT_FOUND)
          .body(e.getMessage());
    }
  }
}
