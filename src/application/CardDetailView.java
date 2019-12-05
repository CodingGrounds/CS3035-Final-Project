package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.Card;
import application.model.cards.CardType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CardDetailView extends BorderPane {

    private Board board;
    private Column column;
    private Card card;

    private CardType selectedCardType;

    private boolean isNew;

    /**
     * Constructor called without and existing card. Creates a new card
     */
    public CardDetailView(Board board, Column column) {
        this.board = board;
        this.column = column;
        isNew = true;

        draw();
    }

    /**
     * Constructor called with an existing card. Enables viewing and editing
     *
     * @param card object containing data about the card
     */
    public CardDetailView(Board board, Column column, Card card) {
        this.board = board;
        this.column = column;
        this.card = card;
        isNew = false;

        draw();
    }

    private void saveCard() {
        // TODO: Save card to model
        if (isNew) {
            this.column.cardsProperty().add(card);
        } else {

        }

        Main.mainScene.setRoot(new BoardView(board));
    }

    public void draw() {
        String titleText;
        String saveButtonText;
        if (this.isNew) {
            titleText = "Create New Card";
            saveButtonText = "Add";
        } else {
            titleText = "Edit Card";
            saveButtonText = "Save";
        }

        Label title = new Label(titleText);
        setAlignment(title, Pos.CENTER);

        // Create interaction buttons
        Button saveButton = new Button(saveButtonText);
        saveButton.setDefaultButton(true);
        saveButton.setPrefWidth(100);
        saveButton.setOnMouseClicked(event -> saveCard());

        Button cancelButton = new Button("Cancel");
        cancelButton.setCancelButton(true);
        cancelButton.setPrefWidth(100);
        cancelButton.setOnMouseClicked(event -> saveCard());

        HBox buttonContainer = new HBox();
        buttonContainer.setSpacing(10);
        buttonContainer.setPadding(new Insets(20));
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.getChildren().addAll(saveButton, cancelButton);

        // Create input fields
        Label typeLabel = new Label("Type:");
        Label titleLabel = new Label("Title");
        Label descriptionLabel = new Label("Description");
        Label stepsLabel = new Label("Steps to Reproduce");

        ComboBox<String> typeInput = new ComboBox<>();
        typeInput.getItems().addAll("Bug", "Checklist", "Event", "Story", "Simple");
        typeInput.setOnAction(event -> {
            switch (typeInput.getValue()) {
                case "Bug":
                    selectedCardType = CardType.BUG;
                    break;
                case "Checklist":
                    selectedCardType = CardType.CHECKLIST;
                    break;
                case "Event":
                    selectedCardType = CardType.EVENT;
                    break;
                case "Simple":
                    selectedCardType = CardType.SIMPLE;
                    break;
                case "Story":
                    selectedCardType = CardType.STORY;
                    break;
            }
            draw();
        });
        TextField titleInput = new TextField();
        TextArea descriptionInput = new TextArea();
        TextArea stepsInput = new TextArea();
        TextField checklistInput = new TextField();
        DatePicker dateInput = new DatePicker();
        Spinner<Integer> storyPointsInput = new Spinner<>(0, 10, 0, 1);
        TextArea requirements = new TextArea();

        GridPane inputContainer = new GridPane();
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setVgap(10);
        inputContainer.setHgap(5);
        inputContainer.addRow(0, typeLabel, typeInput);
        inputContainer.addRow(1, titleLabel, titleInput);
        if (selectedCardType == CardType.BUG) {
            inputContainer.addRow(2, descriptionLabel, descriptionInput);
        }

        if (selectedCardType == CardType.BUG) {

        }

        setTop(title);
        setCenter(inputContainer);
        setBottom(buttonContainer);
    }
}
