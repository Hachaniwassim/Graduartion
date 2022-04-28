package app.igesa.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FileInfo {
  private String name;
  private String url;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  public FileInfo(String name, String url) {
    this.name = name;
    this.url = url;

  }
  public FileInfo(String name, String url, Long id) {
    this.name = name;
    this.url = url;
    this.id=id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public FileInfo() {
    super();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
