package CoreGameTopTrumps;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static Connection connection;
	
	public DBConnect() {
		connectToTopTrumpsGameDataBase();
	}
	
	public static Connection connectToTopTrumpsGameDataBase(){
		String url = "jdbc:mysql://localhost:3306/sys";
		String userName = "root";
		String password = "931112";

	  	try {
	  		Class.forName("com.mysql.jdbc.Driver");// include this line in your code.
	  		connection = DriverManager.getConnection(url, userName, password);
	  	}
	    catch (SQLException | ClassNotFoundException e)
	    {
	    	System.err.println("Connection failed!");
	    	e.printStackTrace();
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
