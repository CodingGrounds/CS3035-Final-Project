package db;

import db.cards.BugData;
import db.cards.CardData;
import db.cards.CheckListData;
import db.cards.EventData;
import db.cards.SimpleData;
import db.cards.StoryData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Column;
import model.cards.Bug;
import model.cards.Card;
import model.cards.CheckList;
import model.cards.Event;
import model.cards.Simple;
import model.cards.Story;

public class ColumnData implements Serializable {

  private String name;

  private List<CardData> cards;

  public ColumnData(String name, List<CardData> cards) {
    this.name = name;
    this.cards = cards;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CardData> getCards() {
    return cards;
  }

  public void setCards(List<CardData> cards) {
    this.cards = cards;
  }

  public static ColumnData convertToColumnData(Column input){
    List<CardData> cardDatas = new ArrayList<>();


    for(Card card: input.cardProperty()){
      if(card instanceof Bug){
        System.out.println("Bug Class");
        cardDatas.add(BugData.convertToBugData((Bug)card));
      }
      else if(card instanceof CheckList){
        System.out.println("CheckList Class");
        cardDatas.add(CheckListData.convertToCheckListData((CheckList)card));
      }
      else if(card instanceof Event){
        System.out.println("Event Class");
        cardDatas.add(EventData.convertToEventData((Event) card));
      }
      else if(card instanceof Simple){
        System.out.println("Simple Class");
        cardDatas.add(SimpleData.convertToSimpleData((Simple) card));
      }
      else if(card instanceof Story){
        System.out.println("Story Class");
        cardDatas.add(StoryData.convertToStoryData((Story) card));
      }
    }
    return new ColumnData(input.nameProperty().get(), cardDatas);
  }
}
