import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ColumnView extends ScrollPane {

    Column column;
    VBox cardsContainer = new VBox();

    public ColumnView(Column column) {
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

        for (Card card: column.cardListProperty()) {
            cardsContainer.getChildren().add(new CardView(card));
        }

        HBox addCardContainer = new HBox();
        addCardContainer.setPadding(new Insets(20));
        addCardContainer.setAlignment(Pos.CENTER);
        addCardContainer.setMinHeight(50);
        Label addCard = new Label("+");
        addCard.setOnMouseClicked(event -> {
            column.addCard(new Card(column));

        });
        addCardContainer.getChildren().add(addCard);
        cardsContainer.getChildren().add(addCardContainer);

        setContent(this.cardsContainer);
    }
}
