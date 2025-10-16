package sprint2.product;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class represents the GUI for the SOS game.
 */
public class SOSGameGUI extends Application {
    
	/**
	 * Starts the JavaFX application and sets up the main window.
	 *
	 * @param primaryStage the primary stage for this application
	 */
    @Override
    public void start(Stage primaryStage) {
        
        // Using Pane for absolute positioning.
        Pane root = new Pane();
        
        // Creating and positioning text.
        Text titleText = new Text();
        titleText.setText("SOS");
        titleText.setX(50);  // X position from left.
        titleText.setY(30);  // Y position from top.
        titleText.setFill(Color.BLUE);
        
        Text instructionText = new Text();
        instructionText.setText("Current Turn: ");
        instructionText.setX(50);
        instructionText.setY(60);
        
        // Creating and positioning lines.
        Line horizontalLine = new Line();
        horizontalLine.setStartX(20);
        horizontalLine.setStartY(80); 
        horizontalLine.setEndX(300); 
        horizontalLine.setEndY(80); 
        horizontalLine.setStroke(Color.BLACK);
        
        Line verticalLine = new Line();
        verticalLine.setStartX(200); 
        verticalLine.setStartY(100); 
        verticalLine.setEndX(300);     
        verticalLine.setEndY(200);     
        verticalLine.setStroke(Color.RED);
        
        // Creating and positioning checkboxes.
        CheckBox checkbox1 = new CheckBox("Record Game");
        checkbox1.setLayoutX(50);   // X position
        checkbox1.setLayoutY(400);  // Y position
        
        // Creating and positioning radio buttons.       
        RadioButtonGroup bluePlayerRadio = new RadioButtonGroup("S", "O");
        bluePlayerRadio.selectFirst();
        bluePlayerRadio.setLayoutX(480);
        bluePlayerRadio.setLayoutY(250);

        RadioButtonGroup redPlayerRadio = new RadioButtonGroup("S", "O");
        redPlayerRadio.selectFirst();
        redPlayerRadio.setLayoutX(120);
        redPlayerRadio.setLayoutY(250);

        // Add all elements to the root container.
        root.getChildren().addAll(
            titleText, 
            instructionText,
            horizontalLine,
            verticalLine, 
            checkbox1, 
            bluePlayerRadio,
            redPlayerRadio
        );
        
        // Create scene and show window.
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("SOS Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}