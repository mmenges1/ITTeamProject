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
		ArrayList<Card> hand = new ArrayList<Card>();
		hand.add(newCard);
		Human h = new Human("Nura", hand);
		AIPlayer a = new AIPlayer("AI player1", hand);
		User humanPlayer = h;
		User aIPlayer1 = a;
		ArrayList<User> players = new ArrayList<User>();
		players.add(humanPlayer);
		players.add(aIPlayer1);
		turnStats= new TurnStatsHelper(1,1,players,1);
	}

	@Test
	void testGetCurrentChoice() {
		assertEquals(1, turnStats.getcurrentChoice(),"Current choice test: 1");
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
	//@Test --check out this method---
	void testDraw() {
		turnStats.addCardToCardsPlayed(newCard);
		Card card2=newCard;
		card2.setName("card2");
		turnStats.addCardToCardsPlayed(card2);
		assertEquals(-1,turnStats.determineWinner(),"Testing a draw round");
	}
	@Test
	void testGetUserCardName() {
		newCard.setName("card2");
		turnStats.addCardToCardsPlayed(newCard);
		assertEquals("card2",turnStats.getUserCardName(0),"Test User Card Name");
	}

}
