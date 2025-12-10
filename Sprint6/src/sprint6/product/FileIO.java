package sprint6.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles File I/O for recording and replaying games.
 */
public class FileIO {
	
	private SOSGame game;
	private SOSGameGUI gui;
	private FileWriter recordWriter;
	private File file;
	private BufferedReader bufferedReader;
	
	/**
	 * Creates a new FileIO instance.
	 * 
	 * @param game the SOS game instance
	 */
	public FileIO(SOSGame game, SOSGameGUI gui) {
		this.game = game;
		this.gui = gui;
	}
	
	/**
	 * Initializes game recording.
	 * 
	 * @param filename name of the text file to record in
	 */
	public void startRecording(String filename) {
		try {
			recordWriter = new FileWriter(filename);
			recordWriter.write(game.getGameMode() + "," + game.getBoardSize() + "\n");
			recordWriter.flush();
		} catch (IOException e) {
			System.out.println("Error Recording Game.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Records game moves to file.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @param letter the letter to place ('S' or 'O')
	 */
	public void recordMove(int row, int col, char letter) {
		if (recordWriter != null) {
			try {
				recordWriter.write(row + "," + col + "," + letter + "," + game.getCurrentPlayer() + "\n");
				recordWriter.flush();
				if (game.isGameOver()) {
					recordWriter.write("WINNER:" + game.getWinner() + "," + game.getBlueScore() + "," + game.getRedScore() + "\n");
					recordWriter.flush();
					recordWriter.close();
					recordWriter = null;
				}
			} catch (IOException e) {
				System.out.println("Error Recording Game.");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Reads the head of the file (game size and mode).
	 */
	public void fileReader() {
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String[] textEntry = bufferedReader.readLine().split(",");
			game = new SOSGame(Integer.parseInt(textEntry[1]), textEntry[0].equals("SIMPLE") ? SOSGame.GameMode.SIMPLE : SOSGame.GameMode.GENERAL, gui);
		} catch (Exception ex) {
			System.out.println("Error Replaying Game.");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Replays moves from file
	 */
	public void moveReplay() {
		String line;
		int time = 0;
		
		try {
			while ((line = bufferedReader.readLine()) != null && !line.startsWith("WINNER:")) {
				final String[] moveEntry = line.split(",");
				final int gameSize = game.getBoardSize();
				final int row = Integer.parseInt(moveEntry[0]); 
				final int col = Integer.parseInt(moveEntry[1]);
				final String letter = moveEntry[2];
				String player = moveEntry[3].trim();
				gui.moveReplay(row, col, letter, gameSize, time, player);
				time++;
			}
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading game file.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the BufferedReader
	 */
	public void closeReader() {
	    try {
	        if (bufferedReader != null) {
	            bufferedReader.close();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Sets the file object.
	 * 
	 * file the file to be set.
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Gets the file object
	 * 
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * Gets the game instance
	 * 
	 * @return the game instance
	 */
	public SOSGame getGame() {
	    return game;
	}
	
}