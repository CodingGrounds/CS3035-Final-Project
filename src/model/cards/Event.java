package model.cards;

import javafx.beans.property.SimpleStringProperty;

public class Event extends Card {


  private SimpleStringProperty description;

  private SimpleStringProperty date;

  public Event(String title, String description, String date) {
    super(title);
    this.description = new SimpleStringProperty(description);
    this.date = new SimpleStringProperty(date);
  }

  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }

  public SimpleStringProperty dateProperty(){
    return this.date;
  }

}
