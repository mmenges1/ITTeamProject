package CoreGameTopTrumps;

import java.util.ArrayList;

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
	
	//This is what the user will see which summarises the round
	String roundString;
	public int getcurrentChoice() {
		return this.currentChoice;
	}
	public void addCardToCardsPlayed(Card card) {
		cardsPlayed.add(card);
	}
	//What is turnNumber for?
	public TurnStatsHelper(int turnNumber, int attributeNumberPlayed, ArrayList<User> players, int currentChoice) {
		this.turnNumber = turnNumber;
		this.attributeNumberPlayed = attributeNumberPlayed;
		cardsPlayed = new ArrayList<Card>();
		this.players = new ArrayList<User>(players);
		this.currentChoice = currentChoice;
		playerHandSizes = new ArrayList<Integer>(); // used to work out difference between old and new hand size 
	}
	
	public void addPlayerHandSize(int handSize) {
		this.playerHandSizes.add(handSize);
	}
	public String returnDifferenceHandSize(User x, int index) {
		if (x.getHandSize() - this.getPlayerHandSize(index) < 0 ) {
			return "" + (x.getHandSize() - this.getPlayerHandSize(index) + " card") ;
		} else {
		return  "+" + (x.getHandSize() - this.getPlayerHandSize(index) + " cards") ; 
	}
	}
	
	public int getPlayerHandSize(int index) {
		return this.playerHandSizes.get(index);
	}
	
	public int getPlayerSize() {
		return this.players.size();
	}
	public void addPlayers(ArrayList<User> players) {
		this.players = new ArrayList<User>(players);
	}
	
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	public int getWinner() {
		return winner;
	}
	
	public boolean getIsDraw() {
		return isDraw;
	}
	
	public ArrayList<Card> passCardsPlayed(){
		return cardsPlayed;
	}
	
	public String getWinningCardName() {
		return this.attributeName;
	}

	
	// Returns -1 if its a draw, other wise the index of the winner
	// Loops through the chosen attribute of each top card and finds the highest/draw-y-est
	public int determineWinner() {
		int currentStat = 0;
		int highestStat = 0;		
		
		for(int i = 0; i < cardsPlayed.size(); i++) {
			
			currentStat = cardsPlayed.get(i).returnSelectedAttr(attributeNumberPlayed);
			
			if(currentStat > highestStat) {
				highestStat = currentStat;
				this.winner = i;
				isDraw = false;
			} else if (currentStat == highestStat) {
//				this.winner = -1;
				this.winner =currentChoice;
				isDraw = true;
			}
			
//			System.out.println("TurnStatsHelper.determineWinner currentStat : " + currentStat);
		}
		this.attributeName = getUserCardName(this.winner);
		return this.winner;		
	}

	@Override
	public String toString() {
		return "TurnStatsHelper [cardsPlayed=" + cardsPlayed + ", turnNumber=" + turnNumber + ", winner=" + winner
				+ ", attributeNumberPlayed=" + attributeNumberPlayed + ", isDraw=" + isDraw + ", roundString="
				+ roundString + "]";
	}
	
	public String getRoundString() {
		
		// This is just a basic version right now because to make it properly user friendly
		// will probibly involve a lot more work

		return String.format("\nPlayer %d won using Attribute %d. \n\nCommunity deck size is currently:%d \n\nThe cards that everyone played are:\n%s ", winner, attributeNumberPlayed, communitySize, cardsPlayed);
	}

	public void setCommunitySize(int size) {
		this.communitySize = size;		
	}
	
	public String getTopCardByAttribute() {
		return cardsPlayed.get(this.winner).getCriteriaName(this.attributeNumberPlayed-1 )+ " : " + cardsPlayed.get(this.winner).getAttribute(attributeNumberPlayed);
	}
	
	public String getAnyCardTopAttribute(int playerIndex) {
		return cardsPlayed.get(playerIndex).getCriteriaName(attributeNumberPlayed-1) + " : " + cardsPlayed.get(playerIndex).getAttribute(attributeNumberPlayed);
	}
	
	public String getUserCardName(int index) {
		return cardsPlayed.get(index).getName();
	}
	
	public int getCardPlayedSize() {
		return cardsPlayed.size();
	}
	
	public User getPlayer(int index) {
		return players.get(index);
	}
	
	public int getAttributeNumberPlayed() {
		return this.attributeNumberPlayed;
	}

}
