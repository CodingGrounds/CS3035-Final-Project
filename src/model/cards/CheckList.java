package model.cards;

import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckList extends Card {

  private SimpleListProperty<String> checkList;

  public CheckList(String title, List<String> list) {
    super(title);
    ObservableList<String> observableList = FXCollections.observableArrayList(list);
    this.checkList = new SimpleListProperty<String>(observableList);
  }

  private SimpleListProperty<String> checkListProperty(){
    return this.checkList;
  }

}
