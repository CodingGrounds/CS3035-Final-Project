package application.model.cards;

import db.cards.StoryData;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Story extends Card {

  private SimpleIntegerProperty storyPoints;

  private SimpleStringProperty requirements;

  public Story(String title, String description, int storyPoints, String requirements) {
    super(title, description);
    this.storyPoints = new SimpleIntegerProperty(storyPoints);
    this.requirements = new SimpleStringProperty(requirements);
  }

  public SimpleIntegerProperty storyPointsProperty(){
    return this.storyPoints;
  }

  public SimpleStringProperty requirementsProperty(){
    return this.requirements;
  }

  public void setStoryPoints(int storyPoints){
    this.storyPoints.set(storyPoints);
  }

  public void setRequirements(String requirements){
    this.requirements.set(requirements);
  }

  public static Card convertToStory(StoryData input){
    return new Story(input.title(), input.getDescription(), input.getStoryPoints(), input.getRequirements());
  }

}
