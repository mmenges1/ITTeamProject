package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CoreGameTopTrumps.AIPlayer;
import CoreGameTopTrumps.Card;

class AITest {
	
	private AIPlayer player;
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
		player = new AIPlayer("AI Player",hand);
	}

	@Test
	void testSetName() {
		player.setName("AI Player2");
		assertEquals("AI Player2",player.getName(),"Test change player name");
	}
	
	@Test
	void testGetCriteriaNameOfTheHighestValue() {
		assertEquals("Range",player.getCriteriaName(newCard),"Testing attribute name  with the highest value");	
	}
	
	@Test
	void testGetHighestValueAttribute() {
		assertEquals(9,player.getHighestValueAttribute(newCard),"Testing attribute index with the highest value");
	}
	
	@Test
	void testGetIndexofCriteriaWithHighestValue(){
		assertEquals(3,player.getIndexofCriteriaWithHighestValue(newCard),"Testing index of criteria with hightst value");
	}


}
