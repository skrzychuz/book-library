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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BookLibraryImp implements BookLibrary {

  @Autowired
  Helper helper;


  @Override
  public Book getBookByIsbn(String isbn) throws IOException {

//    Book book = new Book();
//
    JsonFactory f = new MappingJsonFactory();
    JsonParser jp = f.createParser(new File("src\\main\\resources\\books.json"));
    File jsonInputFile = new File("src\\main\\resources\\books.json");

    JsonNode find = helper.getbyISBN(isbn);

    Book book = helper.bookAdapter(find);

//    JsonNode itemsNode = root.path("items");
//
//    for (JsonNode node: itemsNode) {
//      JsonNode idNode = node.path("id");
//      JsonNode volumeInfoNode = node.path("volumeInfo");
//      JsonNode titleNode = volumeInfoNode.path("title");
//      JsonNode publisherNode = volumeInfoNode.path("publisher");
//
//    }
//    JsonNode idNode = itemsNode.path("id");
//    JsonNode volumeInfoNode = itemsNode.path("volumeInfo");
//    JsonNode titleNode = volumeInfoNode.path("title");
//    JsonNode publisherNode = volumeInfoNode.path("publisher");
//    JsonNode industryIdentifiersNode = volumeInfoNode.path("industryIdentifiers");
//
//    for (JsonNode node: industryIdentifiersNode) {
//      if(node.path("type").asText().equals("ISBN_13")){
//        JsonNode identifierNode = volumeInfoNode.path("identifier");
//
//      }

//    }

//    JsonNode rootNode = mapper.readTree(jp);
//    // read employee id
//    JsonNode empId = rootNode.path("items");
//    JsonNode empId1 = empId.path("kind");
//    System.out.println(empId1.asText());
//    // read employee name
//    JsonNode empName = rootNode.path("id");
//    System.out.println(empName.asText());
//    // read direct reports
//    JsonNode drNode = rootNode.path("volumeInfo");
//    Iterator<JsonNode> itr = drNode.elements();
    return book;
  }


  @Override
  public List<Author> getAutorListOrderOfRating() {
    return null;
  }

  @Override
  public List<Book> getBookListByCategory() {
    return null;
  }
}
