package application.model;

import java.io.Serializable;

public class CheckBoxData implements Serializable {

    private String text;
    private boolean selected;

    /**
     * Stores data used to represent a checkbox
     *
     * @param text text displayed beside the checkbox
     * @param selected whether the checkbox is selected
     */
    public CheckBoxData(String text, boolean selected) {
        this.text = text;
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public boolean getSelected() {
        return selected;
    }
}
