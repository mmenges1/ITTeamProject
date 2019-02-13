package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CoreGameTopTrumps.Card;
import CoreGameTopTrumps.TurnStatsHelper;
import CoreGameTopTrumps.User;
import CoreGameTopTrumps.Human;
import CoreGameTopTrumps.AIPlayer;

class TurnStatsHelperTest {
	private TurnStatsHelper turnStats ;
	private Card newCard;
	private Card card2;

	@BeforeEach
	void setUp() throws Exception {
		newCard = new Card();
		ArrayList<Integer>list = new ArrayList<Integer>();
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(7);
		list.add(6);
		newCard.setAttributes(list);
		newCard.setName("New Card");
		ArrayList<String>criteriaList = new ArrayList<String>();
		criteriaList.add("Size");
		criteriaList.add("Speed");
		criteriaList.add("Range");
		criteriaList.add("Firepower");
		criteriaList.add("Cargo");
		newCard.setCriteria(criteriaList);
		card2=new Card();
		card2.setName("card2");
		ArrayList<Integer>aList = new ArrayList<Integer>();
		aList.add(8);
		aList.add(10);
		aList.add(11);
		aList.add(12);
		aList.add(6);
		card2.setAttributes(aList);
		card2.setCriteria(criteriaList);
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(newCard);
		ArrayList<Card> c = new ArrayList<Card>();
		c.add(card2);
		Human h = new Human("Nura", hand);
		AIPlayer a = new AIPlayer("AI player1", c);
		User humanPlayer = h;
		User aIPlayer1 = a;
		ArrayList<User> players = new ArrayList<User>();
		players.add(humanPlayer);
		players.add(aIPlayer1);
		turnStats= new TurnStatsHelper(1,players,1);
	}

	@Test
	void testAddCardsToCardsPlayer() {
		turnStats.addCardToCardsPlayed(newCard);
		assertEquals(1,turnStats.getCardPlayedSize(),"CardsPlayedSize after adding a card:1");
	}
	@Test
	void testAddPlayerHandSize() {
		turnStats.addPlayerHandSize(1);
		assertEquals(1,turnStats.getPlayerHandSize(0),"Testing player hand size");
	}
	@Test
	void testNumberOfPlayers() {
		assertEquals(2,turnStats.getPlayerSize(),"Number of players in the game:2");
	}
	@Test
	void testAddPlayer() {
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(newCard);
		AIPlayer a = new AIPlayer("AI Player2", hand);
		User aIPlayer2 = a;
		ArrayList<User> players = new ArrayList<User>();
		players.add(aIPlayer2);
		turnStats.addPlayers(players);
		assertEquals(1,turnStats.getPlayerSize(),"Number of players after adding another player:1");
	}
	@Test
	void testWinner() {
		turnStats.setWinner(1);
		assertEquals(1,turnStats.getWinner(),"Testing for Winner after setting winner to 1");
	}
	@Test 
	void testDraw() {
		turnStats.addCardToCardsPlayed(newCard);
		Card card3=newCard;
		card3.setName("card3");
		turnStats.addCardToCardsPlayed(card3);
		turnStats.determineWinner();
		assertTrue(turnStats.getIsDraw(),"Testing a draw round");
	}
	@Test
	void testAGameRoundWin() {
		turnStats.addCardToCardsPlayed(newCard);
		turnStats.addCardToCardsPlayed(card2);
		assertEquals(1,turnStats.determineWinner(),"Testing a winner of a game round");
		
	}
	@Test
	void testGetUserCardName() {
		newCard.setName("card2");
		turnStats.addCardToCardsPlayed(newCard);
		assertEquals("card2",turnStats.getUserCardName(0),"Test User Card Name");
	}
	@Test
	void testWinnerName() {
		turnStats.setWinnerName(0);
		assertEquals("Nura",turnStats.getWinnerName(),"Test winner name after setting it");
	}
	@Test
	void testCommunitySize() {
		turnStats.setCommunitySize(10);
		assertEquals(10,turnStats.getCommunitySize(),"Test for community deck size after setting it to 10");
	}
	@Test
	void testGetAnyCardTopAttribute() {
		turnStats.addCardToCardsPlayed(newCard);
		assertEquals("Size : 5",turnStats.getAnyCardTopAttribute(0),"Test top card attribute of first player");
	}
	@Test
	void testGetTopCardByAttribute() {
		turnStats.addCardToCardsPlayed(card2);
		assertEquals("Size : 8",turnStats.getTopCardByAttribute(),"Test top card by attribute");
	}
	@Test
	void testPassCardsPlayed() {
		turnStats.addCardToCardsPlayed(newCard);
		turnStats.addCardToCardsPlayed(card2);
		assertEquals(newCard.getName(),turnStats.passCardsPlayed().get(0).getName(),"Test name of first pass card played");
	}
	

}
