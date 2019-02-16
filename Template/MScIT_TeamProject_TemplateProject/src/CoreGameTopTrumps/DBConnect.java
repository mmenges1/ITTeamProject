package CoreGameTopTrumps;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is responsible for establishing and closing a connection to a PostgreSQL database
 */
public class DBConnect {
	private static Connection connection;
	
	public static void DBConnect() {
		connectToTopTrumpsGameDataBase();
	}
		
	/*
	 * This method establishes a connection to the Top Trumps database
	 *  @returns the database connection
	 */
	public static Connection connectToTopTrumpsGameDataBase(){
		String url = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/";
		String userName = "m_18_2391985s";
		String password = "hu8jmn3";

	  	try {
	  		Class.forName("org.postgresql.Driver");
	  		connection = DriverManager.getConnection(url, userName, password);
	  	}
	    catch (SQLException | ClassNotFoundException e)	
	    {
	    	System.err.println("You are not connected to the database!");
//	    	e.printStackTrace();
	    	connection = null;
	    }
	  	return connection;
	}
	
	/*
	 * This method closes the connection to the Top Trumps database
	 */
	public void closeConnectionToTopTrumpsGameDataBase() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
