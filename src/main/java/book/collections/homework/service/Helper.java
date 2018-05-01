package book.collections.homework.service;

import book.collections.homework.model.Book;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class Helper {

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

  public Book bookAdapter(JsonNode node) throws IOException {


    mapper.setSerializationInclusion(Include.NON_EMPTY);

    Book book = new Book();

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

    book.setTitle(volumeInfo.path("title").asText());
    book.setSubtitle(volumeInfo.path("subtitle").asText());
    book.setPublisher(volumeInfo.path("publisher").asText());
    book.setPublishedDate(volumeInfo.path("publishedDate").asLong());
    book.setDescription(volumeInfo.path("description").asText());
    book.setPageCount(volumeInfo.path("pageCount").asInt());
    book.setThumbnailUrl(imageLinks.path("thumbnail").asText());
    book.setLanguage(volumeInfo.path("language").asText());
    book.setPreviewLink(volumeInfo.path("previewLink").asText());
    book.setAverageRating(volumeInfo.path("averageRating").asDouble());
    book.setAuthors(authorsList);
    book.setCategories(catList);
    book.setIsbn(isbn);

    return book;
  }
}
