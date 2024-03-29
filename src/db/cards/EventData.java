package db.cards;

import application.model.cards.Event;

public class EventData extends CardData {

  private String description;

  private String date;

  public EventData(String title, String description, String date) {
    super(title, description);
    this.description = description;
    this.date = date;
  }

  public String getDescription(){
    return this.description;
  }

  public String getDate(){
    return this.date;
  }

  public static CardData convertToEventData(Event input){

    return new EventData(input.titleProperty().get(), input.descriptionProperty().get(), input.dateProperty().get());
  }
}
