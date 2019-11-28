import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * This class represents a view for the application
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 *
 */
public class View extends Pane {

	/**
	 * Overriding this method from Pane will allow draw to be called whenever necessary.
	 * In this example, it will be first called when the View is displayed.
	 */
	public void layoutChildren(){
		draw();
	}
	
	/**
	 * Function to draw the view based on the model
	 */
	private void draw() {
		this.getChildren().clear();
		this.setStyle("-fx-background-color: grey");
		
		// Why doesn't this work?
		Label l = new Label("test");
		this.getChildren().add(l);
		
	}

}
