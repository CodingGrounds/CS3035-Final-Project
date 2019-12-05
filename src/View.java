import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
/**
 * This class represents a view for the application
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 *
 */
public class View extends Pane {
	
	public View() {
		// Listeners for changes in column list
		Main.model.columnListProperty().addListener(new ListChangeListener<Column>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Column> c) {
				draw();
			}
		});
		
		// Listeners for changes in card list of each column
		for (Column col : Main.model.columnListProperty()) {
			col.cardListProperty().addListener(new ListChangeListener<Card>() {
				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends Card> c) {
					draw();
				}
			});
		}

		// Initial drawing
		draw();
	}

	public void draw() {
		this.getChildren().clear();
		this.setPrefHeight(900);
		
		// Container
		HBox h = new HBox();
		h.setSpacing(20);
		h.setPrefWidth(1600);
		h.setPrefHeight(900);
		h.setAlignment(Pos.CENTER);
		
		// Generate Columns	and assign model.cards
		for (Column col : Main.model.columnListProperty()) {
			col.getChildren().clear();
			
			// Active columns can have model.cards added to them
			// They are represented in a different colour
			if (col.isActive()) {
				col.setStyle("-fx-background-color: slateblue;");
				
				// If there are model.cards associated with the column (contained in that column's card list)
				// then add those model.cards within the column
				if (col.cardListProperty().getSize() > 0) {
					for (Card c : col.cardListProperty()) {
						col.getChildren().add(c);
					}
				}
				
				// Display "Add Card" option
				Label addCard = new Label("+");
				addCard.setPrefHeight(45);
				addCard.setPrefWidth(45);
				addCard.setStyle("-fx-background-color: lightblue;-fx-text-fill: black;-fx-font-size: 24px;-fx-padding: 10;-fx-cursor: hand");
				addCard.setAlignment(Pos.TOP_CENTER);
				addCard.setOnMouseClicked(event -> {
					col.addCard(new Card(col));
					draw();
				});
				col.getChildren().add(addCard);
			} else {
				col.setStyle("-fx-background-color: lightgrey;");
				
				// Element used to activate a column
				Label activateCol = new Label("Activate Column");
				activateCol.setPrefHeight(50);
				activateCol.setPrefWidth(250);
				activateCol.setStyle("-fx-background-color: lightblue;-fx-text-fill: black;-fx-font-size: 18px;-fx-padding: 10;-fx-cursor: hand");
				activateCol.setAlignment(Pos.BASELINE_CENTER);
				activateCol.setOnMouseClicked(event -> {
					col.setActive(true);
					draw();
				});
				col.getChildren().add(activateCol);
			}

			h.getChildren().add(col);
    	}
		
		this.getChildren().add(h);
		
	}

}
