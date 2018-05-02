package book.collections.homework.service;

import book.collections.homework.model.Author;
import book.collections.homework.model.Book;
import book.collections.homework.repository.BookLibrary;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class BookLibraryImp implements BookLibrary {

  @Autowired
  private Helper helper;


  @Override
  public Book getBookByIsbn(String isbn) throws IOException, ParseException {

    JsonNode find = helper.getbyISBN(isbn);
    return helper.bookAdapter(find);
  }


  @Override
  public List<Author> getAutorListOrderOfRating() {
    return null;
  }

  @Override
  public List<Book> getBookListByCategory(String category) throws IOException, ParseException {
    return helper.bookNodeToObject(helper.getBookList(category));

  }
}
