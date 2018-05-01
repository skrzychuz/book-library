package book.collections.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response implements Serializable {
  String requestedUrl;
  List<Items> items;

  public String getRequestedUrl() {
    return requestedUrl;
  }

  public void setRequestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
  }

  public List<Items> getItems() {
    return items;
  }

  public void setItems(List<Items> items) {
    this.items = items;
  }
}
