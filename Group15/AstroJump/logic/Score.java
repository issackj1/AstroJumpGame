package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Score {

	private int fileScore;
	private String topPlayer = null;
	private String[] information;

	public Score() {
		information = new String[2];
		initScore();
	}

	public void initScore(){
		readFile();
	}

	/**
	 * Checks if the score file "playerInfo.txt" already exist.
	 * If it doesn't, it will call initialWriteFile() method
	 * to create one.
	 * @return nothing.
	 */
	public void initialize() {
		File initializationCheck = new File("HighScore.txt");
		if (!initializationCheck.exists()) {
			createFile();
		}
	}

	/**
	 * Called by the initalize() method to
	 * create the initial file "playerInfo.txt" with defualt values.
	 * @exception IOException on can't write to file.
	 * @return nothing returned.
	 */
	public void createFile(){
		try{
			FileWriter fw = new FileWriter("HighScore.txt");
			PrintWriter pw = new PrintWriter(fw);
			pw.print("Top Player: Null");
			pw.println("");
			pw.print("High Score: 0");
			fw.close();
		}
		catch(IOException e){
		}
	}


	/**
	 * Writes the high score to a text file, and name of player that has the high score
	 * @param topPlayer
	 * @param newHighScore
	 */
	// Write to a file if player has achieved a highScore
	public void writeFile(String topPlayer, int newHighScore) {

		try {
			PrintWriter pw = new PrintWriter(new FileWriter("HighScore.txt"));
			pw.print("Top Player: " + topPlayer);
			pw.println("");
			pw.print("High Score: " + newHighScore);
			pw.close(); // Closes file once finished writing
		} catch (FileNotFoundException noFile) {
			createFile();
			writeFile(topPlayer, newHighScore);
		}catch(IOException e){
			createFile();
		}
	}


	/**
	 * Checks to see if file exists and can be read from
	 * If file is not present error message is displayed
	 * If file cannot be read from error message is displayed
	 */
	public void readFile() {


		try {
			BufferedReader br = new BufferedReader(new FileReader("HighScore.txt"));
			information[0] = br.readLine();
			information[1] = br.readLine();
			br.close();
			topPlayer = (information[0].split(":")[1]);
			topPlayer = topPlayer.trim();
			String fileHighScore = (information[1]).split(":")[1];
			fileHighScore = fileHighScore.trim();
			fileScore = Integer.parseInt(fileHighScore);
		}catch(FileNotFoundException noFile){
			createFile();
			readFile();
		}catch (IOException e) {
			createFile();
		}

	}
	/**
	 * getter method for fileScore
	 * @return fileScore
	 */
	public int getFileScore(){
		return fileScore;
	}
	/**
	 * getter method for topPlayer
	 * @return topPlayer
	 */
	public String getTopPlayer(){
		return topPlayer;
	}

}
