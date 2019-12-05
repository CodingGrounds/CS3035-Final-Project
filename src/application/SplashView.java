package application;

import application.model.Board;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
/**
 * Class representing the splash view containing navigation buttons
 *
 * @author Jason Cleveland
 */
public class SplashView extends BorderPane {

    private List<Board> boards;

    public SplashView() {
        Label appTitle = new Label("Discount Trello");
        appTitle.setStyle("-fx-font-size: 40px");
        setAlignment(appTitle, Pos.CENTER);

        Label newBoard = new Label("Add Board");
        newBoard.setStyle("-fx-font-size: 24px; -fx-cursor: hand");
        setAlignment(newBoard, Pos.CENTER);
        newBoard.setPadding(new Insets(50,0,50,0));


        this.boards = Main.model.boardsProperty().get();

        newBoard.setOnMouseClicked(e -> {
            // Sets the scene to a new board
            Board newModelBoard = new Board("temp");
            this.boards.add(newModelBoard);
            Main.mainScene.setRoot(new BoardView(newModelBoard));
        });

        // TODO: Center these
        VBox existingBoards = new VBox();
        existingBoards.setFillWidth(true);
        existingBoards.setAlignment(Pos.CENTER);
        Label existingBoardTitle = new Label("Existing Boards");
        existingBoardTitle.setAlignment(Pos.CENTER);
        existingBoardTitle.setStyle("-fx-font-size: 24px");
        existingBoards.getChildren().add(existingBoardTitle);

        // Existing Boards
        ArrayList<Label> existingBoardList = new ArrayList<Label>();


        for(Board board: boards){
            existingBoardList.add(new Label(board.getName()));
        }

        // TODO: SQL query to fetch any saved boards

        // Suggestion: SELECT query will return rows, iterate through those rows
        // and create a new Label (or other element) for each

        // NOTE: when these elements are clicked on, they need to load our application.model
        // with their information that was stored in the DB (# of cols, cards in each col)

        // If no saved boards, add default message
        if (existingBoardList.size() == 0) {
            Label defaultMsg = new Label("No existing boards");
            existingBoards.getChildren().add(defaultMsg);
        }
        // TODO: End todo

        ScrollPane existingBoardsContainer = new ScrollPane();
        existingBoardsContainer.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        existingBoardsContainer.setFitToWidth(true);
        existingBoardsContainer.setContent(existingBoards);

        setAlignment(existingBoardsContainer, Pos.CENTER);

        setTop(appTitle);
        setCenter(existingBoardsContainer);
        setBottom(newBoard);
    }
}
