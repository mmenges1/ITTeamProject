package CoreGameTopTrumps;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/*
 * This class is responsible for connecting to the 'Top Trumps Game' database,
 * Retrieving statistics on the previous games played,
 * Creating a report based on the statistics,
 * Writing the report to a text file in the directory
 */
public class PreviousStats {

int numberOfPlayerRoundWins;
int numberOfCPURoundWins;
int numberOfRoundsInGame;
int numberOfDrawsInGame;
String gameWinner;
private DBConnect DBCon;
String reportFileText;
Connection connectionToDatabase;

	//On creation of class, methods will create report on previous game statistics which will be printed to a text file
	public PreviousStats() {
		buildPreviousGameStatisticsReport();
		writeReportToTextFile();
	}

	//This method establishes a connection to the 'Top Trumps Game' database,
	//It creates a StringBuilder report with a a title, a first column naming the statistics rows,
	//and a second columns showing the statistic values.
	//The connection to the database is then closed
	private void buildPreviousGameStatisticsReport() {
		StringBuilder stringBuilder = new StringBuilder();
		DBCon = new DBConnect();
		connectionToDatabase = DBCon.connectToTopTrumpsGameDataBase();
		StringBuilder report = new StringBuilder();

		// Header of report
		stringBuilder.append("\t\tStatistics Of Previous Games Report\n\n");
		// shows the number of overall games
		stringBuilder.append("Number of Previous Games:\t");
		stringBuilder.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(game_id) FROM TopTrumpsGame", "count"));
		stringBuilder.append("\n");
		// shows the number of times the computer won
		stringBuilder.append("Number of CPU Wins:\t");
		stringBuilder.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(game_id) FROM TopTrumpsGame WHERE winner = 'CPU'", "count"));
		stringBuilder.append("\n");
		// shows the number of times the human player won
		stringBuilder.append("Number of Human Wins:\t");
		stringBuilder.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(game_id) FROM TopTrumpsGame WHERE winner = 'human'", "count"));
		stringBuilder.append("\n");
		// shows the average number of draws per game
		stringBuilder.append("Average Number of Games Drawn:\t");
		stringBuilder.append((int)Float.parseFloat(executeTopTrumpsGameDataBaseQuery("SELECT AVG(draws) FROM TopTrumpsGame", "average")));
		stringBuilder.append("\t");
		// shows the maximum number of rounds played
		stringBuilder.append("Highest Number of Rounds Played:\t");
		stringBuilder.append(executeTopTrumpsGameDataBaseQuery("SELECT MAX(rounds) FROM TopTrumpsGame", "maximum"));
		// Converts stringBuilder to a String
		reportFileText = report.toString();
		DBCon.closeConnectionToTopTrumpsGameDataBase();
	}

	//This method tells the database conneciton to create a new statment
	//Query parameter is the database query to be executed; the col query is the name of the column on which the query will be executed
	public String executeTopTrumpsGameDataBaseQuery(String query, String col) {
		StringBuilder stringBuilder = new StringBuilder();
		Statement stmt = null;

		try {
			stmt = connectionToDatabase.createStatement();
    		ResultSet rs = stmt.executeQuery(query);
    		while (rs.next()) {
    				stringBuilder.append(rs.getString(col));
            }
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}

		return stringBuilder.toString();
	}

	//This method writes the created statistics report to a text file
	private void writeReportToTextFile() {
		try {
			FileWriter fw;
			fw = new FileWriter("PreviousGameStatistics.txt");
			fw.write(reportFileText);
			fw.close(); }
		catch (IOException i) {
		}
	}
}
