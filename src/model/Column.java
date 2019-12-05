package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import model.cards.Card;

/**
 * This class represents the Column Object which has a name and a List of Cards
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Column implements Serializable {

  /**
   * Name of the Column
   */
  private transient SimpleStringProperty name;

  /**
   * List of cards per Column
   */
//  private SimpleListProperty<Card> cards;

  /**
   * Constructor for Column Input: Names, List<Cards>
   */
  public Column(SimpleStringProperty name, SimpleListProperty<Card> cards) {
    this.name = name;
//    this.cards = cards;
  }

  /**
   * Constructor for Column Input: Name
   */
  public Column(String name) {
    this.name = new SimpleStringProperty(name);
//    this.cards = new SimpleListProperty<>();
  }

  /**
   * Method for getting list of cards
   *
   * @return SimpleListProperty<Card>
   */
//  public SimpleListProperty<Card> cardProperty() {
//    return this.cards;
//  }

  /**
   * Method for getting name property
   *
   * @return SimpleStringProperty
   */
  public SimpleStringProperty nameProperty() {
    return this.name;
  }


  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
    s.writeObject(nameProperty());
//    s.writeObject();
  }



  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    String object  = (String) s.readObject();
    this.name = new SimpleStringProperty(object);
  }


}
