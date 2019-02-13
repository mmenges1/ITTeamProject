package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import CoreGameTopTrumps.Deck;
import CoreGameTopTrumps.Card;

class DeckTest {
	
	private Deck newDeck;

	@BeforeEach
	public void setUp(){
		newDeck = new Deck();
	}

	@Test
	public void testCreateCard() {
		String [] data = {"New Card","4","6","7","8","9"};
		Card newCard=newDeck.createCard(data);
		assertEquals("New Card",newCard.getName(),"Testing card name of a newly created card");
	}
	
	@Test
	public void testCreateDeckWithValidFileName() {
		String file ="StarCitizenDeck.txt";
		ArrayList<Card> deckCards= newDeck.createDeck(file);
		assertEquals(40,deckCards.size(),"Size of deck cards");
	}
	@Test
	public void testCreateDeckWithInValidFileName() {
		String file ="Deck.txt";
		ArrayList<Card> deckCards= newDeck.createDeck(file);
		assertTrue(deckCards.isEmpty());
	}

}
