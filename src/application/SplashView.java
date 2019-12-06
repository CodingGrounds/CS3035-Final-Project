package application;

import application.model.Board;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Class representing the splash view containing navigation buttons
 * @author Jason Cleveland
 */
public class SplashView extends BorderPane {

    private List<Board> boards;

    //TODO Make Existing list pretty | Bigger

    public SplashView() {
        Main.model.boardsProperty().addListener((observable, oldValue, newValue) -> draw());
        draw();
    }

    public void draw() {
        Label appTitle = new Label("Work in Progress");
        appTitle.setStyle("-fx-font-size: 40px");
        setAlignment(appTitle, Pos.CENTER);

        Label newBoard = new Label("New Board");
        newBoard.setStyle("-fx-font-size: 24px; -fx-cursor: hand");
        setAlignment(newBoard, Pos.CENTER);
        newBoard.setPadding(new Insets(50,0,50,0));

        this.boards = Main.model.boardsProperty().get();

        newBoard.setOnMouseClicked(e -> {
            // Get the name of the board
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Board title");
            dialog.setHeaderText("Enter a name for the new board.");
            dialog.setGraphic(null);

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                Board newModelBoard = new Board(result.get());
                this.boards.add(newModelBoard);
                Main.interactiveModel.setCurrentBoard(newModelBoard);
                Main.mainScene.setRoot(new BoardView(newModelBoard));
            }
        });

        VBox existingBoards = new VBox();
        existingBoards.setFillWidth(true);
        existingBoards.setAlignment(Pos.CENTER);

        // Existing Boards
        ArrayList<Label> existingBoardList = new ArrayList<Label>();

        for(Board board: boards){
            Label label = new Label(board.getName());
            label.setStyle("-fx-font-size: 20px; -fx-cursor: hand; -fx-padding: 10px;");
            label.setOnMouseClicked(event->{
                System.out.println("Label Click on Board " + board.getName());
                Main.interactiveModel.setCurrentBoard(board);
                Main.mainScene.setRoot(new BoardView(board));
            });

            existingBoardList.add(label);
        }

        existingBoards.getChildren().addAll(existingBoardList);

        if (existingBoardList.size() == 0) {
            Label defaultMsg = new Label("No existing boards");
            existingBoards.getChildren().add(defaultMsg);
        }

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
