package db.cards;

import java.util.Arrays;
import java.util.List;

import application.model.CheckBoxData;
import application.model.cards.CheckList;

public class CheckListData extends CardData {

  private List<CheckBoxData> checkList;

  public CheckListData(String title, String description, List<CheckBoxData> list) {
    super(title, description);
    this.checkList = list;
  }

  public List<CheckBoxData> getCheckList(){
    return this.checkList;
  }


  public static CardData convertToCheckListData(CheckList input){
    List<CheckBoxData> data = input.checkListProperty().get();
    CheckBoxData[] temp = new CheckBoxData[data.size()];
    temp = data.toArray(temp);
    List<CheckBoxData> result = Arrays.asList(temp);
    return new CheckListData(input.titleProperty().get(), input.descriptionProperty().get(), result);
  }
}
