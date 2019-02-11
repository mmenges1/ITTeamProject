package CoreGameTopTrumps;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//This class tests whether the 'get' methods and the
//'createQuery' method which called when a Query is made to 
//the database equal their expected value
class GameStatsTest {
	
    @Test
    public void retrieveGametats() {
        	GameStats GameStatistics = new GameStats(5,5,5,5,1);
        	String gameWinnerCPU = " 'CPU' ";
        	String gameWinnerPLAYER = " 'PLAYER' ";
        	assertEquals(gameWinnerCPU, GameStatistics.getGameWinner());
        	assertNotEquals(gameWinnerPLAYER, GameStatistics.getGameWinner());
        	assertEquals(5,GameStatistics.getNumberOfDrawsInGame());
        	assertEquals(5,GameStatistics.getNumberOfRoundsInGame());
        	assertEquals(5,GameStatistics.getNumberOfCPURoundWins());
        	assertEquals(5,GameStatistics.getNumberOfPlayerRoundWins());
        	assertEquals(5,GameStatistics.getNumberOfDrawsInGame());
        	assertNotNull(GameStatistics.createQuery());
    }
}

