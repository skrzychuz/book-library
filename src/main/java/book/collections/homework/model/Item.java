package book.collections.homework.model;

public class Item {

  private String id;
  private VolumeInfo volumeInfo;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public VolumeInfo getVolumeInfo() {
    return volumeInfo;
  }

  public void setVolumeInfo(VolumeInfo volumeInfo) {
    this.volumeInfo = volumeInfo;
  }
}
