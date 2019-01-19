package CoreGameTopTrumps;

import java.util.ArrayList;

// This will be the superclass which human and aiuser will both inherit from

abstract public class User {
	private String name;
	private ArrayList<Card> hand;
	private ArrayList<String> headers;
	
	public User() {
		this.hand = new ArrayList<Card>();
		this.headers = new ArrayList<String>();
	}
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
		
		abstract String getTopCardName();
		
		// This player will print the topCard
		// I imagine that each card will be an arraylist itself
		// with each stat in each index
		// or each card could be an object?
		
		public String showTopCardCriteria() {
			Card topCard = this.hand.get(0);
			return topCard.viewCard();
		}
		
		public Card getTopCard() {
			return this.hand.get(0);
		}
		
		// If the player wins, this method will add the pile of cards to the end of the player's hand

		public void addCards(ArrayList<Card> wonCards) {
			this.hand.addAll(wonCards);
		}
		
		public void addSingleCard(Card card) {
			this.hand.add(card);
		}
		
		public String selectCriteriaOfTopCard(int index) {
			Card topCard = this.hand.get(0);
			String attributeName = topCard.getAttributeName(index);
			return attributeName;
		}
		// therefore an index will be passed to this method which will
		//return the corresponding attribute number that is in the same index
		public int getValue(int criteriaNumber) {
			Card topCard = this.hand.get(0);
			return topCard.getValueforAttribute(criteriaNumber);
		}
		
		//get name of the top card
		public String getNameOfTopCard() {
			return this.hand.get(0).getName();
		}
		
		//This method will remove the top card (i.e. the card at the start)
		public void discardTopCard() {
			this.hand.remove(0);
		}
		
		//return the size of a player's hand
		public int displayHandSize() {
			return this.hand.size();
		}
		//Added for testing - prints all cards to sys out
		public void displayEntireHand() {
			for(Card c: hand) {
				c.viewCard();
			}
		}
		
		public int getHandSize() {
			return hand.size();
		}
		
		// checks to see if a user's hand is == 0
		// return true (i.e. a player is out).
		public boolean userLoses() {
			
			if (this.hand.size() == 0) {
				return true;
			}
			return false;
		}
	}
