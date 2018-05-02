package book.collections.homework.repository;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookLibrary {

  book.collections.homework.model.BookLibrary getBookByIsbn(String isbn) throws IOException, ParseException;
  List<book.collections.homework.model.BookLibrary> getBookListByCategory(String category) throws IOException, ParseException;

}
