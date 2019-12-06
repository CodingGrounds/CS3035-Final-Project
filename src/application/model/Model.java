package application.model;

import db.BoardData;
import db.DatabaseManager;
import java.util.ArrayList;
import java.util.List;
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
    List<BoardData> boardData  = databaseManager.getBoards();
    ArrayList<Board> boards = new ArrayList<>();
    for(BoardData boardData1: boardData){
      boards.add(Board.convertToBoard(boardData1));
    }
    ObservableList<Board> observableList = FXCollections.observableArrayList(boards);
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
      databaseManager.createOrUpdateBoard(counter++, BoardData.convertToBoardData(board));
    }
  }

}
