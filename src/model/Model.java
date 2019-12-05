package model;

import db.DatabaseManager;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

  /**
   * Database connector
   */
  private static DatabaseManager databaseManager = new DatabaseManager();

  /**
   * List of board Objects
   */
  private SimpleListProperty<Board> boards;

  /**
   * Constructor for Model
   */
  public Model() {
    ObservableList<Board> observableList = FXCollections.observableArrayList(databaseManager.getBoards());
    this.boards = new SimpleListProperty<>(observableList);
  }

  /**
   * List of board objects
   */
  public SimpleListProperty<Board> boardsProperty() {
    return this.boards;
  }

  /**
   * Saving board state
   */
  public void saveBoards() {
    int counter = 0;
    for(Board board: boards){
      databaseManager.createOrUpdateBoard(counter++, board);
    }
  }

  public static void main(String[] args){

    Model model = new Model();
    SimpleListProperty<Board> boards = model.boardsProperty();
    System.out.println("----Getting Boards----");
    System.out.println(boards);

    System.out.println("----Adding to the Board----");
    Board board = new Board("josh");
    Board board2 = new Board("isaac");
    boards.add(board);
    boards.add(board2);
    model.saveBoards();
    System.out.println("----Getting Boards----");
    System.out.println(boards);

  }

}
