package application.model.cards;

import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents the application.Card Object which has a title
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public abstract class Card {

  private SimpleStringProperty title;

  private SimpleStringProperty description;

  public Card(String title, String description) {
    this.title = new SimpleStringProperty(title);
    this.description = new SimpleStringProperty(description);
  }

  public SimpleStringProperty titleProperty() {
    return this.title;
  }

  public SimpleStringProperty descriptionProperty(){
    return this.description;
  }

  public void setDescription(String description){
    this.description.set(description);
  }

  public void setTitle(String title){
    this.title.set(title);
  }
}

