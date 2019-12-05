package application;

import application.model.cards.Card;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CardDetailView extends Pane {

    application.model.cards.Card card;

    public CardDetailView(Card card) {
        this.card = card;

        draw();
    }

    public void draw() {
        Label title = new Label("Temp title");
        Label description = new Label("Temp description");

        getChildren().addAll(title, description);
    }
}
