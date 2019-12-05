package db.cards;

import application.model.cards.Story;

public class StoryData extends CardData {

  private String description;

  private int storyPoints;

  private String requirements;

  public StoryData(String title, String description, int storyPoints, String requirements) {
    super(title);
    this.description = description;
    this.storyPoints = storyPoints;
    this.requirements = requirements;
  }

  public String getDescription(){
    return this.description;
  }

  public int  getStoryPoints(){
    return this.storyPoints;
  }

  public String getRequirements(){
    return this.requirements;
  }


  public static StoryData convertToStoryData(Story input){
    return new StoryData(input.titleProperty().get(), input.descriptionProperty().get(),input.storyPointsProperty().get(), input.requirementsProperty().get());
  }

}
