package CoreGameTopTrumps;

import java.util.ArrayList;

/*
 * This class is responsible for the user playing the game
 * Hypothetically they might be asked for their name. Each human will also have a hand, which is their
 * allocated cards.
 * This will be an arraylist of card objects
 *
 */
public class Human {
	private String name;
	private ArrayList<Cards> hand;
	
	public Human(String name) {
		this.name = name;
		this.hand = new ArrayList<Cards>;
	}
	
	// get user's name (might not be necessary)
	public String getName() {
		return this.name;
	}
	
	// set user's name (might not be necessary)
	public void setName(String name) {
		this.name = name;
	}
	
	// This method will get the user's hand
	public ArrayList<Cards> getHand() {
		return this.hand;
	}
	
	// This method will set the user's hand
	public void setHand(ArrayList<Cards> dealtCards) {
		this.hand = dealtCards;
	}
	
	// This player will get the top card
	public Card showTopCard() {
		return this.hand.get(0);
	}
	
	// If the player wins, this method will add the pile of cards to the end of the player's hand
	public void addCards(ArrayList<Cards> wonCards) {
		this.hand.addAll(wonCards);
	}
	
	//This method will remove the top card (i.e. the card at the start)
	public void discardTopCard() {
		this.hand.remove(0);
	}
	
	
	
}
