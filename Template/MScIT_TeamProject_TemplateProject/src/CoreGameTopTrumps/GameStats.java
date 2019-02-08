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
	private PointsTracker points;
	
	/*
	 *Responsibility to keep track of the overall statistics of the game 
	 *Class is created when the game starts and is updated at the end of each turn
	 * When the game is finished the class will insert the statistics of the game into the 'Top Trumps Game' database
	 */	
	
	//Constructor parameters hold game statistics attributes which are passed to set methods
	public GameStats(int numberOfPlayerRoundWins, int numberOfCPURoundWins, int numberOfRoundsInGame, 	int numberOfDrawsInGame) {

		setNumberOfPlayerRoundWins(numberOfPlayerRoundWins);
		setNumberOfCPURoundWins(numberOfCPURoundWins);
		setNumberOfRoundsInGame(numberOfRoundsInGame);
		setNumberOfDrawsInGame(numberOfDrawsInGame);
		points = new PointsTracker();
		
	}
	
	public void incrementPoint(String name) {
		points.increment(name);
	}
	
	public PointsTracker getPoints() {
		return points;
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
	public void setGameWinner() {
		points.setWinner();
		
		if (points.getWinner().equals("You")) {
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
		points.increment("Draw");
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
		return points.getDraw();
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

		String query = "INSERT INTO \"DBTrump\".game(nos_rounds, nos_draws, winner, nos_player_wins, nos_cpu_wins) VALUES( "+
		getNumberOfRoundsInGame() + "," +
		getNumberOfDrawsInGame() + "," +
		getGameWinner() + "," +
		getNumberOfPlayerRoundWins() + "," +
		getNumberOfCPURoundWins() + ");";
		
	try {
		insertionData = connectionToDatabase.createStatement();
		insertionData.executeUpdate(query); 
		DBCon.closeConnectionToTopTrumpsGameDataBase();
	}
	catch (SQLException e) {
		e.printStackTrace();  }
	}
	
	// Points tracker helper class to support JSON conversion
	
	public static class PointsTracker {
		private int human;
		private int ai1;
		private int ai2;
		private int ai3;
		private int ai4;
		private int draw;
		private String winner;
		
		public PointsTracker() {
			this.human = 0;
			this.ai1 = 0;
			this.ai2 = 0;
			this.ai3 = 0;
			this.ai4 = 0;
		}
		
		//increment player by name, because the player index changes as game progresses
		public void increment(String name) {
			if(name.equals("You")) {
				human++;
			} else if(name.equals("AI 1")){
				ai1++;
			} else if(name.equals("AI 2")){
				ai2++;
			} else if(name.equals("AI 3")){
				ai3++;
			} else if(name.equals("AI 4")){
				ai4++;
			} else if(name.equals("Draw")){
				draw++;
			}
			
//			System.out.println("points toString from within object : " + this.toString());
		}
		
		// To String
		
		@Override
		public String toString() {
			return "PointsTracker [human=" + human + ", ai1=" + ai1 + ", ai2=" + ai2 + ", ai3=" + ai3 + ", ai4=" + ai4
					+ ", draw=" + draw + "]";
		}
		
		// getters and setters for Jackson
		public String getWinner() {
			return this.winner;
		}
		
		public void setWinner() {
			int[] points = {human, ai1, ai2, ai3, ai4};
			int max = 0;
			int winnerIndex = 0;
			String winnerName = "";
			
			for(int i = 0; i<points.length; i++){
				if (points[i] > max){
					max = points[i];
					winnerIndex = i;
				}
			}
			if(winnerIndex == 0) {
				winnerName = "You";
			}else {
				winnerName = "AI "+ winnerIndex;
			}
			
		}

		public int getHuman() {
			return human;
		}

		public void setHuman(int human) {
			this.human = human;
		}

		public int getAi1() {
			return ai1;
		}

		public void setAi1(int ai1) {
			this.ai1 = ai1;
		}

		public int getAi2() {
			return ai2;
		}

		public void setAi2(int ai2) {
			this.ai2 = ai2;
		}

		public int getAi3() {
			return ai3;
		}

		public void setAi3(int ai3) {
			this.ai3 = ai3;
		}

		public int getAi4() {
			return ai4;
		}

		public void setAi4(int ai4) {
			this.ai4 = ai4;
		}

		public int getDraw() {
			return draw;
		}

		public void setDraw(int draw) {
			this.draw = draw;
		}
		
	}
}