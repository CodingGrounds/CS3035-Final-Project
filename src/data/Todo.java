package data;

import java.io.Serializable;

public abstract class Todo implements Serializable {

  private String name;

  public Todo(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

}
