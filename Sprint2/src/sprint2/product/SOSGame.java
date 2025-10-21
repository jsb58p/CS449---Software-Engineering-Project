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
	
	/**
	 * Represents the player colors.
	 */
	public enum Player {
		BLUE,
		RED
	}
	
	private Player currentPlayer;
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
		this.currentPlayer = Player.BLUE;
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
	 * Places a letter at the specified position.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @param letter the letter to place ('S' or 'O')
	 */
	public void makeMove(int row, int col, char letter) {
		board[row][col] = letter;
	}
}