package book.collections.homework.rest;

import book.collections.homework.service.BookLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthorsController {

  private BookLibraryService bookLibraryService;

  @Autowired
  public AuthorsController(BookLibraryService bookLibraryService) {
    this.bookLibraryService = bookLibraryService;
  }

  @GetMapping("/api/rating")
  public ResponseEntity getAuthorListOrderOfRating() throws IOException {
    return ResponseEntity.ok(bookLibraryService.getAuthorsRatings());
  }
}
