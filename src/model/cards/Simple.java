package model.cards;

import db.cards.SimpleData;
import javafx.beans.property.SimpleStringProperty;

public class Simple extends Card {

  private SimpleStringProperty description;

  public Simple(String title, String description) {
    super(title);
    this.description = new SimpleStringProperty(description);
  }

  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }

  public static Card convertToSimple(SimpleData input){
    return new Simple(input.title(), input.getDescription());
  }


}
