package book.collections.homework.model.mapped.model;

import java.util.List;

public class BookLibrary {

  private String requestedUrl;
  private List<Item> items;

  public BookLibrary() {
  }

  public String getRequestedUrl() {
    return requestedUrl;
  }

  public List<Item> getItems() {
    return items;
  }
}

