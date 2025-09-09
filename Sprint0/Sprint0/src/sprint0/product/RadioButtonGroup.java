package sprint0.product;

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
}
