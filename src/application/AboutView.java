package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class AboutView extends Pane {
	
	public AboutView() {
		HBox h = new HBox();
		h.setPrefSize(1600, 900);
		h.setStyle("-fx-padding: 20;");
		h.setAlignment(Pos.TOP_LEFT);
		
		Image img;
		ImageView imgView = new ImageView();
		img = new Image("application/resources/logo.png");
		imgView.setImage(img);
		imgView.setPreserveRatio(true);
		imgView.setFitHeight(300);
		imgView.setFitWidth(300);

		VBox v = new VBox();
		v.setSpacing(50);
		
		Label title = new Label("Work In Progress");
		title.setStyle("-fx-font-size: 36;-fx-font-weight: bold;");	
		
		Label members = new Label("Team Members:\n\nJason Cleveland\nJoshua Laver\nJata Maccabe\nAnthony Tomarchio");
		members.setStyle("-fx-font-size: 20;");
		
		Button close = new Button("Close");
		close.setAlignment(Pos.CENTER);
		
		v.getChildren().addAll(title, imgView, members, close);
		h.getChildren().add(v);
		this.getChildren().add(h);
		
		close.setOnAction(e -> {
			Main.mainScene.setRoot(new BoardView(Main.interactiveModel.getCurrentBoard()));
		});
	}

}
