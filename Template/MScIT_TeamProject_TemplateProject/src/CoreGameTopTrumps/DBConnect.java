package CoreGameTopTrumps;

import java.sql.*;

/*
 * Responsibility to establish a connection to the 'Top Trumps Game' database; close the connection to the database after query execution
 */

public class DBConnect {

	public Connection connection;
	
	//New class calls method to establish database connection
	public DBConnect() {
		connectToTopTrumpsGameDataBase();
	}

	//Initial connection to 'Top Trumps Game' database; 
	//returns the connection; allows for other methods to execute queries on the database
	public Connection connectToTopTrumpsGameDataBase(){
		String nameOfDataBase = "?????";
		String userName = "?????";
	  	String password = "?????";

	  	try {
	  		connection = DriverManager.getConnection("jdbc:postgresql://??????" + nameOfDataBase, userName, password);
	  	}
	  	catch (SQLException e) {
	  		e.printStackTrace(); 
	  	}
	  	return connection;
	}
	
	//Closes connections to 'Top Trumps Game' database
	public void closeConnectionToTopTrumpsGameDataBase() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}