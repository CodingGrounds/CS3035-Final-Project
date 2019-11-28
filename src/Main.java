import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


/**
 * CS 3035: Team Project
 * Application Name: 
 * Description: 
 * 
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 *
 */
public class Main extends Application {
	public static final Model model = new Model();
	public static final View view = new View();
	public static final Controller controller = new Controller();

	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderPane base layout
			BorderPane root = new BorderPane();
						
			// Menu bar (top)
			MenuBar menuBar = new MenuBar();
			
			MenuItem save = new MenuItem("Save Board");
	        MenuItem exit = new MenuItem("Exit");
	        MenuItem help = new MenuItem("Help");
	        MenuItem about = new MenuItem("About");
	        
	        Menu menuFile = new Menu("File");
	        menuFile.getItems().addAll(save, exit);
	        
	        Menu menuHelp = new Menu("Help");
	        menuHelp.getItems().addAll(help, about);
	 
	        menuBar.getMenus().addAll(menuFile, menuHelp);
			root.setTop(menuBar);
			
			// View (center)
			root.setCenter(view);
			
			// Set scene
			Scene scene = new Scene(root,1600,1000);
			primaryStage.setResizable(false);
			primaryStage.setTitle("CS3035 - Team Project");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
