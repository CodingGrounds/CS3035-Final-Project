import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;

public class Card extends VBox {
	
	private final Column parent;

	public Card(Column parent) {
		this.parent = parent;
		
		/**
		 * Right-click will remove the card
		 * this can be changed to another control key
		 */
		this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                del(this.parent);
                Main.boardView.draw();
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
