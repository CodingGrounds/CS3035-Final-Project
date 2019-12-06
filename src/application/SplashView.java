package application;

import application.model.Board;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * Class representing the splash view containing navigation buttons
 * @author Jason Cleveland
 */
public class SplashView extends BorderPane {

    private List<Board> boards;

    //TODO Make Existing list pretty | Bigger
    //TODO Convert to having a controller

    public SplashView() {
        Label appTitle = new Label("Work in Progress");
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
            Main.interactiveModel.setCurrentBoard(newModelBoard);
            Main.mainScene.setRoot(new BoardView(newModelBoard));
        });

        VBox existingBoards = new VBox();
        existingBoards.setFillWidth(true);
        existingBoards.setAlignment(Pos.CENTER);
        Label existingBoardTitle = new Label("Existing Boards");
        existingBoardTitle.setAlignment(Pos.CENTER);
        existingBoardTitle.setStyle("-fx-font-size: 24px;");

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

        existingBoards.getChildren().add(existingBoardTitle);
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
