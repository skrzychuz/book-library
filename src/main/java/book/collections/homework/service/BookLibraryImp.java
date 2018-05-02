package book.collections.homework.service;

import book.collections.homework.model.BookLibrary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class BookLibraryImp implements book.collections.homework.repository.BookLibrary {

  @Autowired
  private Helper helper;


  @Override
  public BookLibrary getBookByIsbn(String isbn) throws IOException, ParseException {

    JsonNode find = helper.getbyISBN(isbn);
    return helper.bookAdapter(find);
  }


  @Override
  public List<BookLibrary> getBookListByCategory(String category)
      throws IOException, ParseException {
    return helper.bookNodeToObject(helper.getBookList(category));
  }


  public BookLibrary getAll() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    BookLibrary bookLibrary = null;
    try {
      bookLibrary = mapper
          .readValue(new File("src\\main\\resources\\books.json"), BookLibrary.class);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return bookLibrary;

  }
}
