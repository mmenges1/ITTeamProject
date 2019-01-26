package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Collections;

// this will be the ai player

public class AIPlayer extends User {
	private String name;
	private ArrayList<Card> hand;
	

	public AIPlayer(String name, ArrayList<Card> hand) {
		super(name, hand);
	}
	
	public AIPlayer() {
		super();
	}
	
	/**
	 *  
	 * @param topCard - drawn card, top of the hand
	 * @returns the name of the criteria associated with the highest skill number
	 */
	public String getCriteriaName(Card topCard) {
		int highestValue = getHighestValueAttribute(topCard);
		int positioninCriteria = topCard.getIndexOfValue(highestValue);
		return topCard.getCriteriaName(positioninCriteria);
	}
	
	public int getIndexofCriteriaWithHighestValue(Card topCard) {
		int maxValue = getHighestValueAttribute(topCard);
		return topCard.getIndexOfValue(maxValue) + 1;
	}
	
	/**
	 * 
	 * @param topCard - drawn card, top of the hand
	 * @return the position of the criteria that is associated with the highest skill number
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

	// Displays a message stating which attribute was chosen by the AI
//	@Override
//	public String playerChoosesMessage(Card topCard) {
//		return this.name + " chooses attribute " + this.getIndexofCriteriaWithHighestValue(topCard) + ": " + this.getCriteriaName(topCard);
//	}

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
	
	// if ai win, this method will print their card name
	@Override
	String getTopCardName() {
		return "The winning card is " + this.hand.get(0).getName();
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		ArrayList<Card> list = new ArrayList<Card>();
		ArrayList<Card> hand = new ArrayList<Card>();
		AIPlayer ai = new AIPlayer();
		list = d.createDeck("StarCitizenDeck.txt");
		//This method shuffles the list
		Collections.shuffle(list);
		Card c = list.get(0);
		Card c2 = list.get(1);
		hand.add(c);
		hand.add(c2);
		ai.setHand(hand);
		while (ai.getHandSize() != 0) {
			Card topCard = ai.getTopCard();
			System.out.println(topCard.viewCard());
			System.out.println("AI chooses attribute " + ai.getIndexofCriteriaWithHighestValue(topCard) + ": " + ai.getCriteriaName(topCard) );
			ai.discardTopCard();
			System.out.println("AI has " + ai.getHandSize() + " cards remaining");
			System.out.println();
		}
	}
}
