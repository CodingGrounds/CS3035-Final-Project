package db.cards;

import model.cards.Bug;

public class BugData extends CardData {

  private String description;

  private String steps;

  public BugData(String title, String description, String steps) {
    super(title);
    this.description = description;
    this.steps = steps;
  }

  public String getDescription(){
    return this.description;
  }

  public String getSteps(){
    return this.steps;
  }

  public static CardData convertToBugData(Bug input){
    return new BugData(input.titleProperty().get(), input.descriptionProperty().get(), input.stepsProperty().get());
  }

}
