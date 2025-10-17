package sprint2.product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        
    	// Setting up main window.
        BorderPane root = new BorderPane();
        
        root.setPadding(new Insets(10));
        
        // Top section.
        VBox topSection = new VBox(10);
        topSection.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("SOS");
        titleLabel.setTextFill(Color.BLUE);
        titleLabel.setStyle("-fx-font-size: 20px;");
        
        topSection.getChildren().addAll(titleLabel);
        root.setTop(topSection);
        
        // Center section.
        VBox centerSection = new VBox(20);
        centerSection.setAlignment(Pos.CENTER);
        

        Line horizontalLine = new Line(0, 0, 200, 100); // Relative coordinates
        horizontalLine.setStroke(Color.BLACK);
        
        Line verticalLine = new Line(0, 0, 100, 100);
        verticalLine.setStroke(Color.RED);

        centerSection.getChildren().addAll(horizontalLine, verticalLine);
        root.setCenter(centerSection);

        // Left Section.
        VBox leftSection = new VBox(15);
        leftSection.setAlignment(Pos.TOP_CENTER);
        
        Label blueLabel = new Label("Blue Player");
        blueLabel.setStyle("-fx-font-weight: bold;");
        
        RadioButtonGroup bluePlayerRadio = new RadioButtonGroup("S", "O");
        bluePlayerRadio.selectFirst();
                
        leftSection.getChildren().addAll(blueLabel, bluePlayerRadio);
        root.setLeft(leftSection);
        
        //Right section.
        VBox rightSection = new VBox(15);
        rightSection.setAlignment(Pos.TOP_CENTER);
        
        Label redLabel = new Label("Red Player");
        redLabel.setStyle("-fx-font-weight: bold;");
        
        RadioButtonGroup redPlayerRadio = new RadioButtonGroup("S", "O");
        redPlayerRadio.selectFirst();
        
        rightSection.getChildren().addAll(redLabel, redPlayerRadio);
        root.setRight(rightSection);
        
        // Bottom section.
        VBox bottomSection = new VBox(20);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(10));
        
        Label instructionLabel = new Label("Current Turn:");

        CheckBox checkbox1 = new CheckBox("Record Game");
        
        bottomSection.getChildren().addAll(instructionLabel, checkbox1);
        root.setBottom(bottomSection);

        // Create scene and show window.
        Scene scene = new Scene(root, 400, 400);
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