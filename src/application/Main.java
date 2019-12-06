package application;

import application.model.InteractiveModel;
import application.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;


/**
 * CS 3035: Team Project Application Name: Description:
 *
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 */
public class Main extends Application {

  public static final Model model = new Model();
  public static final InteractiveModel interactiveModel = new InteractiveModel();
  public static final SplashView splashView = new SplashView();
  public static final Controller controller = new Controller();

  public static Scene mainScene;

  @Override
  public void start(Stage primaryStage) {
    try {
      // Set scene
      mainScene = new Scene(splashView, 800, 600);

      primaryStage.setTitle("Work in Progress");
      primaryStage.setScene(mainScene);
      primaryStage.setMaximized(true);
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void stop(){
    System.out.println("Stage is closing");
    model.saveBoards();
  }

}
