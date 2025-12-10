package sprint6.product;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
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
import javafx.util.Duration;
import sprint6.product.SOSGame.Player;
import javafx.stage.FileChooser;

/**
 * This class represents the GUI for the SOS game.
 */
public class SOSGameGUI extends Application {
	    
	private SOSGame game;
	private GridPane boardGrid;
	private RadioButtonGroup bluePlayerRadio;
	private RadioButtonGroup redPlayerRadio;
	private RadioButtonGroup bluePlayerOpponent;
	private RadioButtonGroup redPlayerOpponent;
	private RadioButtonGroup gameMode;
	private Label instructionLabel;
	private Label bluePoints = new Label(" ");
	private Label redPoints = new Label(" ");
    private String selectedMode;
    private boolean replay = false;
    private FileIO io;
    private Timeline timeline;
	
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
        titleLabel.setTextFill(Color.PURPLE);
        titleLabel.setStyle("-fx-font-size: 20px;");
        
        Label sizeLabel = new Label("Board Size:");
        sizeLabel.setStyle("-fx-font-size: 16px");
        sizeLabel.setPadding(new Insets(0, -20, 0, 0));
        
        Spinner<Integer> boardSizeSpinner = new Spinner<>();
        boardSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 10, 3));
        boardSizeSpinner.setMaxWidth(60);
        
        gameMode = new RadioButtonGroup("Simple game", "General game", false);
        gameMode.selectFirst();
        gameMode.setPadding(new Insets(30));
        
        Button newGameButton = new Button("New Game");
        
        topSection.getChildren().addAll(titleLabel, gameMode, sizeLabel, boardSizeSpinner, newGameButton);
        root.setTop(topSection);
        
        // Center section.
        VBox centerSection = new VBox(20);
        centerSection.setAlignment(Pos.CENTER);
        centerSection.setPadding(new Insets(30));
        
        boardGrid = new GridPane();
        boardGrid.setAlignment(Pos.CENTER);   

        centerSection.getChildren().addAll(boardGrid);
        root.setCenter(centerSection);

        // Left Section.
        VBox leftSection = new VBox(15);
        leftSection.setAlignment(Pos.TOP_CENTER);
        
        Label blueLabel = new Label("Blue Player");
        blueLabel.setStyle("-fx-font-weight: bold;");
        
        bluePlayerOpponent = new RadioButtonGroup("Human", "Computer");
        bluePlayerOpponent.selectFirst();
        bluePlayerOpponent.getSecondButton().setPadding(new Insets(0, 0, 0, 20));
        
        bluePlayerRadio = new RadioButtonGroup("S", "O");
        bluePlayerRadio.selectFirst();
        bluePlayerRadio.setPadding(new Insets(0, 0, 0, 50));
           
    	bluePoints.setTextFill(Color.BLUE);
    	bluePoints.setStyle("-fx-font-size: 30");
                
        leftSection.getChildren().addAll(blueLabel, bluePlayerOpponent.getFirstButton(), bluePlayerRadio, bluePlayerOpponent.getSecondButton(), bluePoints);
        root.setLeft(leftSection);
        
        //Right section.
        VBox rightSection = new VBox(15);
        rightSection.setAlignment(Pos.TOP_CENTER);
        
        Label redLabel = new Label("Red Player");
        redLabel.setStyle("-fx-font-weight: bold;");
        redLabel.setPadding(new Insets(0, 50, 0, 0));
        
        redPlayerOpponent = new RadioButtonGroup("Human", "Computer");
        redPlayerOpponent.selectFirst();
        redPlayerOpponent.getFirstButton().setPadding(new Insets(0,50,0,0));
        redPlayerOpponent.getSecondButton().setPadding(new Insets(0,30,0,0));
        
        redPlayerRadio = new RadioButtonGroup("S", "O");
        redPlayerRadio.selectFirst();
        redPlayerRadio.setPadding(new Insets(0, 0, 0, 30));
        
    	redPoints.setTextFill(Color.RED);
    	redPoints.setStyle("-fx-font-size: 30");
        
        rightSection.getChildren().addAll(redLabel, redPlayerOpponent.getFirstButton(), redPlayerRadio, redPlayerOpponent.getSecondButton(), redPoints);
        root.setRight(rightSection);
        
        // Bottom section.
        VBox bottomSection = new VBox(20);
        bottomSection.setAlignment(Pos.CENTER);
        bottomSection.setPadding(new Insets(10));
        
        instructionLabel = new Label("Current Turn:");
    	instructionLabel.setStyle("-fx-font-size: 15");
        CheckBox recordGame = new CheckBox("Record Game");
        
        Button replayGame = new Button("Replay");
        gameReplay(replayGame);
        
        bottomSection.getChildren().addAll(instructionLabel, recordGame, replayGame);
        root.setBottom(bottomSection);

        // Create scene and show window.
        newGameButton.setOnAction(e -> {
        	instructionLabel.setText("Current Turn: Blue Player");
        	instructionLabel.setTextFill(Color.BLUE);
        	boardGrid.setDisable(false);
        	bluePoints.setText(" ");
        	redPoints.setText(" ");
        	int size = boardSizeSpinner.getValue();
        	selectedMode = gameMode.getSelectedButton();
        	SOSGame.GameMode mode = selectedMode.equals("Simple game") ? SOSGame.GameMode.SIMPLE : SOSGame.GameMode.GENERAL;
        	game = new SOSGame(size, mode, this);
        	io = new FileIO(game, this);
        	
        	if(recordGame.isSelected()) {
        		FileChooser fileChooser = new FileChooser();
        		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        		io.setFile(fileChooser.showSaveDialog(null));
        		if (io.getFile() != null) {
        			io.startRecording(io.getFile().getAbsolutePath());
        		} 
        	}
        	updateBoardDisplay();
        });
        
        Scene scene = new Scene(root, 800, 600);
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
                cell.setMinSize(50, 40);
                cell.setMaxSize(50, 40);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-font-weight: bold;");
                // Wire up cells on game board.
                cell.setOnAction(e -> {
                	if (game.isCellEmpty(r, c)) {
                		String selectedLetter;
                		if (game.getCurrentPlayer() == Player.BLUE) {
                			selectedLetter = bluePlayerRadio.getSelectedButton();
                		} else {
                			selectedLetter = redPlayerRadio.getSelectedButton();
                		}
                		game.makeMove(r, c, selectedLetter.charAt(0));
                		cell.setText(selectedLetter);
                		if(boardGrid.isDisabled()) { // Checks if game is over before switching players.
                			return;
                		}
                		// Show current player turn.
                		Player nextPlayer = game.getCurrentPlayer();
                		if (nextPlayer == Player.BLUE) {
                			instructionLabel.setText("Current Turn: Blue Player");
                			instructionLabel.setTextFill(Color.BLUE);
                		} else {
                			instructionLabel.setText("Current Turn: Red Player");
                			instructionLabel.setTextFill(Color.RED);
                		}
                		if (!replay) {
                			computerMove();
                		}
                	}
                });
                boardGrid.add(cell, col, row);
            }
        }
        
    	if (!replay && "Computer".equals(bluePlayerOpponent.getSelectedButton())) {
    		computerMove();
    	}
    }
    
    /**
     * Replays a game from a valid file containing a recording.
     * 
     * @param replayGame the Replay button on the GUI
     */
    private void gameReplay(Button replayGame) {
    	replayGame.setOnAction(e -> {
        	FileChooser fileChooser = new FileChooser();
        	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        	
        	 if (io == null) {
                 io = new FileIO(null, this);
             }
        	 
        	io.setFile(fileChooser.showOpenDialog(null));
        	
        	if (io.getFile() == null) {
        		return;
        	}
        	
        	bluePoints.setText(" ");
        	redPoints.setText(" ");
        	
        	io.fileReader();
            game = io.getGame();
            
			replay = true;
			updateBoardDisplay();
			boardGrid.setDisable(true);
			
			timeline = new Timeline();
			io.moveReplay();
			
	        timeline.setOnFinished(event -> io.closeReader());
			timeline.play();
			replay = false;
    	});
    }
    
    /**
     * Replays moves from file to the GUI.
     * 
     * @param row the row
     * @param col the column
     * @param letter the letter to be placed
     * @param gameSize the size of the game
     * @param time the time to place the new frame
     */
    public void moveReplay(int row, int col, String letter, int gameSize, int time, String player) {
    	timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(time), event -> {
			game.makeMove(row,  col,  letter.charAt(0));
			((Button) boardGrid.getChildren().get(row * gameSize + col)).setText(letter);
	        bluePoints.setText(String.valueOf(getBlueScore()));
			redPoints.setText(String.valueOf(getRedScore()));
			if(!game.isGameOver()) { 
				instructionLabel.setText("Current Turn: " + player + " Player");
				instructionLabel.setTextFill(player.equals("BLUE") ? Color.BLUE : Color.RED);
			}
		}));
    }
    
    /**
     * Updates display with computer move
     */
    private void computerMove() {
    	int[] move;
    	int size = game.getBoardSize();
    	char[][] board = game.getBoard();
    	while ((move = game.cpuMoveCheck()) != null) {
    	    int cpuRow = move[0];
    	    int cpuCol = move[1];
    	    char letter = game.getBoard()[cpuRow][cpuCol];
    	    board[cpuRow][cpuCol] = letter;
    	    ((Button) boardGrid.getChildren().get(cpuRow * size + cpuCol)).setText(String.valueOf(letter));
      
    	    if(game.isGameOver()) {
    			endGameDisplay(game.getWinner(), getBlueScore(), getRedScore());
    			break;
    		}
    	}
    }
	
    
    /**
     * Draws line at appropriate angle over cells forming SOS pattern.
     * 
     * @param row the row to draw on
     * @param col the column to draw on
     * @param color the player color (RED or BLUE)
     * @param angle the value used to determine the line angle
     */
    public void drawLine(int row, int col, Player color, int angle) {
    	Line line = new Line(0, 0, 0, 0);
    	switch(angle) {
    	case -1:
    		line.setEndX(47);
    		line.setEndY(36);
    		break;
    	case 0: 
    		line.setEndX(47);
    		line.setEndY(0);
    		break;
    	case 1:
    		line.setEndX(47);
    		line.setEndY(-36);
    		break;
    	case 2:
    		line.setStartX(25);
    		line.setStartY(0);
    		line.setEndX(25);
    		line.setEndY(36);
    		break;
    	default:
    		break;
    	}
		line.setStrokeWidth(2);
		// Set line color.
		if (color == Player.BLUE) {
			line.setStroke(Color.rgb(0, 0, 255, 0.5));
		} else {
			line.setStroke(Color.rgb(255, 0, 0, 0.5));
		}
		GridPane.setHalignment(line, HPos.CENTER);
		boardGrid.add(line, col, row);
    }
    
    
    /**
     * Sets the display after a player has won.
     * 
     * @param playerColor the player color that won (BLUE or RED)
     * @param blueScore the blue player's score
     * @param redScore the red player's score
     */
    public void endGameDisplay(Player playerColor, int blueScore, int redScore) {
    	boardGrid.setDisable(true);
    	switch(playerColor){
    	case BLUE:
    		instructionLabel.setTextFill(Color.BLUE);
    		instructionLabel.setText("Blue Player Wins!");
    		break;
    	case RED:
    		instructionLabel.setTextFill(Color.RED);
        	instructionLabel.setText("Red Player Wins!");
        	break;
    	default:
    		instructionLabel.setTextFill(Color.BLACK);
        	instructionLabel.setText("Draw.");
        	break;
    	}
    	bluePoints.setText(String.valueOf(blueScore));
    	redPoints.setText(String.valueOf(redScore));
    }
    
    /**
     * Get the selected blue player.
     * 
     * @return the currently selected blue player
     */
    public String getBluePlayerOpponent() {
    	return bluePlayerOpponent.getSelectedButton();
    }
    
    /**
     * Get the selected red player.
     * 
     * @return the currently selected red player
     */
    public String getRedPlayerOpponent() {
    	return redPlayerOpponent.getSelectedButton();
    }
    
    /**
     * Get the blue player letter
     * 
     * @return the current letter of the blue player.
     */
    public String getBluePlayerRadio() {
    	return bluePlayerRadio.getSelectedButton();
    }
    
    /**
     * Get the red player letter
     * 
     * @return the current letter of the red player.
     */
    public String getRedPlayerRadio() {
    	return redPlayerRadio.getSelectedButton();
    }
    
    /**
     * Get the blue player score.
     * 
     * @return the blue player score
     */
    public int getBlueScore() {
    	return game.getBlueScore();
    }
    
    /**
     * Get the red player score.
     * 
     * @return the red player score
     */
    public int getRedScore() {
    	return game.getRedScore();
    }
    
    /**
     * Main entry point for the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Gets the I/O instance
     */
    public FileIO getFileIO() {
    	return io;
    }
}