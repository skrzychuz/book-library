package book.collections.homework.rest;

import book.collections.homework.model.response.model.Book;
import book.collections.homework.service.BookLibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BookController {

  private BookLibraryService bookLibraryService;

  public BookController(BookLibraryService bookLibraryService) {
    this.bookLibraryService = bookLibraryService;
  }

  @GetMapping("/api/book/{isbn}")
  public ResponseEntity getBookByIsbn(@PathVariable("isbn") String isbn) throws IOException {
    Book book = bookLibraryService.getBookByIsbn(isbn);
    if (book == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found");
    }
    return ResponseEntity.ok(book);
  }
}
