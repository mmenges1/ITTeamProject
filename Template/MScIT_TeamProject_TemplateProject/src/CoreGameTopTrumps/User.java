package CoreGameTopTrumps;

import java.util.ArrayList;

// This will be the superclass which human and aiuser will both inherit from

abstract public class User {
	private String name;
	private ArrayList<Card> hand;
	
	public User(String name, ArrayList<Card> hand) {
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
		public ArrayList<Card> getHand() {
			return this.hand;
		}
		
		// This method will set the user's hand
		public void setHand(ArrayList<Card> dealtCards) {
			this.hand = dealtCards;
		}
		
		abstract void getTopCardName();
		
		// This player will print the topCard
		// I imagine that each card will be an arraylist itself
		// with each stat in each index
		// or each card could be an object?
		
		// BC - Changed so that the top Card stats are printed via sysout. 
		//  I anticipate this will change to passing the whole card
		public void showTopCardCriteria() {
			Card topCard = this.hand.get(0);
			topCard.viewCard();
		}
		
		// If the player wins, this method will add the pile of cards to the end of the player's hand
		public void addCards(ArrayList<Card> wonCards) {
			this.hand.addAll(wonCards);
		}
		
		//This method will remove the top card (i.e. the card at the start)
		public void discardTopCard() {
			this.hand.remove(0);
		}
		
		
		// checks to see if a user's hand is == 0
		// return true (i.e. a player is out).
		public boolean UserLoses() {
			if (this.hand.size() == 0) {
				return true;
			}
			return false;
		}
	}
