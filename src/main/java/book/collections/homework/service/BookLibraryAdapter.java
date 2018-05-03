package book.collections.homework.service;

import book.collections.homework.model.mapped.model.BookLibrary;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class BookLibraryAdapter {

  @Autowired
  ObjectMapper mapper;

  @Value("${database.filepath}")
  String pathname;


  public BookLibrary getAllBooks() {

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    BookLibrary bookLibrary = null;
    try {
      bookLibrary = mapper
          .readValue(new File(pathname), BookLibrary.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bookLibrary;
  }


}
