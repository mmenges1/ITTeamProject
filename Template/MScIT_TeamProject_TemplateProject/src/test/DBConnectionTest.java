package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CoreGameTopTrumps.DBConnect;

class DBConnectionTest {
	DBConnect dBConnection ;

	@BeforeEach
	void setUp() throws Exception {
		dBConnection = new DBConnect();
	}

	@SuppressWarnings("static-access")
	//@Test
	void testDBConnection()throws SQLException {
		assertFalse(dBConnection.connectToTopTrumpsGameDataBase().isClosed());
	}

}
