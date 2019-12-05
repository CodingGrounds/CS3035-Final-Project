package application.model;

import db.BoardData;
import db.ColumnData;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents the application.model.Board Object
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Board {

  /**
   * Name of the application.model.Board
   */
  private String name;

  /**
   * List of application.Column Objects per application.model.Board
   */
  private transient SimpleListProperty<Column> columns;

  /**
   * Constructor for the application.model.Board
   */
  public Board(String name) {
    this.name = name;
    ArrayList<Column> columns = new ArrayList<>();
    ObservableList<Column> observableList = FXCollections.observableList(columns);
    this.columns = new SimpleListProperty<>(observableList);
  }

  public Board(String name, List<Column> columns){
    this.name = name;
    ObservableList<Column> observableList = FXCollections.observableList(columns);
    this.columns = new SimpleListProperty<>(observableList);
  }

  public SimpleListProperty<Column> columnsListProperty(){
    return this.columns;
  }

  /**
   * Override Method for toString
   */
  @Override
  public String toString() {
    return this.name;
  }

  public String getName() {
    return this.name;
  }

  public static Board convertToBoard(BoardData input){
    List<ColumnData> columnDataList = input.getColumns();
    List<Column> columns = new ArrayList<>();
    for(ColumnData columnData: columnDataList){
      columns.add(Column.convertToColumn(columnData));
    }
    return new Board(input.getName(), columns);
  }
}
