package book.collections.homework.service;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import book.collections.homework.model.Author;
import book.collections.homework.model.Book;
import book.collections.homework.model.Response;
import book.collections.homework.model.Wrapper;
import book.collections.homework.repository.BookLibrary;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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
    return helper.bookList(helper.getBookList(category));

  }
}
