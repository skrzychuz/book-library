package book.collections.homework.service;

import book.collections.homework.model.BookBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class AbstractClassTest {

  DateAdapter dateAdapter = new DateAdapter();
  BookBuilder bookBuilder = new BookBuilder();
  BookAdapter bookAdapter = new BookAdapter(dateAdapter);
  private ObjectMapper mapper = new ObjectMapper();
  private String testFilePath = "src/test/resources/booksForTests.json";
  BookLibraryAdapter bookLibraryAdapter = new BookLibraryAdapter(mapper, testFilePath);
  BookLibraryService bookLibraryService = new BookLibraryService(bookAdapter,
      bookLibraryAdapter);


}
