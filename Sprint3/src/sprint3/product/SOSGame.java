package sprint3.product;

/**
*Represents the SOS game logic.
*Handles game size, rules, and player turns.
*/
public class SOSGame {
	
	/**
	 * Represents the game mode options.
	 */
	public enum GameMode {
		SIMPLE,
		GENERAL
	}
	
	/**
	 * Represents the player colors.
	 */
	public enum Player {
		BLUE,
		RED
	}
	
	/**
	 * Represents the array boundaries to be checked.
	 */
	public enum Bound {
		OHORIZONTALBOUND,
		SLEFTBOUND,
		SLEFTBOUND2,
		SRIGHTBOUND,
		SRIGHTBOUND2,
		OVERTICALBOUND,
		SUPBOUND,
		SUPBOUND2,
		SDOWNBOUND,
		SDOWNBOUND2
	}
	
	private Player currentPlayer;
	private int boardSize;
	private GameMode gameMode;
	private char[][] board;
	private GameModeLogic gameModeLogic;
	private BoundaryCheck bounds;
	
	
	/**
	 * Creates a new SOS game with the specified settings.
	 * 
	 * @param boardSize the size of the board (e.g. 3 for 3x3)
	 * @param gameMode the game mode (SIMPLE or GENERAL)
	 */
	public SOSGame(int boardSize, GameMode gameMode) {
		this.boardSize = boardSize;
		this.gameMode = gameMode;
		this.board = new char[boardSize][boardSize];
		this.currentPlayer = Player.BLUE;
		bounds = new BoundaryCheck(boardSize);

		if (gameMode == GameMode.SIMPLE) {
			gameModeLogic = new SimpleMode(this);
		} else {
			gameModeLogic = new GeneralMode(this);
		}
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	/**
	 * Updates the board size.
	 * Used when user changes the spinner value.
	 * 
	 * @param boardSize the new board size (between 3 and 10)
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize  = boardSize;
	}
	
	/**
	 * Updates the game mode.
	 * Used when user selects Simple or General game.
	 * 
	 * @param gameMode the new game mode
	 */
	public void setGameMode(GameMode gameMode) {
		this.gameMode = gameMode;
		if (gameMode == GameMode.SIMPLE) {
			gameModeLogic = new SimpleMode(this);
		} else {
			gameModeLogic = new GeneralMode(this);
		}
	}
	
	/**
	 * Gets the current game mode.
	 * 
	 * @return the game mode
	 */
	public GameMode getGameMode() {
		return gameMode;
	}
	
	/**
	 * Gets the current board state.
	 * 
	 * @return the 2D char array representing the board.
	 */
	public char[][] getBoard(){
		return board;
	}
	
	/**
	 * Gets the current board size.
	 * 
	 * @return the board size
	 */
	public int getBoardSize() {
		return boardSize;
	}
	
	/**
	 * Checks if a cell is empty.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @return true if the cell is empty, false otherwise
	 */
	public boolean isCellEmpty(int row, int col) {
		return board[row][col] == ' ';
	}
	
	/**
	 * Gets the current player.
	 * 
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Switches to the other player.
	 */
	public void switchPlayer() {
		currentPlayer = (currentPlayer == Player.BLUE) ? Player.RED : Player.BLUE;
	}
	
	/**
	 * Places a letter at the specified position and applies game logic.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @param letter the letter to place ('S' or 'O')
	 */
	public void makeMove(int row, int col, char letter) {
		board[row][col] = letter;
		gameModeLogic.handleMove(row, col, letter, currentPlayer);
		
		if (gameModeLogic.isGameOver()) {
			gameModeLogic.getWinner();
		}
	}
	
	/**
	 * Checks if the most recent move formed any "SOS" patterns and counts them.
	 * It first checks the horizontal and diagonal for a given letter, then vertical.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @param letter the letter placed ('S' or 'O')
	 * @return the number of "SOS" patterns formed by this move
	 */
	public int checkScore(int row, int col, char letter) {
		int sosCount = 0;
		for(int i = -1; i <= 1; i++) {
			// Horizontal and diagonal check for "SOS".
			switch (letter) {
			case 'S':
				if(bounds.boundsCheck(Bound.SRIGHTBOUND, row, col, i) && board[row-i][col+1] == 'O') {
				    if(bounds.boundsCheck(Bound.SRIGHTBOUND2, row, col, i) && board[row - (2*i)][col + 2] == 'S') {
				        sosCount++;
				        System.out.println("SOS");
				    }
				}
				if (bounds.boundsCheck(Bound.SLEFTBOUND, row, col, i) && board[row+i][col-1] == 'O') {
					if(bounds.boundsCheck(Bound.SLEFTBOUND2, row, col, i) && board[row + (2*i)][col - 2] == 'S') {
				        sosCount++;
				        System.out.println("SOS");
				    }
				}
				break;
			case 'O':
				if(bounds.boundsCheck(Bound.OHORIZONTALBOUND, row, col, i) && board[row-i][col+1] == 'S' && board[row+i][col-1] == 'S') {
					System.out.println("SOS");
					sosCount++;
				} 
				break;
			}
		}
		// Vertical check for "SOS".
		switch (letter) {
		case 'S':
			if(bounds.boundsCheck(Bound.SUPBOUND, row, col) && board[row+1][col] == 'O') { 
				if(bounds.boundsCheck(Bound.SUPBOUND2, row, col, 0) && board[row+2][col] == 'S') { 
					System.out.println("SOS");
					sosCount++;
				}
			}
			if(bounds.boundsCheck(Bound.SDOWNBOUND, row, col) && board[row-1][col] == 'O') {
				if(bounds.boundsCheck(Bound.SDOWNBOUND2, row, col) && board[row-2][col] == 'S') {
					System.out.println("SOS");
					sosCount++;
				}
			}
			break;
		case 'O':
			if(bounds.boundsCheck(Bound.OVERTICALBOUND, row, col) && board[row+1][col] == 'S' && board[row-1][col] == 'S') {
				System.out.println("SOS");
				sosCount++;
			}
			break;
		}
		 System.out.println(sosCount);
		return sosCount;
	}
}