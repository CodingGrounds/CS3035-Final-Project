package db;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import application.model.Board;
import application.model.Column;
import application.model.cards.Bug;
import application.model.cards.Card;
import application.model.cards.CheckList;
import application.model.cards.Event;
import application.model.cards.Story;

/**
 * This class represents the db.DatabaseManager for accessing the Database
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 *
 */
public class DatabaseManager {

  private Connection connect() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:todo");

      if (!checkTable(connection)) {
        System.out.println("Database does not have Table board");
        System.out.println("Creating new table....");
        createTable(connection);
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return connection;
  }

  /*
   * Method for checking if a board table exists inside Database
   */
  private boolean checkTable(Connection connection) throws Exception {
    String sql = "SELECT name FROM sqlite_master WHERE name='board'";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    ResultSet resultSet = preparedStatement.executeQuery();
    boolean result = false;
    while (resultSet.next()) {
      result = true;
    }
    return result;
  }

  /*
   * Creates table board if it does not exist.
   */
  public boolean createTable(Connection connection) throws Exception {
    String sql = "CREATE TABLE IF NOT EXISTS board(id INTEGER PRIMARY KEY, object BLOB NOT NULL);";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    int result = preparedStatement.executeUpdate();
    if(result == 1){
      return true;
    }
    return false;
  }

  /*
   * Method for Creating or Updating new Boards
   * Idea based on Array Index
   */
  public boolean createOrUpdateBoard(int id, BoardData object) {
    String sql = "INSERT OR REPLACE INTO board(id, object) VALUES(?, ?)";
    boolean result = false;

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out;

    //Closes Connection
    try (Connection conn = connect()) {
      out = new ObjectOutputStream(bos);
      out.writeObject(object);
      out.flush();
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      preparedStatement.setBytes(2, bos.toByteArray());
      int executeUpdate = preparedStatement.executeUpdate();
      if (executeUpdate == 1) {
        result = true;
      }
    } catch (SQLException e) {
      System.out.println("ERROR: " + e.getMessage());
      result = false;
    } catch (IOException io) {
      System.out.println("IO ERROR: " + io.getMessage());
    } finally {
      try {
        bos.close();
      } catch (IOException ex) {
        System.out.println("IOException: " + ex);
      }
    }
    return result;
  }

  /*
   * Method for Creating or Updating new Boards
   */
  public ArrayList<BoardData> getBoards() {
    String sql = "SELECT * FROM board";

    ResultSet resultSet;
    ArrayList<byte[]> list = new ArrayList<>();
    int count = 0;
    //Closes Connection
    try (Connection conn = connect()) {
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        list.add(resultSet.getBytes(2));
        count++;
      }
    } catch (SQLException e) {
      System.out.println("ERROR:" + e.getMessage());
    }

    ArrayList<BoardData> result = new ArrayList<>();

    for (byte[] bytes : list) {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInput in = null;
      try {
        in = new ObjectInputStream(bis);
        BoardData board = (BoardData) in.readObject();
        result.add(board);
      } catch (ClassNotFoundException cl) {
        System.out.println("ClassNotFound:" + cl);
      } catch (IOException io) {
        System.out.println("IOException" + io);
      } finally {
        try {
          if (in != null) {
            in.close();
          }
        } catch (IOException ex) {
          // ignore close exception
        }
      }
    }
    return result;
  }

  public static void main(String[] args) {

    DatabaseManager databaseManager = new DatabaseManager();
//
//    System.out.println("---- Create Or Update application.model.Board  1 -----");
//    boolean result1 = databaseManager.createOrUpdateBoard(1, new BoardData("Fred"));
//    System.out.println(result1);
//
//    System.out.println("---- Create Or Update application.model.Board  2 -----");
//    boolean result2 = databaseManager.createOrUpdateBoard(2, new BoardData("Jeff"));
//    System.out.println(result2);
//
//    System.out.println("---- GetBoards ----");
//    ArrayList<BoardData> boardDatas = databaseManager.getBoards();
//    ArrayList<ColumnData> columnDatas = new ArrayList<>();
//
//    List<CardData> cardsData = new ArrayList<>();

    List<String> strings = new ArrayList<>();
    strings.add("check1");
    strings.add("check2");

//    columnDatas.add(new ColumnData("Jeff", cardsData));
////    cardsData.add(new BugData("Bug", "crap", "1 2 3 "));
////    cardsData.add(new CheckListData("CheckList", strings));
////    cardsData.add(new EventData("Event", "Event Description", "21313213123"));
////    cardsData.add(new SimpleData("Simple", "Simple card"));
////    cardsData.add(new StoryData("Story", "story description", 1, "requirements"));
////    boardDatas.add(new BoardData("new", columnDatas));
////
////    ArrayList<Board> boards = new ArrayList<>();
////    for (BoardData boardData : boardDatas) {
////      boards.add(Board.convertToBoard(boardData));
////      System.out.println("application.model.Board: " + boardData.toString());
////    }
////    System.out.println(boards);
////
    Board board = new Board("Board");

    List<Card> cards = new ArrayList<>();

    board.columnsListProperty().add(new Column("Columns", cards));

    cards.add(new Bug("Bug 1", "B 1", "steps 1"));
    cards.add(new CheckList("Checklist 1", "d1", strings));
    cards.add(new Event("Event 1", "D 1", "s213132"));
    cards.add(new Story("story 1", "d 1", 2, "requirements 1"));

    System.out.print("Board: " + board);

    BoardData boardData2 = BoardData.convertToBoardData(board);

    databaseManager.createOrUpdateBoard(1, boardData2);

    List<BoardData> boardData = databaseManager.getBoards();

    Board result = Board.convertToBoard(boardData.get(0));

    System.out.print("Finish");

  }
}


