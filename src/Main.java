import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * CS 3035: Team Project Application Name: Description:
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Main extends Application {

  public static final BorderPane container = new BorderPane();
  public static final Model model = new Model();
  public static final View view = new View();
  public static final Controller controller = new Controller();

  @Override
  public void start(Stage primaryStage) {
    try {
      // Top menu
      MenuItem save = new MenuItem("Save model.Board");
      save.setOnAction(e -> {
        e.consume();
        // TODO: SQL to save BoardSchema.sql to database
      });

      MenuItem exit = new MenuItem("Exit");
      exit.setOnAction(e -> {
        e.consume();
        Platform.exit();
      });

      MenuItem help = new MenuItem("Help");
      help.setOnAction(e -> {
        e.consume();
        // TODO: help menu option triggered
      });

      MenuItem about = new MenuItem("About");
      about.setOnAction(e -> {
        e.consume();
        // TODO: about menu option triggered
      });

      MenuBar menuBar = new MenuBar();
      Menu menuFile = new Menu("File");
      Menu menuHelp = new Menu("Help");
      menuFile.getItems().addAll(save, exit);
      menuHelp.getItems().addAll(help, about);
      menuBar.getMenus().addAll(menuFile, menuHelp);

      // Splash
      Label appName = new Label("Application Name");
      appName.setStyle("-fx-font-size: 40px");
      appName.setPrefHeight(300);
      appName.setPrefWidth(1600);
      appName.setAlignment(Pos.TOP_CENTER);

      Label newBoard = new Label("New model.Board");
      newBoard.setStyle("-fx-font-size: 24px; -fx-cursor: hand");
      newBoard.setPrefHeight(100);
      newBoard.setPrefWidth(1600);
      newBoard.setAlignment(Pos.TOP_CENTER);
      newBoard.setOnMouseClicked(e -> {
        // Sets the scene to a new BoardSchema.sql
        primaryStage.getScene().setRoot(container);
      });

      VBox existingBoards = new VBox();
      existingBoards.setPrefWidth(1600);
      existingBoards.setAlignment(Pos.TOP_CENTER);

      Label existingBoardTitle = new Label("Existing Boards");
      existingBoardTitle.setAlignment(Pos.TOP_CENTER);
      existingBoardTitle.setStyle("-fx-font-size: 24px");
      existingBoardTitle.setPrefHeight(50);
      existingBoardTitle.setPrefWidth(1600);
      existingBoards.getChildren().addAll(existingBoardTitle);

      // Existing Boards
      ArrayList<Label> existingBoardList = new ArrayList<Label>();

      // TODO: SQL query to fetch any saved boards

      // Suggestion: SELECT query will return rows, iterate through those rows
      // and create a new Label (or other element) for each

      // NOTE: when these elements are clicked on, they need to load our model
      // with their information that was stored in the DB (# of cols, model.cards in each col)

      // If no saved boards, add default message
      Label defaultMsg = new Label("No existing boards");
      defaultMsg.setAlignment(Pos.TOP_CENTER);
      existingBoards.getChildren().add(defaultMsg);

      VBox splashContainer = new VBox();
      splashContainer.setStyle("-fx-background-color: lightblue;-fx-text-fill: black;-fx-padding: 10");
      splashContainer.getChildren().addAll(appName, newBoard, existingBoards);

      // model.Board Name
      Label boardName = new Label("model.Board Name");
      boardName.setStyle("-fx-font-size: 40px");
      boardName.setPrefHeight(75);
      boardName.setPrefWidth(1600);
      boardName.setAlignment(Pos.TOP_CENTER);

      // Set container items
      container.setTop(menuBar);
      container.setCenter(view);
      container.setBottom(boardName);

      // Set scene
      Scene scene = new Scene(splashContainer, 1600, 1000);
      primaryStage.setResizable(false);
      primaryStage.setTitle("APP NAME");
      primaryStage.setScene(scene);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

}
