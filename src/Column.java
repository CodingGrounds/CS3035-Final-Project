import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Column {
	private SimpleListProperty<Card> cardListProperty;
	
	public Column () {
		
		// List of cards that currently belong to this column
		ArrayList<Card> list = new ArrayList<Card>();
		ObservableList<Card> observableList = (ObservableList<Card>) FXCollections.observableArrayList(list);
		cardListProperty = new SimpleListProperty<Card>(observableList);
	}
	
	public SimpleListProperty<Card> cardListProperty(){
		return cardListProperty;
	}
	
	public void addCard(Card newCard) {
		cardListProperty.add(newCard);	
	}
	
	public void delCard(Card card) {
		cardListProperty.remove(card);
	}
}
