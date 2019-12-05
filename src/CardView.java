import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CardView extends VBox {

    Card card;

    public CardView(Card card) {
        this.card = card;

        setOnMouseClicked(event -> Main.mainScene.setRoot(new CardDetailView(card)));

        draw();
    }

    public void draw() {
        setPrefWidth(200);
        setPrefHeight(50);
        setMinWidth(150);
        setMinHeight(50);

        Label title = new Label("Title");
        Label description = new Label("Description here.");

        getChildren().addAll(title, description);
    }
}
