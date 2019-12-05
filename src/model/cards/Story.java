package model.cards;

import db.cards.StoryData;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Story extends Card {

  private SimpleStringProperty description;

  private SimpleIntegerProperty storyPoints;

  private SimpleStringProperty requirements;

  public Story(String title, String description, int storyPoints, String requirements) {
    super(title);
    this.description = new SimpleStringProperty(description);
    this.storyPoints = new SimpleIntegerProperty(storyPoints);
    this.requirements = new SimpleStringProperty(requirements);
  }

  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }

  public SimpleIntegerProperty storyPointsProperty(){
    return this.storyPoints;
  }

  public SimpleStringProperty requirementsProperty(){
    return this.requirements;
  }

  public static Card convertToStory(StoryData input){
    return new Story(input.title(), input.getDescription(), input.getStoryPoints(), input.getRequirements());
  }

}
