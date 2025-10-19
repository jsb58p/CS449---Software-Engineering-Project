package sprint2.product;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import sprint2.product.SOSGame.Player;

/**
 * This class represents the GUI for the SOS game.
 */
public class SOSGameGUI extends Application {
	    
	private SOSGame game;
	private GridPane boardGrid;
	private RadioButtonGroup bluePlayerRadio;
	private RadioButtonGroup redPlayerRadio;
	private Label instructionLabel;
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
        HBox topSection = new HBox(30);
        topSection.setAlignment(Pos.CENTER);
        
        Label titleLabel = new Label("SOS");
        titleLabel.setTextFill(Color.BLUE);
        titleLabel.setStyle("-fx-font-size: 20px;");
        
        Spinner<Integer> boardSizeSpinner = new Spinner<>();
        boardSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 3));
        boardSizeSpinner.setMaxWidth(60);
        
        RadioButtonGroup gameMode = new RadioButtonGroup("Simple game", "General game", false);
        gameMode.selectFirst();
        gameMode.setPadding(new Insets(30));
        
        Button newGameButton = new Button("New Game");
        newGameButton.setOnAction(e -> {
        	int size = boardSizeSpinner.getValue();
        	String selectedMode = gameMode.getSelectedButton();
        	SOSGame.GameMode mode = selectedMode.equals("Simple game")
        			? SOSGame.GameMode.SIMPLE
        			: SOSGame.GameMode.GENERAL;
        	game = new SOSGame(size, mode);
        	updateBoardDisplay();
        	instructionLabel.setText("Current Turn: Blue Player");
        	instructionLabel.setTextFill(Color.BLUE);
        	System.out.println("New game started: " + size + "x" + size + ", " + mode);
        });
        
        topSection.getChildren().addAll(titleLabel, gameMode, boardSizeSpinner, newGameButton);
        root.setTop(topSection);
        
        // Center section.
        VBox centerSection = new VBox(20);
        centerSection.setAlignment(Pos.CENTER);
        centerSection.setPadding(new Insets(30));
        
        boardGrid = new GridPane();
        boardGrid.setAlignment(Pos.CENTER);
        boardGrid.setHgap(5);
        boardGrid.setVgap(5);   

        Line horizontalLine = new Line(0, 0, 200, 100); // Relative coordinates
        horizontalLine.setStroke(Color.BLACK);
        
        Line verticalLine = new Line(0, 0, 100, 100);
        verticalLine.setStroke(Color.RED);

        centerSection.getChildren().addAll(boardGrid, horizontalLine, verticalLine);
        root.setCenter(centerSection);

        // Left Section.
        VBox leftSection = new VBox(15);
        leftSection.setAlignment(Pos.TOP_CENTER);
        
        Label blueLabel = new Label("Blue Player");
        blueLabel.setStyle("-fx-font-weight: bold;");
        
        bluePlayerRadio = new RadioButtonGroup("S", "O");
        bluePlayerRadio.selectFirst();
                
        leftSection.getChildren().addAll(blueLabel, bluePlayerRadio);
        root.setLeft(leftSection);
        
        //Right section.
        VBox rightSection = new VBox(15);
        rightSection.setAlignment(Pos.TOP_CENTER);
        
        Label redLabel = new Label("Red Player");
        redLabel.setStyle("-fx-font-weight: bold;");
        
        redPlayerRadio = new RadioButtonGroup("S", "O");
        redPlayerRadio.selectFirst();
        
        rightSection.getChildren().addAll(redLabel, redPlayerRadio);
        root.setRight(rightSection);
        
        // Bottom section.
        VBox bottomSection = new VBox(20);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(10));
        
        instructionLabel = new Label("Current Turn:");
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
     * Updates the board display to match the current game state.
     * Clears the existing grid and creates new cells based on board size.
     */
    private void updateBoardDisplay() {
    	boardGrid.getChildren().clear();
    	int size = game.getBoardSize();
    	char[][] board = game.getBoard();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                final int r = row;
                final int c = col;
                Button cell = new Button(String.valueOf(board[row][col]));
                cell.setMinSize(40, 40);
                cell.setMaxSize(40, 40);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1;");
                cell.setOnAction(e -> {
                	if (game.isCellEmpty(r, c)) {
                		Player currentPlayer = game.getCurrentPlayer();
                		String selectedLetter;
                		if (currentPlayer == Player.BLUE) {
                			selectedLetter = bluePlayerRadio.getSelectedButton();
                		} else {
                			selectedLetter = redPlayerRadio.getSelectedButton();
                		}
                		game.makeMove(r,  c,  selectedLetter.charAt(0));
                		cell.setText(selectedLetter);
                		game.switchPlayer();
                		Player nextPlayer = game.getCurrentPlayer();
                		if (nextPlayer == Player.BLUE) {
                			instructionLabel.setText("Current Turn: Blue Player");
                			instructionLabel.setTextFill(Color.BLUE);
                		} else {
                			instructionLabel.setText("Current Turn: Red Player");
                			instructionLabel.setTextFill(Color.RED);
                		}
                	}
                });
                boardGrid.add(cell, col, row);
            }
        }	
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