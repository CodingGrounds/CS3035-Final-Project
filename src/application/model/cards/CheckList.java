package application.model.cards;

import db.cards.CheckListData;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckList extends Card {

  private SimpleListProperty<String> checkList;

  public CheckList(String title, List<String> list) {
    super(title);
    ObservableList<String> observableList = FXCollections.observableArrayList(list);
    this.checkList = new SimpleListProperty<>(observableList);
  }

  public SimpleListProperty<String> checkListProperty(){
    return this.checkList;
  }

  public static Card convertToCheckList(CheckListData input){
    return new CheckList(input.title(), input.getCheckList());
  }

}
