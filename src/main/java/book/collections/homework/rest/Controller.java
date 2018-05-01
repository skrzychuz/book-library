package book.collections.homework.rest;

import book.collections.homework.model.Author;
import book.collections.homework.model.Book;
import book.collections.homework.repository.BookLibrary;
import book.collections.homework.service.BookLibraryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

  @Autowired
  BookLibraryImp bookLibraryImp;


  @RequestMapping(value = "/api/book/{isbn}", method = RequestMethod.GET)
  public Book getBookByIsbn(@PathVariable String isbn) throws IOException {

    return bookLibraryImp.getBookByIsbn(isbn);
  }

  @RequestMapping(value = "/api/category/{categoryName}/books}", method = RequestMethod.GET)
  public List<Book> getBookListByCategory(@PathVariable String isbn) {

    return new ArrayList<>();


  }


  @RequestMapping(value = "/api/rating", method = RequestMethod.GET)
  public List<Author> getAutorListOrderOfRating(@PathVariable String isbn) {

    return new ArrayList<>();
  }


}
