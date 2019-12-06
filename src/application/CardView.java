package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.Bug;
import application.model.cards.Card;
import application.model.cards.CheckList;
import application.model.cards.Event;
import application.model.cards.Story;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sun.java2d.pipe.SpanShapeRenderer.Simple;

public class CardView extends VBox {

    private Board board;
    private Column column;
    private Card card;

    public CardView(Board board, Column column, Card card) {
        this.board = board;
        this.column = column;
        this.card = card;

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                Main.mainScene.setRoot(new CardDetailView(board, column, card));
            }
            else if (event.getButton() == MouseButton.SECONDARY) {
                column.cardsProperty().remove(this.card);
            }
        });


        draw();
    }

    public void draw() {
//        setPrefWidth(200);
//        setPrefHeight(50);
        setPrefWidth(200);
        setMaxWidth(250);
        setMinWidth(150);
        setMinHeight(50);

        HBox hTitle = new HBox();
        hTitle.setAlignment(Pos.CENTER);

        Color colorTitle;
        if(card.getClass() == Story.class){
            colorTitle = Color.LIGHTBLUE;
        }
        else if(card.getClass() == Bug.class){
            colorTitle = Color.INDIANRED;
        }
        else if(card.getClass() == CheckList.class){
            colorTitle = Color.GREEN;
        }
        else {
            colorTitle = Color.ORANGE;
        }

        hTitle.setBackground(new Background(new BackgroundFill(colorTitle, new  CornerRadii(10), Insets.EMPTY)));
        hTitle.setPadding(new Insets(5));

        Label title = new Label(card.titleProperty().get());
        title.setFont(Font.font("Cambria", 18));
        title.setWrapText(true);

        HBox hDescription = new HBox();
        hDescription.setPadding(new Insets(15));
        hDescription.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(0, 0, 0, 0, false), Insets.EMPTY)));

        Label description = new Label(card.descriptionProperty().get());
        description.setWrapText(true);

        hTitle.getChildren().add(title);
        hDescription.getChildren().add(description);

        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(5,5,5,5, false), Insets.EMPTY)));
        this.setPadding(new Insets(5));

        getChildren().addAll(hTitle, hDescription);


        this.setStyle("-fx-cursor: hand");

    }
}
