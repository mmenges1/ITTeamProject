package CoreGameTopTrumps;


import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;

public class DBConnectTest {
	
    Connection connection;
   
    @Test
    public void testGetConnection() throws SQLException {
    Connection con = DBConnect.connectToTopTrumpsGameDataBase();

        assertNotNull("test return value", con);
//        assertTrue(con.isValid(0));
        con.close();
    }
}
