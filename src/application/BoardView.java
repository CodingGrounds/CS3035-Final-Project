package application;

import application.model.Board;
import application.model.Column;
import application.model.cards.Card;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;

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

    // Board Name
    Label boardName = new Label("Board Name");
    boardName.setStyle("-fx-font-size: 40px");
    boardName.setAlignment(Pos.TOP_CENTER);

    setTop(new MenuBarWidget());

    draw();
  }

  public void draw() {
    boardContainer.setFitToHeight(true);
    boardContainer.setFitToWidth(true);

    columnsContainer.setSpacing(20);

    columnsContainer.getChildren().clear();
    for (Column column : this.board.columnsListProperty()) {
      columnsContainer.getChildren().add(new ColumnView(board, column));
    }

    VBox addColumnContainer = new VBox();
    addColumnContainer.setPadding(new Insets(20));
    addColumnContainer.setAlignment(Pos.CENTER);
    addColumnContainer.setMinWidth(50);

    IconFontFX.register(FontAwesome.getIconFont());

    IconNode iconNode = new IconNode(FontAwesome.PLUS_CIRCLE);
    iconNode.setIconSize(30);
    iconNode.setFill(Color.DODGERBLUE);

    Label addColumn = new Label();
    addColumn.setGraphic(iconNode);

    addColumn.setStyle("-fx-cursor: hand;");

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
