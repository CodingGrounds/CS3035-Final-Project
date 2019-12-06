package application;

import application.model.CheckBoxData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CheckListWidget extends ScrollPane {

    List<CheckBoxData> checkBoxDataList;
    VBox checkBoxContainer;

    public CheckListWidget() {
        checkBoxDataList = new ArrayList<>();
        checkBoxContainer = new VBox();

        draw();
    }

    public void setValue(List<CheckBoxData> checkboxes) {
        checkBoxDataList = checkboxes;
    }

    public List<CheckBoxData> getValue() {
        return checkBoxDataList;
    }

    public void draw() {
        checkBoxContainer.getChildren().clear();
        checkBoxContainer.setSpacing(10);
        checkBoxContainer.setPadding(new Insets(10));

        for (CheckBoxData data: checkBoxDataList) {
            CheckBox checkBox = new CheckBox(data.getText());
            checkBox.setSelected(data.getSelected());
            checkBoxContainer.getChildren().add(checkBox);
        }

        // TODO: Replace this with your fancy buttons Josh
        HBox inputContainer = new HBox();
        inputContainer.setSpacing(10);
        TextField newCheckboxText = new TextField();
        newCheckboxText.setPrefWidth(400);
        Button addCheckbox = new Button("+");
        addCheckbox.setOnMouseClicked(event -> {
            checkBoxDataList.add(new CheckBoxData(newCheckboxText.getText(), false));
            draw();
        });
        inputContainer.getChildren().addAll(addCheckbox, newCheckboxText);
        checkBoxContainer.getChildren().add(inputContainer);

        setMaxHeight(200);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        setContent(checkBoxContainer);
    }
}
