package application;

import application.model.Board;
import application.model.Column;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;

public class ColumnView extends ScrollPane {

    private Board board;
    private Column column;
    private VBox cardsContainer = new VBox();

    public ColumnView(Board board, Column column) {
        this.board = board;
        this.column = column;
        setHbarPolicy(ScrollBarPolicy.NEVER);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        setFitToHeight(true);
        setFitToWidth(true);

        draw();
    }

    public void draw() {
        cardsContainer.setSpacing(20);
        cardsContainer.setPadding(new Insets(10));

        setPrefHeight(600);
        setMinWidth(200);
        setMinHeight(400);

        cardsContainer.getChildren().clear();
        for (application.model.cards.Card card: column.cardsProperty()) {
            cardsContainer.getChildren().add(new CardView(board, column, card));
        }

        IconFontFX.register(FontAwesome.getIconFont());

        IconNode iconNode = new IconNode(FontAwesome.PLUS_CIRCLE);
        iconNode.setIconSize(30);
        iconNode.setFill(Color.DODGERBLUE);

        HBox addCardContainer = new HBox();
        addCardContainer.setPadding(new Insets(20));
        addCardContainer.setAlignment(Pos.CENTER);
        addCardContainer.setMinHeight(50);

        Label addCard = new Label();
        addCard.setGraphic(iconNode);
        addCard.setStyle("-fx-cursor: hand;");

        addCard.setOnMouseClicked(event -> {
            Main.mainScene.setRoot(new CardDetailView(board, column));
            draw();
        });
        addCardContainer.getChildren().add(addCard);
        cardsContainer.getChildren().add(addCardContainer);

        setContent(this.cardsContainer);
    }
}
