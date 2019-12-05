import java.io.Serializable;

public class Board implements Serializable {

  private String name;

  public Board(String name) {
    this.name = name;
  }

  public String toString(){
    return this.name;
  }

}
