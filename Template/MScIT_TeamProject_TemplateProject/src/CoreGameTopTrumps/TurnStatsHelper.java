package CoreGameTopTrumps;

import java.util.ArrayList;
/**
 * This class is responsible for handling each round and determine whether there is a winning card or if it is a draw
 * A new object is created in the gameManager class, on each round  a card from each player is added to the pile
 */
public class TurnStatsHelper {
	ArrayList<Card> cardsPlayed;
	ArrayList<User> players;
	String attributeName;
	int turnNumber;
	int winner;
	int attributeNumberPlayed;
	int communitySize;
	boolean isDraw;
	int currentChoice;
	ArrayList<Integer> playerHandSizes;
	String roundString;
	
	/**
	 * This constructs a new turn stats helper object that manages each round
	 * @param turnNumber
	 * @param attributeNumberPlayed - the index of the criteria that was played for this round 
	 * @param players - the list of players that are currently in the game at the present round
	 * @param currentChoice - is the index of the player in the list of players who made the choice of attribute to play this round
	 */
	public TurnStatsHelper(int turnNumber, int attributeNumberPlayed, ArrayList<User> players, int currentChoice) {
		this.turnNumber = turnNumber;
		this.attributeNumberPlayed = attributeNumberPlayed;
		cardsPlayed = new ArrayList<Card>();
		this.players = new ArrayList<User>(players);
		this.currentChoice = currentChoice;
		playerHandSizes = new ArrayList<Integer>(); // used to work out difference between old and new hand size 
	}

	/*
	 * This method returns the numerical represenation of the attribute that was played based on the index of the arraylist
	 * of criteria in card object
	 * @return index of criteria that was played for round
	 */
	public int getcurrentChoice() {
		return this.currentChoice;
	}

	/*
	 * This method adds a card from all available players to a deck for this round
	 * @param card object from each player
	 */
	public void addCardToCardsPlayed(Card card) {
		cardsPlayed.add(card);
	}
	/**
	 * Add a player's deck size to a list of deck sizes in the present round, used to determine the change to deck size after each round
	 * @param handSize for each player
	 */
	public void addPlayerHandSize(int handSize) {
		this.playerHandSizes.add(handSize);
	}

	/**
	 * This method returns the player's deck size for a round
	 * @param index of a player in list of active players
	 * @return a number of the size of deck
	 */
	public int getPlayerHandSize(int index) {
		return this.playerHandSizes.get(index);
	}
	/**
	 * This method works out the change in deck size based on the outcome of the round
	 * @param player - a player that participated in the round
	 * @param index - the index of that player in the list of players
	 * @return a string displaying the changes in deck size
	 */
	public String returnDifferenceHandSize(User player, int index) {
		if (player.getHandSize() - this.getPlayerHandSize(index) < 0 ) {
			return "" + (player.getHandSize() - this.getPlayerHandSize(index) + " card") ;
		} else {
			return  "+" + (player.getHandSize() - this.getPlayerHandSize(index) + " cards") ; 
		}
	}

	/**
	 * This method returns the number of players that are active in the current round
	 * @return number of active players in round
	 */
	public int getPlayerSize() {
		return this.players.size();
	}
	
	/**
	 * This method adds an active player to the current round
	 * @param player with deck size greater than 1 
	 */
	public void addPlayers(ArrayList<User> players) {
		this.players = new ArrayList<User>(players);
	}
	
	/**
	 * Sets the winner based on the winning hand and the index of that player of the player list
	 * @param index of the player that won in this list of players
	 */
	public void setWinner(int index) {
		this.winner = index;
	}

	/**
	 * THis method returns the winner which is the index of the current active players
	 * @return number of winner
	 */
	public int getWinner() {
		return winner;
	}
	
	/**
	 * Returns a boolean value whether the current round ended in a draw
	 * @return true if ended in a draw, false if outright win by a player
	 */
	public boolean getIsDraw() {
		return isDraw;
	}
	
	/**
	 * This method returns a list of played cards for the current round
	 * @return list of card objects that were played this round
	 */
	public ArrayList<Card> passCardsPlayed(){
		return cardsPlayed;
	}
	
	/**
	 * This method returns the name of the winning card
	 * @return String of winning card name
	 */
	public String getWinningCardName() {
		return this.attributeName;
	}


	/**
	 * This method is used to determine the winner of the round by comparing the value for the played criteria of each card
	 * The player choice for the next round is determined
	 * It'll return the index of the player in the player's list who won
	 * If the round ends in draw, it'll set the boolean accordingly
	 * @return  index of the player in the player's list who won or who made the choice if draw
	 */
	public int determineWinner() {
		int currentStat = 0;
		int highestStat = 0;		

		for(int i = 0; i < cardsPlayed.size(); i++) {
			currentStat = cardsPlayed.get(i).getAttribute(attributeNumberPlayed);
			if(currentStat > highestStat) {
				highestStat = currentStat;
				this.winner = i;
				isDraw = false;
				this.attributeName = getUserCardName(this.winner);
			} else if (currentStat == highestStat) {
				this.winner = currentChoice;
				isDraw = true;
			}
		}
		return this.winner;		
	}

	@Override
	public String toString() {
		return "TurnStatsHelper [cardsPlayed=" + cardsPlayed + ", turnNumber=" + turnNumber + ", winner=" + winner
				+ ", attributeNumberPlayed=" + attributeNumberPlayed + ", isDraw=" + isDraw + ", roundString="
				+ roundString + "]";
	}
	
	/**
	 * Displays a round summary as a string, including which player won, community deck size, played cards, changes in deck sizes 
	 * @returns a message of the result of a round
	 */
	public String getRoundString() {
		return String.format("\nPlayer %d won using Attribute %d. \n\nCommunity deck size is currently:%d \n\nThe cards that everyone played are:\n%s ", winner, attributeNumberPlayed, communitySize, cardsPlayed);
	}

	/**
	 * This method sets the community deck size depending on 
	 * @param size
	
	public void setCommunitySize(int size) {
		this.communitySize = size;		
	} */

	/**
	 * Get the winning top card by the attribute and score as a message
	 * @return the winning card attribute  and score
	 */
	public String getTopCardByAttribute() {
		return cardsPlayed.get(this.winner).getCriteriaName(this.attributeNumberPlayed-1 )+ " : " + cardsPlayed.get(this.winner).getAttribute(attributeNumberPlayed);
	}

	/**
	 * This method gets the attribute and score for the played attribute for any player at index playerindex 
	 * @param playerIndex is the index of the player in this list of players
	 * @return a message of the attribute and score for the played attribute for a player
	 */
	public String getAnyCardTopAttribute(int playerIndex) {
		return cardsPlayed.get(playerIndex).getCriteriaName(attributeNumberPlayed-1) + " : " + cardsPlayed.get(playerIndex).getAttribute(attributeNumberPlayed);
	}
	
	/**
	 * Returns the name of a card for a player at index index
	 * @param index of the player in list of players 
	 * @return name of card of a player in the round
	 */
	public String getUserCardName(int index) {
		return cardsPlayed.get(index).getName();
	}
	
	/**
	 * This method returns the number of cards that were played in the round
	 * @return a number of cards played
	 */
	public int getCardPlayedSize() {
		return cardsPlayed.size();
	}
	
	/**
	 * This method returns the player at index of the list of current players
	 * @param index is the position a player in this list of players
	 * @return the player
	 */
	public User getPlayer(int index) {
		return players.get(index);
	}
	
	/**
	 * This method returns the criteria that was played as a number
	 * @return the criteria that was played as a number
	 */
	public int getAttributeNumberPlayed() {
		return this.attributeNumberPlayed;
	}

}
