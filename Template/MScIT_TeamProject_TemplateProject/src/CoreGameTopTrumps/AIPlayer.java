package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is responsible for bringing specific functionality to an ai player (i.e. to deliberately  play strongly).
 */
public class AIPlayer extends User {

	public AIPlayer(String name, ArrayList<Card> hand) {
		super(name, hand);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *  This method will return the name of the criterion that is associated with the highest value in an ai player's top card
	 * @param topCard - drawn card, top of the hand
	 * @returns the name of the criteria associated with the highest skill number
	 */
	public String getCriteriaName(Card topCard) {
		int highestValue = getHighestValueAttribute(topCard);
		int positioninCriteria = topCard.getIndexOfValue(highestValue);
		return topCard.getCriteriaName(positioninCriteria);
	}
	
	/**
	 * This method will return the index of a criterion in the list of criteria
	 * @param topCard the card at the top of a player's deck
	 * @return the index of that position
	 */
	public int getIndexofCriteriaWithHighestValue(Card topCard) {
		int maxValue = getHighestValueAttribute(topCard);
		return topCard.getIndexOfValue(maxValue) + 1;
	}
	
	/**
	 * Returns the value of the criteria that is highest in the top card of an ai player's deck
	 * @param topCard - drawn card, top of the hand
	 * @return the highest value for a top card 
	 */
	public int getHighestValueAttribute(Card topCard) {
		int maxValue = topCard.getAttribute(1);
		int noOfCriteria = topCard.getCriterias().size();
		for (int i = 2; i <= noOfCriteria; i++) {
			if (maxValue <= topCard.getAttribute(i)) {
				maxValue = topCard.getAttribute(i);
			}
		}
			return maxValue;
		}

	/**
	 * Returns a message stating the name of the card on top of a ai player's deck and the attribute they have chosen that is associated with the highest score in their top card and  
	 * @param topCard the card that is at the top of an ai player's deck
	 * @return the string message stating what ai has played
	 */
	public String playerChoosesMessage(Card topCard) {
		return this.name + " chooses attribute " + this.getIndexofCriteriaWithHighestValue(topCard) + ": " + this.getCriteriaName(topCard);
	}

	//Overloaded method - leave empty to choose the top card on the deck
	public int getHighestValueAttribute() {
		Card topCard = hand.get(0);
		
		int maxValue = topCard.getAttribute(1);
		int noOfCriteria = topCard.getCriterias().size();
		for (int i = 2; i <= noOfCriteria; i++) {
			if (maxValue <= topCard.getAttribute(i)) {
				maxValue = topCard.getAttribute(i);
			}
		}
			return maxValue;
		}
	
	/*
	 * (non-Javadoc)
	 * @see CoreGameTopTrumps.User#getTopCardName()
	 */
	public String getTopCardName() {
		return "The winning card is " + this.hand.get(0).getName();
	}
}
