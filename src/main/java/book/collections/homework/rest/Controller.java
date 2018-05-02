package book.collections.homework.rest;

import book.collections.homework.model.AuthorRating;
import book.collections.homework.model.Book;
import book.collections.homework.model.BookLibrary;
import book.collections.homework.service.BookLibraryImp;
import book.collections.homework.service.newhel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.TreeSet;

@RestController
public class Controller {

  @Autowired
  BookLibraryImp bookLibraryImp;
  @Autowired
  newhel newhel;


  @RequestMapping(value = "/api/book/{isbn}", method = RequestMethod.GET)
  public Book getBookByIsbn(@PathVariable String isbn) throws IOException, ParseException {

//    return bookLibraryImp.getBookByIsbn(isbn);
    return newhel.GETISBN(isbn);
  }

  @RequestMapping(value = "/api/category/{categoryName}/books", method = RequestMethod.GET)
  public List<Book> getBookListByCategory(@PathVariable String categoryName)
      throws IOException, ParseException {
    newhel newhel = new newhel();
//    return bookLibraryImp.getBookListByCategory(categoryName);
//return bookLibraryImp.getAll();
//    return newhel.GETISBN("N1IiAQAAIAAJ");
    return newhel.getListBookbyCategory(categoryName);

  }

  @RequestMapping(value = "/api/rating", method = RequestMethod.GET)
  public List<AuthorRating> getAutorListOrderOfRating() {

    return newhel.getAverageAuthorsRatin();
  }


}
