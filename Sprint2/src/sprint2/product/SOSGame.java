package sprint2.product;

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
	
	private int boardSize;
	private GameMode gameMode;
	private char[][] board;
	
	
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
	}
	
	/**
	 * Gets the current board state.
	 * 
	 * @return the 2D char array representing the board
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
}