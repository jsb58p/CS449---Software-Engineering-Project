package sprint2.product;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 * A custom VBox containing two radio buttons with a shared toggle group.
 */
public class RadioButtonGroup extends VBox {

    private ToggleGroup toggleGroup = new ToggleGroup();

    /**
     * Creates a RadioButtonGroup with two radio buttons labeled as specified.
     *
     * @param label1 the label for the first radio button
     * @param label2 the label for the second radio button
     */
    public RadioButtonGroup(String label1, String label2) {
        super(10); // spacing between buttons
        
        RadioButton rb1 = new RadioButton(label1);
        RadioButton rb2 = new RadioButton(label2);
        rb1.setToggleGroup(toggleGroup);
        rb2.setToggleGroup(toggleGroup);
        this.getChildren().addAll(rb1, rb2);
    }
    
    /**
     * Gets the text of the currently selected radio button.
     * 
     * @return the text ("S" or "O") of the selected button, or null if nothing is selected
     */
    public String getSelectedLetter() {
    	RadioButton selected = (RadioButton) toggleGroup.getSelectedToggle(); //typecast generic Toggle object to RadioButton object
    	return (selected != null) ? selected.getText() : null;
    }
    
    /**
     * Sets the first radio button as selected by default.
     */
    public void selectFirst() {
    	if (!this.getChildren().isEmpty()) {
    		RadioButton first = (RadioButton) this.getChildren().get(0);
    		first.setSelected(true);
    	}
    }
}
