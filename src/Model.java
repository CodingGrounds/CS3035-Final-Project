import db.BoardData;
import java.util.ArrayList;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents the model for the application. It contains
 * information about how many active columns there are. Those columns
 * contain information about what model.cards they contain.
 * 
 * @author Jason Cleveland
 * @author Joshua Laver
 * @author Jata Maccabe
 * @author Anthony Tomarchio
 *
 */
public class Model {
	// List of Columns. Within each Column is a list of Cards associated with it
	private SimpleListProperty<Column> columnListProperty;	

	public Model() {
		ArrayList<Column> list = new ArrayList<Column>();
		ObservableList<Column> observableList = (ObservableList<Column>) FXCollections.observableArrayList(list);
		columnListProperty = new SimpleListProperty<Column>(observableList);
		
		// TODO: if existing BoardSchema.sql, load columns/model.cards
		
		// TODO: clicking save from the menu will load/update the model to the DB
		
		// Generate new BoardSchema.sql, if no existing boards
		for (int i = 0; i < 5; i++) {
			// One active column by default
			if (i == 0) addColumn(true);
			else addColumn(false);
		}
	}
	
	public SimpleListProperty<Column> columnListProperty(){
		return columnListProperty;
	}
	
	public void addColumn(Boolean isActive) {
    	Column newColumn = new Column(isActive);
		columnListProperty.add(newColumn);
	}
}
