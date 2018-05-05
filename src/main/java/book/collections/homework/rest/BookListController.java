package book.collections.homework.rest;

import book.collections.homework.model.response.model.Book;
import book.collections.homework.service.BookLibraryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BookListController {

  private BookLibraryService bookLibraryService;

  public BookListController(BookLibraryService bookLibraryService) {
    this.bookLibraryService = bookLibraryService;
  }

  @GetMapping("/api/category/{categoryName}/books")
  public ResponseEntity getBookListByCategory(@PathVariable String categoryName)
      throws IOException {
    List<Book> list = bookLibraryService.getBookListByCategory(categoryName);
    return ResponseEntity.ok(list);
  }
}
