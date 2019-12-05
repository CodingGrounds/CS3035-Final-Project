package db.cards;

import java.io.Serializable;
/**
 * This class represents the application.Card Object which has a title
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public abstract class CardData implements Serializable {

  private String title;

  public CardData(String title) {
    this.title = title;
  }

  public String title() {
    return title;
  }

}
