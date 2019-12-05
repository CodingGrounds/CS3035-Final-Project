package model.cards;

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

}
