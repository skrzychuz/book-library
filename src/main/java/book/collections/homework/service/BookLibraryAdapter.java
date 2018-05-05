package book.collections.homework.service;

import book.collections.homework.model.mapped.model.BookLibrary;
import book.collections.homework.model.mapped.model.Item;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;


@Service
public class BookLibraryAdapter {

  private final File myFileDatabase;
  private ObjectMapper mapper;

  public BookLibraryAdapter(ObjectMapper mapper, @Value("${database.filepath}") String pathname) {
    this.mapper = mapper;
    this.myFileDatabase = new File(pathname);
   }

  public BookLibrary getBookLibrary() {

    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    BookLibrary bookLibrary = null;
    try {
      bookLibrary = mapper
          .readValue(myFileDatabase, BookLibrary.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return bookLibrary;
  }

  public Set<String> getAuthorsList() {

    Set<String> authorsList = new TreeSet<>();

    for (Item item : getBookLibrary().getItems()) {
      if (item.getVolumeInfo().getAuthors() != null) {
        authorsList.addAll(item.getVolumeInfo().getAuthors());
      }
    }
    return authorsList;
  }

}
