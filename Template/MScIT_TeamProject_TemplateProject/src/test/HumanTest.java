package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import CoreGameTopTrumps.Card;
import CoreGameTopTrumps.Human;

class HumanTest {

	private Human person;
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
		person= new Human("Nura",hand);

	}

	@Test
	void testName() {
		person.setName("Nourida");
		assertEquals("Nourida",person.getName(),"Testing new human player name");
	}

}
