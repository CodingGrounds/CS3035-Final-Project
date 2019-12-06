package application;

import application.model.CheckBoxData;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
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
        // Deep copy the data
        checkBoxDataList = new ArrayList<>(checkboxes);
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
            checkBox.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.PRIMARY) {
                    data.setSelected(((CheckBox) event.getSource()).isSelected());
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    checkBoxDataList.remove(data);
                    draw();
                }
            });
            checkBoxContainer.getChildren().add(checkBox);
        }

        // TODO: Replace this with your fancy buttons Josh
        HBox inputContainer = new HBox();
        inputContainer.setSpacing(10);
        TextField newCheckboxText = new TextField();
        newCheckboxText.setPrefWidth(450);
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
