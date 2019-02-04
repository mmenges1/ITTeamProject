package CoreGameTopTrumps;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PreviousStatsTest {
	
    Connection connection;
//    private DBConnect DBCon;
//    public PreviousStats 
//     PreviousStats getPreviousStats;
    
//    @Before
//    public void before() {
//        DBConnect DBCon = new DBConnect();
////        		connection.connectToTopTrumpsGameDataBase();
//    }

//     PreviousStats getPreviousStats = new PreviousStats();
     
//  @Before
//  public void before() {
//      PreviousStats getPreviousStats = new PreviousStats();
////      		connection.connectToTopTrumpsGameDataBase();
//  }

//    @After
//    public void after() {
//    	DBCon.closeConnectionToTopTrumpsGameDataBase();
//    }

    @Test
    public void retrievePreviousStats() {
    	PreviousStats getPreviousStats = new PreviousStats();
//        PreviousStats getPreviousStats = new PreviousStats();
//    	getPreviousStats.buildPreviousGameStatisticsReport();
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'CPU'", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'PLAYER'", "count"));

    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'PLAYER'", "count"));

    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT AVG(nos_draws) FROM \"DBTrump\".game", "avg"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT MAX(nos_rounds) FROM \"DBTrump\".game", "max"));
//    	assertNotNull("", getPreviousStats.report, )
//    	assertNotNull("Check if the return message is NotNull", getPreviousStats.
//    	
//    	
//        Statement statement = connection.createStatement();
//        SybaseDBConnection.closeStatement(statement);
//        assertTrue(statement.isClosed());
    }

//    @Test
//    public void closeStatementWithNullShouldNotThrow() {
//        SybaseDBConnection.closeStatement(null);
//    }
}