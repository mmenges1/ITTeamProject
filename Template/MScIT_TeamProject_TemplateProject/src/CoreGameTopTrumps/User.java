package CoreGameTopTrumps;

import java.util.ArrayList;

/*
 * This class is responsible for the functionality of each user in the game
 * There are a list of methods that operate or return a player's name or card deck.
 * This is the generic superclass from which separate human and ai classes will inherit from.
 */
abstract public class User {
	protected String name;
	protected ArrayList<Card> hand;
	protected int score;

//	public User() {
//		this.hand = new ArrayList<Card>();
//	}
	
	
	public User(String name, ArrayList<Card> hand) {
		this.name = name;
		this.hand = hand;
		this.score = 0;
	}

	
	/**
	 * Returns the name of a player
	 * @return the name of a player
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * This method sets the name for a player
	 * @param name of a player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
		/**
		 * This method returns a player's deck as an arraylist of card objects
		 * @return a player's deck
		 */
		public ArrayList<Card> getHand() {
			return this.hand;
		}
		
		/**
		 * This method will set a player's deck with a given list of card objects
		 * @param dealtCards a list of card objects (after cards are dealt)
		 */
		public void setHand(ArrayList<Card> dealtCards) {
			this.hand.clear();
			this.hand = dealtCards;
		}
	
	/*
	 * Return a name of a criterion in the top card
	 */
	abstract String getCriteriaName(Card topCard);
	
	/*
	 * Return the index of a criterion in the top card
	 */
	abstract  int getIndexofCriteriaWithHighestValue(Card topCard);

	/*
	 * Return the name of the top card
	 * @return name of top card
	 */
	abstract String getTopCardName();
	
	/*
	 * Return a message of a player's choice
	 */
	abstract public String playerChoosesMessage(Card topCard);
	
	/**
	 * This method calls the viewCard() method in the card class for the top card in a player's deck
	 * which prints to the console that card
	 * @return a string representation of a player's top card
	 */
	public String showTopCard() {
		Card topCard = this.hand.get(0);
		return topCard.viewCard();
	}

	/*
	 * This method will return the first card in a player's deck
	 * @return top card in a player's deck as a card object
	 */
	public Card getTopCard() {
		return this.hand.get(0);
	}
	

	/**
	 * This method will add a pile of cards to the bottom of a player's deck (e.g. after they win a round)
	 * @param wonCards is an arraylist of card objects
	 */
	public void addCards(ArrayList<Card> wonCards) {
		this.hand.addAll(wonCards);
	}
	
	/**
	 * This method will add a single card to the bottom of a player's deck
	 * @param card to be added
	 */
	public void addSingleCard(Card card) {
		this.hand.add(card);
	}

	/**
	 * This method will return the name of a criteria that is selected by a player for the top card
	 * @param index of the chosen criterion in list of criteria
	 * @return the name of a criterion associated with that index in the list
	 */
	public String selectCriteriaOfTopCard(int index) {
		Card topCard = this.hand.get(0);
		String attributeName = topCard.getCriteriaName(index);
		return attributeName;
	}

	/**
	 * This method will return the number associated with a chosen criterion for the top card
	 * @param index of the chosen criterion in the list of criteria
	 * @return the value associated with that criterion
	 */
	public int getValue(int index) {
		Card topCard = this.hand.get(0);
		return topCard.getAttribute(index);
	}

	/**
	 * This method will remove the top card of a player's deck
	 */
	public void discardTopCard() {
		this.hand.remove(0);
	}

	/*
	 * This method will return the number of cards remaining in a player's deck
	 * @return number of cards left in player's deck
	 */
	public int getHandSize() {
		return this.hand.size();
	}

	/*
	 * This method will check whether a player's deck is empty or not
	 * @return true if deck size is 0 and false otherwise
	 */
	public boolean userLoses() {
		return this.hand.size() == 0;
	}

	public String toString() {
		return this.name + ": You have " + this.getHandSize() + " cards in your deck.";
	}
}
