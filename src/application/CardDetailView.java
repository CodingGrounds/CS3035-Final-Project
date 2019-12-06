package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.*;
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
    private Card newCard;

    private ComboBox<String> typeInput;
    private TextField titleInput;
    private TextArea descriptionInput;
    private TextArea stepsInput;
    private TextField checklistInput;
    private DatePicker dateInput;
    private Spinner<Integer> storyPointsInput;
    private TextArea requirementsInput;

    private String selectedType;
    private CardType selectedCardType;

    private boolean isNew;

    /**
     * Constructor called without and existing card. Creates a new card
     */
    public CardDetailView(Board board, Column column) {
        this.board = board;
        this.column = column;
        isNew = true;

        titleInput = new TextField();
        descriptionInput = new TextArea();
        stepsInput = new TextArea();
        checklistInput = new TextField();
        dateInput = new DatePicker();
        storyPointsInput = new Spinner<>(0, 10, 0, 1);
        requirementsInput = new TextArea();

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

        titleInput = new TextField();
        descriptionInput = new TextArea();
        stepsInput = new TextArea();
        checklistInput = new TextField();
        dateInput = new DatePicker();
        storyPointsInput = new Spinner<>(0, 10, 0, 1);
        requirementsInput = new TextArea();

        draw();
    }

    private void typeChanged() {
        selectedType = typeInput.getValue();
        switch (selectedType) {
            case "Bug":
                selectedCardType = CardType.BUG;
                break;
            case "Checklist":
                selectedCardType = CardType.CHECKLIST;
                break;
            case "Event":
                selectedCardType = CardType.EVENT;
                break;
            case "Story":
                selectedCardType = CardType.STORY;
                break;
        }
        draw();
    }

    private void saveCard() {
        // TODO: Save card to model
        switch (selectedCardType) {
            case BUG:
                newCard = new Bug(
                        titleInput.getText(),
                        descriptionInput.getText(),
                        stepsInput.getText()
                );
                break;
            case CHECKLIST:
                // TODO:
                break;
            case EVENT:
                System.out.println(dateInput.getValue().toString());
                newCard = new Event(
                        titleInput.getText(),
                        descriptionInput.getText(),
                        dateInput.getValue().toString()
                );
                break;
            case STORY:
                newCard = new Story(
                        titleInput.getText(),
                        descriptionInput.getText(),
                        storyPointsInput.getValue(),
                        requirementsInput.getText()
                );
        }

        if (isNew) {
            this.column.cardsProperty().add(card);
        } else {
            // TODO:
            if (card.getClass() == Bug.class) {

            }
            else if (card.getClass() == CheckList.class) {

            }
            else if (card.getClass() == Event.class) {

            }
            else if (card.getClass() == Story.class) {

            }
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
        Label titleLabel = new Label("Title:");
        Label descriptionLabel = new Label("Description:");
        Label stepsLabel = new Label("Steps to Reproduce:");
        Label checklistLabel = new Label("Checklist:");
        Label dateLabel = new Label("Due Date:");
        Label storyPointsLabel = new Label("Story Points:");
        Label requirementsLabel = new Label("Requirements:");

        typeInput = new ComboBox<>();
        typeInput.getItems().addAll("Bug", "Checklist", "Event", "Story");
        typeInput.setOnAction(event -> typeChanged());
        typeInput.setValue(selectedType);

        GridPane inputContainer = new GridPane();
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setVgap(10);
        inputContainer.setHgap(5);
        inputContainer.addRow(0, typeLabel, typeInput);
        inputContainer.addRow(1, titleLabel, titleInput);
        inputContainer.addRow(2, descriptionLabel, descriptionInput);

        if (selectedCardType != null) {
            switch (selectedCardType) {
                case BUG:
                    inputContainer.addRow(3, stepsLabel, stepsInput);
                    break;
                case CHECKLIST:
                    inputContainer.addRow(3, checklistLabel, checklistInput);
                    break;
                case EVENT:
                    inputContainer.addRow(3, dateLabel, dateInput);
                    break;
                case STORY:
                    inputContainer.addRow(3, storyPointsLabel, storyPointsInput);
                    inputContainer.addRow(4, requirementsLabel, requirementsInput);
            }
        }

        setTop(title);
        setCenter(inputContainer);
        setBottom(buttonContainer);
    }
}
