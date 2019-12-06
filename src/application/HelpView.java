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
		h.setStyle("-fx-padding: 20;");
		h.setAlignment(Pos.TOP_LEFT);
		
		VBox v = new VBox();
		v.setSpacing(50);
		
		Label title = new Label("Help");
		title.setStyle("-fx-font-size: 36;-fx-font-weight: bold;");

		VBox instructionsContainer = new VBox();
		Label instructions = new Label("Work In Progress is an application designed to help you track your tasks and organize them within lists.");
		instructions.setStyle("-fx-font-size: 20;");
		Label add = new Label("Click on the '+' button to add columns and cards.");
		add.setStyle("-fx-font-size: 20;");
		Label edit = new Label("Left-Click on a card to view details and edit.");
		edit.setStyle("-fx-font-size: 20;");
		Label delete = new Label("Right-Click on a card or checkbox in checkbox card to delete it.");
		delete.setStyle("-fx-font-size: 20;");
		instructionsContainer.getChildren().addAll(instructions, add, edit, delete);
		
		Button close = new Button("Close");
		close.setAlignment(Pos.CENTER);
		
		v.getChildren().addAll(title, instructionsContainer, close);
		h.getChildren().add(v);
		getChildren().add(h);
		
		close.setOnAction(e -> Main.mainScene.setRoot(new BoardView(Main.interactiveModel.getCurrentBoard())));
	}
}
