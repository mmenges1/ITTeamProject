package CoreGameTopTrumps;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


//This class tests whether the query methods
//which are called when the database value is to be displayed
//to the player return a not null value
public class PreviousStatsTest {
    Connection connection;

    @Test
    public void retrievePreviousStats() {
    	PreviousStats getPreviousStats = new PreviousStats();
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'CPU'", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'PLAYER'", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT COUNT(nos_rounds) FROM \"DBTrump\".game WHERE winner = 'PLAYER'", "count"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT AVG(nos_draws) FROM \"DBTrump\".game", "avg"));
    	assertNotNull("Check if the return message is NotNull", getPreviousStats.executeTopTrumpsGameDataBaseQuery("SELECT MAX(nos_rounds) FROM \"DBTrump\".game", "max"));
    }
}