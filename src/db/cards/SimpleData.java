package db.cards;

import model.cards.Simple;

public class SimpleData extends CardData {

  private String description;

  public SimpleData(String title, String description) {
    super(title);
    this.description = description;
  }

  public String getDescription(){
    return this.description;
  }

  public static CardData convertToSimpleData(Simple input){

    return new SimpleData(input.titleProperty().get(), input.descriptionProperty().get());
  }
}

