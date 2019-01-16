package CoreGameTopTrumps;

import java.util.ArrayList;

// This will be the superclass which human and aiuser will both inherit from

abstract public class User {
	private String name;
	private ArrayList<Cards> hand;
	
	public User(String name; ArrayList<Cards> hand) {
		this.name = name;
		this.hand = hand;
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
		
		// This player will print the topCard
		// I imagine that each card will be an arraylist itself
		// with each stat in each index
		// or each card could be an object?
		abstract void showTopCard();
		
		// If the player wins, this method will add the pile of cards to the end of the player's hand
		public void addCards(ArrayList<Cards> wonCards) {
			this.hand.addAll(wonCards);
		}
		
		//This method will remove the top card (i.e. the card at the start)
		public void discardTopCard() {
			this.hand.remove(0);
		}
		
		public String toString() {
			return this.name + ": You have " + this.hand.size() + " cards in your hand.";
		}
	}
