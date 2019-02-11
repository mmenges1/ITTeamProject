package CoreGameTopTrumps;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class GameStats {

	private int numberOfPlayerRoundWins;
	private int numberOfCPURoundWins;
	private int numberOfRoundsInGame;
	private int numberOfDrawsInGame;
	private String gameWinner;
	private String query;
	
	/*
	 *Responsibility to keep track of the overall statistics of the game 
	 *Class is created when the game starts and is updated at the end of each turn
	 * When the game is finished the class will insert the statistics of the game into the 'Top Trumps Game' database
	 */	
	
	//Constructor parameters hold game statistics attributes which are passed to set methods
	public GameStats(int numberOfPlayerRoundWins, int numberOfCPURoundWins, int numberOfRoundsInGame, 	int numberOfDrawsInGame,	int gameWinner) {

		setNumberOfPlayerRoundWins(numberOfPlayerRoundWins);
		setNumberOfCPURoundWins(numberOfCPURoundWins);
		setNumberOfRoundsInGame(numberOfRoundsInGame);
		setNumberOfDrawsInGame(numberOfDrawsInGame);
		setGameWinner(gameWinner);
	}
		
	//This method sets the total rounds won by the player
	public void setNumberOfPlayerRoundWins(int numberOfPlayerRoundWins) {
		this.numberOfPlayerRoundWins = numberOfPlayerRoundWins;
	}
	
	//This method sets the total rounds won by the CPU
	public void setNumberOfCPURoundWins(int numberOfPlayerRoundWins) {
		this.numberOfCPURoundWins = numberOfPlayerRoundWins;
	}
	
	//This method sets the count of the total rounds of the game
	public void setNumberOfRoundsInGame(int numberOfRoundsInGame) {
		this.numberOfRoundsInGame = numberOfRoundsInGame;
	}
	
	//This method sets the count of the total draws of the game
	public void setNumberOfDrawsInGame(int numberOfDrawsInGame) {
		this.numberOfDrawsInGame = numberOfDrawsInGame;
	}
	
	//This method sets the winner of the overall game
	//This method is called on completion of the game
	public void setGameWinner(int gameWinner) {
		
		if (gameWinner == 0) {
			this.gameWinner = " 'PLAYER' ";
		}
		else {
			this.gameWinner = " 'CPU' ";
		}
	}
	
	//This method adds integer one onto the player wins attribute
	public void setNumberOfPlayerRoundWinsPlusOne() {
		this.numberOfPlayerRoundWins = numberOfPlayerRoundWins + 1;
	}
	
	//This method adds integer one onto the CPU wins attribute
	public void setNumberOfCPURoundWinsPlusOne() {
		this.numberOfCPURoundWins = numberOfCPURoundWins + 1;
	}
	
	//This method adds integer one onto the total game rounds count attribute
	public void setNumberOfRoundsInGamePlusOne() {
		this.numberOfRoundsInGame = numberOfRoundsInGame + 1;
	}
	
	//This method adds integer one onto the total game rounds count attribute
	public void setNumberOfDrawsInGamePlusOne() {
		this.numberOfDrawsInGame = numberOfDrawsInGame + 1;
	}
	
	//This method gets the numberOfPlayerRoundWins attribute value
	public int getNumberOfPlayerRoundWins() {
		return numberOfPlayerRoundWins;
	}
	
	//This method gets the numberOfCPURoundWins attribute value
	public int getNumberOfCPURoundWins() {
		return numberOfCPURoundWins;
	}
	
	//This method gets the numberOfRoundsInGame attribute value
	public int getNumberOfRoundsInGame() {
		return numberOfRoundsInGame;
	}
	
	//This method gets the numberOfDrawsInGame attribute value
	public int getNumberOfDrawsInGame() {
		return numberOfDrawsInGame;
	}
	
	//This method gets the gameWinner attribute value
	public String getGameWinner() {
		return gameWinner;
	}
	
	
	//This method connects to the 'Top Trumps Game' database
	//then inserts the five overall game statistic attributes into the database
	//Closes database connection
	public void insertCurrentGameStatisticsIntoDatabase() {
		Statement insertionData = null;
		DBConnect DBCon = new DBConnect();
		Connection connectionToDatabase = DBConnect.connectToTopTrumpsGameDataBase();

		if (connectionToDatabase == null) {
			return; 		
		}
		
		createQuery();
		
	try {
		insertionData = connectionToDatabase.createStatement();
		insertionData.executeUpdate(query); 
		DBCon.closeConnectionToTopTrumpsGameDataBase();
	}
	catch (SQLException e) {
		e.printStackTrace();  }
	}	   

	
	public String createQuery() {
		query = "INSERT INTO \"DBTrump\".game(nos_rounds, nos_draws, winner, nos_player_wins, nos_cpu_wins) VALUES( "+
				getNumberOfRoundsInGame() + "," +
				getNumberOfDrawsInGame() + "," +
				getGameWinner() + "," +
				getNumberOfPlayerRoundWins() + "," +
				getNumberOfCPURoundWins() + ");";
		return query;
	}
}
