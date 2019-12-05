package db.cards;

import java.util.Arrays;
import java.util.List;
import application.model.cards.CheckList;

public class CheckListData extends CardData {

  private List<String> checkList;

  public CheckListData(String title, List<String> list) {
    super(title);
    this.checkList = list;
  }

  public List<String> getCheckList(){
    return this.checkList;
  }


  public static CardData convertToCheckListData(CheckList input){
    List<String> strings = input.checkListProperty().get();
    String[] temp = new String[strings.size()];
    temp = strings.toArray(temp);
    List<String> result = Arrays.asList(temp);
    return new CheckListData(input.titleProperty().get(), result);
  }
}
