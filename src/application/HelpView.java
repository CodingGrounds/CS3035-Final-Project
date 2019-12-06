package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HelpView extends Pane {

	public HelpView() {
		HBox h = new HBox();
		h.setPrefSize(1600, 900);
		h.setStyle("-fx-padding: 20;");
		h.setAlignment(Pos.TOP_LEFT);
		
		VBox v = new VBox();
		v.setSpacing(50);
		
		Label title = new Label("Help");
		title.setStyle("-fx-font-size: 36;-fx-font-weight: bold;");
		
		Label instructions = new Label("Work In Progress is an application designed to help you track your tasks and organize them within columns."
				+ "\n\nClick on the '+' to add columns and cards within the columns."
				+ "\n\nThere are different types of cards to represent different tasks. Click on a card to view/edit the card's details.");
		instructions.setStyle("-fx-font-size: 20;");
		
		Button close = new Button("Close");
		close.setAlignment(Pos.CENTER);
		
		v.getChildren().addAll(title, instructions, close);
		h.getChildren().add(v);
		this.getChildren().add(h);
		
		close.setOnAction(e -> {
			Main.mainScene.setRoot(new BoardView(Main.interactiveModel.getCurrentBoard()));
		});
	}
}
