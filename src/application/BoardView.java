package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.Card;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class representing the board view containing columns and cards
 *
 * @author Jason Cleveland
 */
public class BoardView extends BorderPane {

  private ScrollPane boardContainer = new ScrollPane();
  private HBox columnsContainer = new HBox();

  private Board board;

  public BoardView(Board board) {

    this.board = board;
    // Listeners for changes in column list
    this.board.columnsListProperty().addListener((ListChangeListener<application.model.Column>) c -> draw());

    // Listeners for changes in card list of each column
    for (application.model.Column col : this.board.columnsListProperty()) {
      col.cardsProperty().addListener((ListChangeListener<Card>) c -> draw());
    }

    // Top menu
    MenuItem save = new MenuItem("Save Board");
    save.setOnAction(e -> {
      Main.model.saveBoards();
      e.consume();
      // TODO: SQL to save board to database
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

    // TODO: Find a place for this
    // Board Name
    Label boardName = new Label("Board Name");
    boardName.setStyle("-fx-font-size: 40px");
    boardName.setAlignment(Pos.TOP_CENTER);

    setTop(menuBar);

    draw();
  }

  public void draw() {
    boardContainer.setFitToHeight(true);
    boardContainer.setFitToWidth(true);

    columnsContainer.setSpacing(20);

    columnsContainer.getChildren().clear();
    for (Column column : this.board.columnsListProperty()) {
      columnsContainer.getChildren().add(new ColumnView(column));
    }

    VBox addColumnContainer = new VBox();
    addColumnContainer.setPadding(new Insets(20));
    addColumnContainer.setAlignment(Pos.CENTER);
    addColumnContainer.setMinWidth(50);
    Label addColumn = new Label("+");
    addColumn.setOnMouseClicked(event -> {
      board.columnsListProperty().add(new Column("2"));
      draw();
    });
    addColumnContainer.getChildren().add(addColumn);
    columnsContainer.getChildren().add(addColumnContainer);

    boardContainer.setContent(columnsContainer);
    setCenter(boardContainer);
  }
}
