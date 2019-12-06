package application;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuBarWidget extends MenuBar {

    public MenuBarWidget() {
        MenuItem back = new MenuItem("Close Board");
        back.setOnAction(e ->{
            Main.mainScene.setRoot(Main.splashView);
            e.consume();
        });

        // Top menu
        MenuItem save = new MenuItem("Save Board");
        save.setOnAction(e -> {
            Main.model.saveBoards();
            e.consume();
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> {
            e.consume();
            Platform.exit();
        });

        MenuItem help = new MenuItem("Help");
        help.setOnAction(e -> {
            Main.mainScene.setRoot(new HelpView());
            e.consume();
        });

        MenuItem about = new MenuItem("About");
        about.setOnAction(e -> {
            Main.mainScene.setRoot(new AboutView());
            e.consume();
        });

        Menu menuFile = new Menu("File");
        Menu menuHelp = new Menu("Help");
        menuFile.getItems().addAll(back, save, exit);
        menuHelp.getItems().addAll(help, about);
        getMenus().addAll(menuFile, menuHelp);
    }
}
