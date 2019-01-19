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
		this.hand = new ArrayList<Card>();
	}
	
	/**
	 *  
	 * @param topCard - drawn card, top of the hand
	 * @returns the name of the criteria associated with the highest skill number
	 */
	public String getCriteriaName(Card topCard) {
		int value = getHighestValueAttribute(topCard);
		int positioninCriteria = topCard.getIndexOfValue(value);
		return topCard.getAttributeName(positioninCriteria);
	}
	
	/**
	 * 
	 * @param topCard - drawn card, top of the hand
	 * @return the position of the criteria that is associated with the highest skill number
	 */
	public int getHighestValueAttribute(Card topCard) {
		int maxValue = topCard.getValueforAttribute(1);
		int noOfCriteria = topCard.getCriterias().size();
		for (int i = 1; i <= noOfCriteria; i++) {
			if (maxValue <= topCard.getValueforAttribute(i)) {
				maxValue = topCard.getValueforAttribute(i);
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
		list = d.createDeck("StarCitizenDeck.txt");
		//This method shuffles the list
		Collections.shuffle(list);
		Card c = list.get(0);
		Card c2 = list.get(1);
		hand.add(c);
		c.viewCard();
		hand.add(c2);
		AIPlayer ai = new AIPlayer();
		System.out.println("ai chooses " + ai.getCriteriaName(c));
	}
}
