package application.model.cards;

import application.model.CheckBoxData;
import db.cards.CheckListData;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CheckList extends Card {

  private SimpleListProperty<CheckBoxData> checkList;

  public CheckList(String title, String description, List<CheckBoxData> list) {
    super(title, description);
    ObservableList<CheckBoxData> observableList = FXCollections.observableArrayList(list);
    this.checkList = new SimpleListProperty<>(observableList);
  }

  public SimpleListProperty<CheckBoxData> checkListProperty() {
    return this.checkList;
  }

  public void setCheckList(List<CheckBoxData> checkList) {
    this.checkList.clear();
    this.checkList.addAll(checkList);
  }

  public static Card convertToCheckList(CheckListData input) {
    return new CheckList(input.title(), input.getDescription(), input.getCheckList());
  }

}
