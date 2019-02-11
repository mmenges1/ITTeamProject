package CoreGameTopTrumps;


import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;


/*
 * This class tests whether the database used in the Top Trumps game
 * If a connection is found then a not null value is returned 
 */
public class DBConnectTest {
    Connection connection;
   
    @Test
    public void testGetConnection() throws SQLException {
    Connection con = DBConnect.connectToTopTrumpsGameDataBase();
        assertNotNull("test if a value is returned", con);
        con.close();
    }
}
