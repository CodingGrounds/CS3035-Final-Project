package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents the model.Board Object
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Board implements Serializable {

  /**
   * Name of the model.Board
   */
  private String name;

  /**
   * List of Column Objects per model.Board
   */
  private transient SimpleListProperty<Column> columns;

  /**
   * Constructor for the model.Board
   */
  public Board(String name) {
    this.name = name;
    ArrayList<Column> columns = new ArrayList<>();
    ObservableList<Column> observableList = FXCollections.observableList(columns);
    this.columns = new SimpleListProperty<>(observableList);
  }

  /**
   * Override Method for toString
   */
  @Override
  public String toString() {
    return this.name;
  }

  /**
   *
   */
  public SimpleListProperty<Column> columnListProperty() {
    return this.columns;
  }

  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    s.writeObject(columnListProperty().toArray());
  }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    this.name = (String) s.readObject();
    ArrayList<Column> columns = new ArrayList<>();
    ObservableList<Column> observableList = FXCollections.observableList(columns);
    this.columns = new SimpleListProperty<>(observableList);
    System.out.println("help");
  }


}
