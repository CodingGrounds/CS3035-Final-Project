package model.cards;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class represents the Card Object which has a title
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public abstract class Card implements Serializable {

  private SimpleStringProperty title;

  public Card(String title) {
    this.title = new SimpleStringProperty(title);
  }

  public SimpleStringProperty titleProperty() {
    return title;
  }

}