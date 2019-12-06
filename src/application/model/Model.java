package application.model;

import db.BoardData;
import db.DatabaseManager;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.model.cards.Bug;
import application.model.cards.Card;
import application.model.cards.CheckList;
import application.model.cards.Event;
import application.model.cards.Story;

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

  public static void main(String[] args){

    Model model = new Model();
    SimpleListProperty<Board> boards = model.boardsProperty();
    System.out.println("----Getting Boards----");
    System.out.println(boards);

    Board board = new Board("Board");

    List<CheckBoxData> strings = new ArrayList<>();
    strings.add(new CheckBoxData("Josh", false));
    List<Card> cards = new ArrayList<>();

    board.columnsListProperty().add(new Column("Columns", cards));

    cards.add(new Bug("Bug 1", "B 1", "steps 1"));
    cards.add(new CheckList("Checklist 1", "D 1", strings));
    cards.add(new Event("Event 1", "D 1", "s213132"));
    cards.add(new Story("story 1", "d 1", 2, "requirements 1"));

    System.out.print("Board: " + board);
    boards.add(board);

    model.saveBoards();
//    Board board = new Board("isaac")

//    Model application.model = new Model();

//
//    System.out.println("----Adding to the Board----");
//    Board board = new Board("isaac");
//    board.columnListProperty().add(new application.Column("col1"));
//    board.columnListProperty().add(new application.Column("col2"));
//    boards.add(board);
////    application.model.saveBoards();
//    System.out.println("----Getting Boards----");
    List<Board> result = model.boardsProperty().get();
    System.out.println("");

  }

}
