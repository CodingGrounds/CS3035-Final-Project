package application.model;

import db.ColumnData;
import db.cards.BugData;
import db.cards.CardData;
import db.cards.CheckListData;
import db.cards.EventData;
import db.cards.SimpleData;
import db.cards.StoryData;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.model.cards.Bug;
import application.model.cards.Card;
import application.model.cards.CheckList;
import application.model.cards.Event;
import application.model.cards.Simple;
import application.model.cards.Story;

/**
 * This class represents the application.Column Object which has a name and a List of Cards
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Column {

  /**
   * Name of the application.Column
   */
  private SimpleStringProperty name;

  /**
   * List of cards per application.Column
   */
  private SimpleListProperty<Card> cards;

  /**
   * Constructor for application.Column Input: Names, List<Cards>
   */
  public Column(String name, List<Card> cards) {
    this.name = new SimpleStringProperty(name);
    ObservableList<Card> observableList = FXCollections.observableList(cards);
    this.cards = new SimpleListProperty<>(observableList);
  }

  /**
   * Constructor for application.Column Input: Name
   */
  public Column(String name) {
    this.name = new SimpleStringProperty(name);
    ArrayList<Card> arrayList = new ArrayList<>();
    ObservableList<Card> observableList = FXCollections.observableList(arrayList);
    this.cards = new SimpleListProperty<>(observableList);
  }

  /**
   * Method for getting list of cards
   *
   * @return SimpleListProperty<application.Card>
   */
  public SimpleListProperty<Card> cardsProperty() {
    return this.cards;
  }

  /**
   * Method for getting name property
   *
   * @return SimpleStringProperty
   */
  public SimpleStringProperty nameProperty() {
    return this.name;
  }

  public static Column convertToColumn(ColumnData input){
    List<Card> cards = new ArrayList<>();

    for(CardData cardData: input.getCards()){
      if(cardData instanceof BugData){
        System.out.println("Bug Class");
        cards.add(Bug.convertToBug((BugData)cardData));
      }
      else if(cardData instanceof CheckListData){
        System.out.println("CheckList Class");
        cards.add(CheckList.convertToCheckList((CheckListData)cardData));
      }
      else if(cardData instanceof EventData){
        System.out.println("Event Class");
        cards.add(Event.convertToEvent((EventData) cardData));
      }
      else if(cardData instanceof SimpleData){
        System.out.println("Simple Class");
        cards.add(Simple.convertToSimple((SimpleData) cardData));
      }
      else if(cardData instanceof StoryData){
        System.out.println("Story Class");
        cards.add(Story.convertToStory((StoryData) cardData));
      }
    }
    return new Column(input.getName(), cards);
  }

}
