package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.Card;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CardView extends VBox {

    private Board board;
    private Column column;
    private Card card;

    public CardView(Board board, Column column, Card card) {
        this.board = board;
        this.column = column;
        this.card = card;

        setOnMouseClicked(event -> Main.mainScene.setRoot(new CardDetailView(board, column, card)));

        draw();
    }

    public void draw() {
        setPrefWidth(200);
        setPrefHeight(50);
        setMinWidth(150);
        setMinHeight(50);

        Label title = new Label(card.titleProperty().get());
        Label description = new Label(card.descriptionProperty().get());
        getChildren().addAll(title, description);
    }
}
