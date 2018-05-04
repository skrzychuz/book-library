package book.collections.homework.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class LibraryAbstractTest {
  BookLibraryAdapter adapter = new BookLibraryAdapter(new ObjectMapper(), "src/test/resources/books.json");

}
