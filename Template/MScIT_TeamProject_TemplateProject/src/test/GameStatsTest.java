package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CoreGameTopTrumps.GameStats;
import CoreGameTopTrumps.GameStats.PointsTracker;

class GameStatsTest {

	private GameStats gameStats;
	private PointsTracker pointsTracker ;
	@BeforeEach
	void setUp() throws Exception {
		gameStats = new GameStats(4,2,10,0);
		pointsTracker = new PointsTracker();
	}

	@Test
	void testNumberOfRoundsWin() {
		gameStats.setNumberOfPlayerRoundWins(4);
		assertEquals(4,gameStats.getNumberOfPlayerRoundWins(),"Test number of rounds wins after setting it to 4");
	}
	@Test
	void testNumberOfCPURoundWins() {
		gameStats.setNumberOfCPURoundWins(3);
		assertEquals(3, gameStats.getNumberOfCPURoundWins(),"Test number of CPU round wins after setting it to 3");
	}
	@Test
	void testNumberOfPointsDraws() {
		pointsTracker.setDraw(5);
		assertEquals(5, pointsTracker.getDraw(),"Test number of game draws after setting it to 5");
	}
	@Test
	void testNumberOfGameDraws() {
		gameStats.setNumberOfDrawsInGame(5);
		assertEquals(5, gameStats.getNumberOfDrawsInGame(),"Test number of game draws after setting it to 5");
	}
	@Test
	void testNumberOfRoundsInGame() {
		gameStats.setNumberOfRoundsInGame(20);
		assertEquals(20,gameStats.getNumberOfRoundsInGame(),"Test number of rounds in a game after setting it to 20");
	}
	@Test
	void testNumberOfPlayerRoundWinsPlusOne() {
		gameStats.setNumberOfPlayerRoundWins(4);
		gameStats.setNumberOfPlayerRoundWinsPlusOne();
		assertEquals(5,gameStats.getNumberOfPlayerRoundWins(),"Test number of rounds wins plus one after setting it to 4 & setting a plus one");
	}
	@Test
	void testNumberOfCPURoundWinsPlusOne() {
		gameStats.setNumberOfCPURoundWins(3);
		gameStats.setNumberOfCPURoundWinsPlusOne();
		assertEquals(4, gameStats.getNumberOfCPURoundWins(),"Test number of CPU round wins plus one after setting it to 3 & setting plus one");	
	}
	@Test
	void testNumberOfRoundsInGamePlusOne() {
		gameStats.setNumberOfRoundsInGame(20);
		gameStats.setNumberOfRoundsInGamePlusOne();
		assertEquals(21,gameStats.getNumberOfRoundsInGame(),"Test number of rounds plus one in a game after setting it to 20 & setting plus one");
	}
	@Test
	void testNumberOfDrawsInGamePlusOne() {
		gameStats.setNumberOfDrawsInGamePlusOne();
		assertEquals(1,gameStats.getNumberOfDrawsInGame(),"Test Draws plus one ");
	}
	

}
