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
		h.setStyle("-fx-padding: 20;");
		h.setAlignment(Pos.TOP_LEFT);

		Image img;
		ImageView imgView = new ImageView();
		img = new Image("application/resources/logo.png");
		imgView.setImage(img);
		imgView.setPreserveRatio(true);
		imgView.setFitHeight(200);
		imgView.setFitWidth(200);

		VBox v = new VBox();
		v.setSpacing(30);
		
		Label title = new Label("Work In Progress");
		title.setStyle("-fx-font-size: 36;-fx-font-weight: bold;");

		VBox membersContainer = new VBox();
		Label members = new Label("Team Members:");
		members.setStyle("-fx-font-size: 30;");
		Label jason_cleveland = new Label("Jason Cleveland");
		jason_cleveland.setStyle("-fx-font-size: 20;");
		Label joshua_laver = new Label("Joshua Laver");
		joshua_laver.setStyle("-fx-font-size: 20;");
		Label jata_maccabe = new Label("Jata Maccabe");
		jata_maccabe.setStyle("-fx-font-size: 20;");
		Label anthony_tomarchio = new Label("Anthony Tomarchio");
		anthony_tomarchio.setStyle("-fx-font-size: 20;");
		membersContainer.getChildren().addAll(members, jason_cleveland, joshua_laver, jata_maccabe, anthony_tomarchio);
		
		Button close = new Button("Close");
		close.setAlignment(Pos.CENTER);
		
		v.getChildren().addAll(title, imgView, membersContainer, close);
		h.getChildren().add(v);
		this.getChildren().add(h);
		
		close.setOnAction(e -> Main.mainScene.setRoot(new BoardView(Main.interactiveModel.getCurrentBoard())));
	}

}
