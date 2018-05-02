package book.collections.homework.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.context.annotation.Bean;

import java.util.List;


public class BookLibrary {

  private String requestedUrl;
  private List<Item> items;

  public String getRequestedUrl() {
    return requestedUrl;
  }

  public void setRequestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
}

