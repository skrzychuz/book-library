package book.collections.homework.service;

import book.collections.homework.model.BookBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class AbstractClassTest {

  private String testFilePath = "src/test/resources/booksForTests.json";
  private String isbnType = "ISBN_13";
  private ObjectMapper mapper = new ObjectMapper();
  DateAdapter dateAdapter = new DateAdapter();
  BookBuilder bookBuilder = new BookBuilder();
  BookLibraryAdapter bookLibraryAdapter = new BookLibraryAdapter(mapper, testFilePath);
  BookAdapter bookAdapter = new BookAdapter(dateAdapter, bookBuilder);
  BookLibraryService bookLibraryService = new BookLibraryService(isbnType, bookAdapter,
      bookLibraryAdapter);


}
