package model.cards;


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

}
