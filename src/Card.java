import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class Card extends VBox {
	
	private final Column parent;

	public Card(Column parent) {
		this.parent = parent;
		
		Label header = new Label("Header");
		header.setPrefHeight(40);
		header.setPrefWidth(300);
		header.setStyle("-fx-background-color: SteelBlue;-fx-text-fill: white");
		header.setAlignment(Pos.CENTER);
		
		Label cardContent = new Label("Content here.");
		cardContent.setPrefHeight(150);
		cardContent.setPrefWidth(300);
		cardContent.setStyle("-fx-background-color: lightblue;-fx-text-fill: black;-fx-padding: 10");
		cardContent.setAlignment(Pos.TOP_LEFT);
		
		this.getChildren().addAll(header,cardContent);
		
		/**
		 * Right-click will remove the card
		 * this can be changed to another control key
		 */
		this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                del(this.parent);
                Main.view.draw();
            }
        });
	}
	
	public void add(Column c) {
		c.addCard(this);
	}
	
	public void del(Column c) {
		c.delCard(this);
	}

}
