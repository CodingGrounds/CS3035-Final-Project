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

  private String description;

  public CardData(String title, String description) {
    this.title = title;
    this.description = description;
  }

  public String title() {
    return this.title;
  }

  public String getDescription (){
    return this.description;
  }

}
