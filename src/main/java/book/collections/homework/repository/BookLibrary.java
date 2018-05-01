package book.collections.homework.repository;

import book.collections.homework.model.Author;
import book.collections.homework.model.Book;

import java.io.IOException;
import java.util.List;

public interface BookLibrary {

  Book getBookByIsbn(String isbn) throws IOException;
  List<Author> getAutorListOrderOfRating();
  List<Book> getBookListByCategory();

}
