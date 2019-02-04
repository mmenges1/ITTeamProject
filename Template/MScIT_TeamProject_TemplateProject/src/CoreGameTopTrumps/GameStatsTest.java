package CoreGameTopTrumps;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameStatsTest {
	
//    @Test
//    public void retrieveGameStatsTest() {
//    	PreviousStats getPreviousStats = new PreviousStats();
	
    @Test
    public void retrieveGametats() {
        	
        	GameStats GameStatistics = new GameStats(5,5,5,5,1);
//            PreviousStats getPreviousStats = new PreviousStats();
//        	getPreviousStats.buildPreviousGameStatisticsReport();
        	
//        	assertEquals("Check if the return message is NotNull", GameStatistics.getGameWinner(),"CPU");
//        	assertNotEquals("Check if the return message is NotNull", GameStatistics.getGameWinner(), "PLAYER");
//        	System.out.println(GameStatistics.getGameWinner());
//        	String first = " 'CPU' ";
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

