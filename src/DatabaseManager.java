import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManager {
  public static Connection Connector() {
    Connection connection = null;
    try{
      Class.forName("org.sqlite.JDBC");
      connection  = DriverManager.getConnection("jdbc:sqlite:todo");
    } catch(Exception e){
      System.out.println(e);
    }
    return connection;
  }





  public static void main(String []args) throws Exception{
    Connection connection = Connector();
    System.out.println(connection);


    System.out.println("Show Tables");

    DatabaseMetaData metaData = connection.getMetaData();

    Statement statement = connection.createStatement();

//    ResultSet set1 = statement.executeQuery("insert")

    ResultSet set = statement.executeQuery("select * from board");


    while(set.next())
      System.out.println(set.getString("object"));

    ResultSet tables = metaData.getTables(null, null, "%", new String[] {"TABLE"});
    System.out.println("Output");

    while (tables.next()) {
      System.out.println(tables.getString("TABLE_NAME"));
    }
  }
}


