package CoreGameTopTrumps;

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
private DBConnect DBCon;
private Connection connectionToDatabase;
private StringBuilder report;
	//On creation of class, methods will create report on previous game statistics which will be printed to a text file
	public PreviousStats() {
		buildPreviousGameStatisticsReport();
	}
	
	//This method establishes a connection to the 'Top Trumps Game' database,
	//It creates a StringBuilder report with a a title, a first column naming the statistics rows,
	//and a second columns showing the statistic values. 
	//The connection to the database is then closed
	public void buildPreviousGameStatisticsReport() {
		DBCon = new DBConnect();
		connectionToDatabase = DBConnect.connectToTopTrumpsGameDataBase();
//		System.out.println(connectionToDatabase);
		if (connectionToDatabase == null) {
			return; 		
		}
		report = new StringBuilder();
		// Header of report	
		report.append("Statistics Of Previous Games Report\n\n");
		// shows the number of overall games
		report.append("Number of Previous Games:\t\t");
		report.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game", "count"));
		report.append("\n");
		// shows the number of times the computer won
		report.append("Number of CPU Wins:\t\t\t");
		report.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'CPU'", "count"));
		report.append("\n");
		// shows the number of times the human player won
		report.append("Number of Human Wins:\t\t\t");
		report.append(executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'PLAYER'", "count"));
		report.append("\n");
//		 shows the average number of draws per game
		report.append("Average Number of Games Drawn:\t\t");
		report.append((int)Float.parseFloat(executeTopTrumpsGameDataBaseQuery("SELECT AVG(nos_draws) FROM \"DBTrump\".game", "avg")));
		report.append("\n");
		// shows the maximum number of rounds played
		report.append("Highest Number of Rounds Played:\t");
		report.append(executeTopTrumpsGameDataBaseQuery("SELECT MAX(nos_rounds) FROM \"DBTrump\".game", "max"));	
		System.out.println(report.toString());
		DBCon.closeConnectionToTopTrumpsGameDataBase();
	}
	
	//This method tells the database connection to create a new statement
	//Query parameter is the database query to be executed; the columnName query is the name of the column on which the query will be executed
	public String executeTopTrumpsGameDataBaseQuery(String query, String columnName) {
		StringBuilder stringBuilder = new StringBuilder();
		Statement stmt = null;
		try {
			stmt = connectionToDatabase.createStatement();
			if (stmt == null) {
				return null;
			}
    		ResultSet rs = stmt.executeQuery(query);
    		while (rs.next()) {
    			stringBuilder.append(rs.getString(columnName));
            }
		}
		catch (SQLException e ) {
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	
}

