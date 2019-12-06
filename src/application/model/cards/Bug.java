package application.model.cards;

import db.cards.BugData;
import javafx.beans.property.SimpleStringProperty;

public class Bug extends Card {

  private SimpleStringProperty steps;

  public Bug(String title, String description, String steps) {
    super(title, description);
    this.steps = new SimpleStringProperty(steps);
  }

  public SimpleStringProperty stepsProperty() {
    return this.steps;
  }

  public void setStepsProperty(String steps) {
    this.steps.set(steps);
  }

  public static Card convertToBug(BugData input) {
    return new Bug(input.title(), input.getDescription(), input.getSteps());
  }

}
