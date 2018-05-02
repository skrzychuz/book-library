package book.collections.homework.service;

import book.collections.homework.model.BookLibrary;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class Helper {

  @Autowired
  DateAdapter dateAdapter;
  @Autowired
  private ObjectMapper mapper;


  public JsonNode getbyISBN(String isbn) throws IOException {

    JsonNode root = mapper.readTree(new File("src\\main\\resources\\books.json"));
    JsonNode itemsNode = root.path("items");

    for (JsonNode itemNode : itemsNode) {
      JsonNode idNode = itemNode.path("id");
      JsonNode volumeInfoNode = itemNode.path("volumeInfo");
      JsonNode industryIdentifiers = volumeInfoNode.path("industryIdentifiers");
      for (JsonNode industryIdNode : industryIdentifiers) {
        if (industryIdNode.path("type").asText().equals("ISBN_13")) {
          if (industryIdNode.path("identifier").asText().equals(isbn)) {
            return itemNode;
          }
        }
      }
      if (idNode.asText().equals(isbn)) {
        return itemNode;
      }
    }
    return null;
  }

  public BookLibrary bookAdapter(JsonNode node) throws IOException, ParseException {

    mapper.setSerializationInclusion(Include.NON_EMPTY);

    BookLibrary bookLibrary = new BookLibrary();

    String isbn = "";
    JsonNode volumeInfo = node.path("volumeInfo");
    JsonNode imageLinks = volumeInfo.path("imageLinks");

    String authors = volumeInfo.path("authors").toString();
    List<String> authorsList = mapper.readValue(authors, new TypeReference<List<String>>() {
    });

    String categories = volumeInfo.path("categories").toString();
    List<String> catList = mapper.readValue(categories, new TypeReference<List<String>>() {
    });

    JsonNode isbnNode = volumeInfo.path("industryIdentifiers");
    for (JsonNode isbn123 : isbnNode) {
      if (isbn123.path("type").asText().equals("ISBN_13")) {
        isbn = isbn123.path("identifier").asText();
      } else {
        isbn = node.path("id").asText();
      }
    }
//
//    bookLibrary.setTitle(volumeInfo.path("title").asText());
//    bookLibrary.setSubtitle(volumeInfo.path("subtitle").asText());
//    bookLibrary.setPublisher(volumeInfo.path("publisher").asText());
//
//    Long timestamp = dateAdapter
//        .stringToTimestampAdapter(volumeInfo.path("publishedDate").asText());
//
//    bookLibrary.setPublishedDate(timestamp);
//    bookLibrary.setDescription(volumeInfo.path("description").asText());
//    bookLibrary.setPageCount(volumeInfo.path("pageCount").asInt());
//    bookLibrary.setThumbnailUrl(imageLinks.path("thumbnail").asText());
//    bookLibrary.setLanguage(volumeInfo.path("language").asText());
//    bookLibrary.setPreviewLink(volumeInfo.path("previewLink").asText());
//    bookLibrary.setAverageRating(volumeInfo.path("averageRating").asDouble());
//    bookLibrary.setAuthors(authorsList);
//    bookLibrary.setCategories(catList);
//    bookLibrary.setIsbn(isbn);

    return bookLibrary;
  }


  public List<JsonNode> getBookList(String category) throws IOException {

    List<JsonNode> jsonNodesList = new ArrayList<>();

    JsonNode root = mapper.readTree(new File("src\\main\\resources\\books.json"));
    JsonNode itemsNode = root.path("items");

    for (JsonNode itemNode : itemsNode) {

      JsonNode volumeInfoNode = itemNode.path("volumeInfo");
      JsonNode categories = volumeInfoNode.path("categories");
      for (JsonNode cat : categories) {
        if (cat.asText().equals(category)) {
          jsonNodesList.add(itemNode);
        }
      }
    }
    return jsonNodesList;
  }


  public List<BookLibrary> bookNodeToObject(List<JsonNode> nodeList) {
    List<BookLibrary> bookLibraryList = new ArrayList<>();

    for (JsonNode jsonNode : nodeList) {
      try {
        bookLibraryList.add(bookAdapter(jsonNode));
      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
    }

    return bookLibraryList;
  }

  public Set<String> authorsSet() throws IOException {

    ObjectMapper mapper11 = new ObjectMapper();
    JsonNode root = mapper11.readTree(new File("src\\main\\resources\\books.json"));
    JsonNode itemsNode = root.path("items");

    Set<String> authorsList = new TreeSet<>();

    for (JsonNode itemNode : itemsNode) {
      JsonNode idNode = itemNode.path("id");
      JsonNode volumeInfoNode = itemNode.path("volumeInfo");
      JsonNode authors = volumeInfoNode.path("authors");

      for (JsonNode aut : authors) {
        authorsList.add(aut.asText());
      }
    }
    return authorsList;
  }

  public double averageRate() throws IOException {

    double sum = 0.0;
    int divide = 1;
    ObjectMapper mapper11 = new ObjectMapper();
    JsonNode root = mapper11.readTree(new File("src\\main\\resources\\books.json"));
    JsonNode itemsNode = root.path("items");
    Set<String> authors = authorsSet();

    for (String autor : authors) {
      for (JsonNode itemNode : itemsNode) {
        JsonNode volumeInfoNode = itemNode.path("volumeInfo");
        JsonNode authors1 = volumeInfoNode.path("authors");
        for (JsonNode node : authors1) {
          if (authors1.equals(autor)) {
            sum += volumeInfoNode.path("averageRating").asDouble();
          }


        }
      }


    }

    return sum;
  }


}
