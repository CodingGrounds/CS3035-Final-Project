package model.cards;

import db.cards.BugData;
import javafx.beans.property.SimpleStringProperty;

public class Bug extends Card {

  private SimpleStringProperty description;

  private SimpleStringProperty steps;

  public Bug(String title, String description, String steps) {
    super(title);
    this.description = new SimpleStringProperty(description);
    this.steps = new SimpleStringProperty(steps);
  }

  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }

  public SimpleStringProperty stepsProperty(){
    return this.steps;
  }

  public static Card convertToBug(BugData input){
    return new Bug(input.title(), input.getDescription(), input.getSteps());
  }

}
