package book.collections.homework.model;

import book.collections.homework.model.mapped.model.BookLibrary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class BookLibraryBuilder {

  @Autowired
  ObjectMapper mapper;

  public BookLibrary getAllBooks() {

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
