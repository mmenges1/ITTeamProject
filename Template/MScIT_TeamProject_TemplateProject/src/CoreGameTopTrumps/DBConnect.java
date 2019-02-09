package CoreGameTopTrumps;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.postgresql.util.PSQLException;

public class DBConnect {
	private static Connection connection;

//	public static void main(String args[]) {
//		DBConnect();
//	}
	
	public static void DBConnect() {
		connectToTopTrumpsGameDataBase();
	}
	
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
//	    	GameManager.mainGame();
//	    	e.printStackTrace();
	    }
	  	return connection;
	}
	
	public void closeConnectionToTopTrumpsGameDataBase() {
		try {
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
