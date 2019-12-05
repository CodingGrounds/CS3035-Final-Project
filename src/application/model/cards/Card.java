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

  public Card(String title) {
    this.title = new SimpleStringProperty(title);
  }

  public SimpleStringProperty titleProperty() {
    return title;
  }

}
