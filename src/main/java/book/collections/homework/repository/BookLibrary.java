package book.collections.homework.repository;

import book.collections.homework.model.Author;
import book.collections.homework.model.Book;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface BookLibrary {

  Book getBookByIsbn(String isbn) throws IOException, ParseException;
  List<Author> getAutorListOrderOfRating();
  List<Book> getBookListByCategory(String category) throws IOException, ParseException;

}
