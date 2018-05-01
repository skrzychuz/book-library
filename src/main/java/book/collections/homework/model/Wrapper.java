package book.collections.homework.model;

import java.util.ArrayList;
import java.util.List;

public class Wrapper {

  String requestedUrl;
  List<Items> items;

  public List<Items> getBooks() {
    return items;
  }

  public String getRequestedUrl() {

    return requestedUrl;
  }
}
