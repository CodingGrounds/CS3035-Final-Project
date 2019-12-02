import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class Column extends VBox {
	private SimpleListProperty<Card> cardListProperty;
	private boolean isActive;
	
	public Column (Boolean isActive) {
		this.isActive = isActive;
		
		// List of cards that currently belong to this column
		ArrayList<Card> list = new ArrayList<Card>();
		ObservableList<Card> observableList = (ObservableList<Card>) FXCollections.observableArrayList(list);
		cardListProperty = new SimpleListProperty<Card>(observableList);

		// Styling
		this.setPrefWidth(300);
		this.setSpacing(20);
		this.setAlignment(Pos.TOP_CENTER);
		this.setPadding(new Insets(10, 0, 0, 0));
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
	
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return this.isActive;
	}

}
