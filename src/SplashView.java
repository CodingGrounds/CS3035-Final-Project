import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Class representing the splash view containing navigation buttons
 *
 * @author Jason Cleveland
 */
public class SplashView extends BorderPane {

    public SplashView() {
        Label appTitle = new Label("Discount Trello");
        appTitle.setStyle("-fx-font-size: 40px");
        setAlignment(appTitle, Pos.CENTER);

        Label newBoard = new Label("Add Board");
        newBoard.setStyle("-fx-font-size: 24px; -fx-cursor: hand");
        setAlignment(newBoard, Pos.CENTER);
        newBoard.setPadding(new Insets(0,0,100,0));
        newBoard.setOnMouseClicked(e -> {
            // Sets the scene to a new board
            Main.mainScene.setRoot(Main.boardView);
        });

        // TODO: Center these
        VBox existingBoards = new VBox();
        Label existingBoardTitle = new Label("Existing Boards");
        existingBoardTitle.setAlignment(Pos.CENTER);
        setAlignment(existingBoardTitle, Pos.CENTER);
        existingBoardTitle.setStyle("-fx-font-size: 24px");
        existingBoards.getChildren().addAll(existingBoardTitle);

        // Existing Boards
        ArrayList<Label> existingBoardList = new ArrayList<Label>();

        // TODO: SQL query to fetch any saved boards

        // Suggestion: SELECT query will return rows, iterate through those rows
        // and create a new Label (or other element) for each

        // NOTE: when these elements are clicked on, they need to load our model
        // with their information that was stored in the DB (# of cols, cards in each col)

        // If no saved boards, add default message
        Label defaultMsg = new Label("No existing boards");
        defaultMsg.setAlignment(Pos.CENTER);
        setAlignment(defaultMsg, Pos.CENTER);
        existingBoards.getChildren().add(defaultMsg);
        // TODO: End todo

        setStyle("-fx-background-color: lightblue; -fx-text-fill: black; -fx-padding: 10");

        setTop(appTitle);
        setCenter(existingBoards);
        setBottom(newBoard);
    }
}
