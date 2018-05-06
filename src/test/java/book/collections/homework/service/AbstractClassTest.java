package book.collections.homework.service;

import static book.collections.homework.configuration.VariableConfig.TEST_FILE;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class AbstractClassTest {

  private ObjectMapper mapper = getTestMapper();
  private String testFilePath = TEST_FILE;
  protected BookLibraryAdapter bookLibraryAdapter = new BookLibraryAdapter(mapper, testFilePath);
  protected DateAdapter dateAdapter = new DateAdapter();
  protected BookAdapter bookAdapter = new BookAdapter(dateAdapter);
  protected BookLibraryService bookLibraryService = new BookLibraryService(bookAdapter,
      bookLibraryAdapter);


  ObjectMapper getTestMapper () {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }
}
