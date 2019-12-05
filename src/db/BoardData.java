package db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Board;
import model.Column;

public class BoardData implements Serializable {

  private String name;

  private List<ColumnData> columns;

  public BoardData(String name){
    this.name = name;
    this.columns = new ArrayList<>();
  }

  public BoardData(String name, List<ColumnData> columns){
    this.name = name;
    this.columns = columns;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<ColumnData> getColumns() {
    return columns;
  }

  public void setColumns(List<ColumnData> columns) {
    this.columns = columns;
  }

  public static BoardData convertToBoardData(Board input){
    List<Column> columns = input.columnsListProperty().get();
    Column[] array = new Column[columns.size()];
    array = columns.toArray(array);
    List<Column> list = Arrays.asList(array);

    List<ColumnData> columnDatas = new ArrayList<>();
    System.out.println("BoardData");
    for(Column column: list){
      columnDatas.add(ColumnData.convertToColumnData(column));
    }
    return new BoardData(input.getName(), columnDatas);
  }
}
