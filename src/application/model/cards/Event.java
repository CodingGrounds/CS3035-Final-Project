package application.model.cards;

import db.cards.EventData;
import javafx.beans.property.SimpleStringProperty;

public class Event extends Card {

  private SimpleStringProperty date;

  public Event(String title, String description, String date) {
    super(title, description);
    this.date = new SimpleStringProperty(date);
  }

  public SimpleStringProperty dateProperty(){
    return this.date;
  }

  public static Card convertToEvent(EventData input){
    return new Event(input.title(), input.getDescription(), input.getDate());
  }


}
