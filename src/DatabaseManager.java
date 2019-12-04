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
import java.util.Objects;

public class DatabaseManager {

  private Connection connect() {
    Connection connection = null;
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:todo");
    } catch (Exception e) {
      System.out.println(e);
    }
    return connection;
  }

  //TODO Create tables

  //TODO Object


  /*
   * Method for Creating or Updating new Boards
   * TODO Requires a Board Object
   */
  public boolean createOrUpdateBoard(int id, Board object) {
    String sql = "INSERT OR REPLACE INTO board(id, object) VALUES(?, ?)";
    boolean result = false;

    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutput out = null;

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
        System.out.println("Crap");
      }
    }
    return result;
  }


  /*
   * Method for Creating or Updating new Boards
   * TODO Requires a Board Object
   */
  public Board[] getBoards() {
    String sql = "select * from board";

    ResultSet resultSet = null;



    ArrayList<byte[]> list = new ArrayList<>();
    int count = 0;
    //Closes Connection
    try (Connection conn = connect()) {
      PreparedStatement preparedStatement = conn.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while(resultSet.next()){
        list.add(resultSet.getBytes(2));
        count++;
      }
    } catch (SQLException e) {
      System.out.println("ERROR:" + e.getMessage());
    }

    Board[] result = new Board[count];

    int listCounter = 0;
    for(byte[] bytes: list){
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInput in = null;
      try {
        in = new ObjectInputStream(bis);
        Board board  = (Board)in.readObject();
        result[listCounter++] = board;
      } catch(ClassNotFoundException cl){
        System.out.println(cl);
      }
      catch(IOException io){
        System.out.println(io);
      }
      finally {
        try {
          if (in != null) {
            in.close();
          }
        } catch (IOException ex) {
          // ignore close exception
        }
      }
    }
    //TODO Covert Blob to board
    return result;
  }


  public boolean addCard(Column column, Card card) throws Exception {

//     Statement statement =  .createStatement();

//     ResultSet resultSet = statement.

    return true;
  }


  public static void main(String[] args) {

    DatabaseManager databaseManager = new DatabaseManager();

    System.out.println("---- Create Or Update Board  1 -----");
    boolean result1 = databaseManager.createOrUpdateBoard(1, new Board("Fred"));
    System.out.println(result1);

    System.out.println("---- Create Or Update Board  2 -----");
    boolean result2 = databaseManager.createOrUpdateBoard(2, new Board("Jeff"));
    System.out.println(result2);

    System.out.println("---- GetBoards ----");
    Board[] boards =  databaseManager.getBoards();
    for(Board board: boards){
      System.out.println("Board: " + board.toString());
    }

  }
}


