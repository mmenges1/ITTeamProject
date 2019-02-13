package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CoreGameTopTrumps.Card;


class CardTest {
	
	private Card newCard;

	@BeforeEach
	void setUp() throws Exception {
		newCard = new Card();
	}

	@Test
	void testCardName() {
		newCard.setName("New Card");
		assertEquals("New Card",newCard.getName(),"Test for card name: New Card");
	}
	@Test
	void testCardAttributes() {
		ArrayList<Integer>list = new ArrayList<Integer>();
		list.add(5);
		list.add(7);
		list.add(9);
		list.add(7);
		list.add(6);
		newCard.setAttributes(list);
		assertEquals(list.size(),newCard.getAttributes().size(),"Test for card attributes size");
	}
	@Test
	void testCardCriteria() {
		ArrayList<String>list = new ArrayList<String>();
		list.add("Size");
		list.add("Speed");
		list.add("Range");
		list.add("Firepower");
		list.add("Cargo");
		newCard.setCriteria(list);
		assertEquals(list.get(0),newCard.getCriterias().get(0),"Test for card criterias with reference to first item in the list");
	}

}
