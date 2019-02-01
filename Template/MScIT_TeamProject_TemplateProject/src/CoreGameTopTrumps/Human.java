package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Collections;

/*
 * This class is responsible for the human object during gameplay
 * The hand will be an arraylist of card objects
 * As the human will be making their own choices, the inherited methods are not essential
 */
public class Human extends User {
//	private String name;
//	private ArrayList<Card> hand;

	public Human(String name, ArrayList<Card> hand) {
		super(name, hand);
	}

//	public Human() {
//		super();
//	}
	
	public void setName(String name) {
		this.name = name;
	}
	/*
	 * (non-Javadoc)
	 * @see CoreGameTopTrumps.User#getTopCardName()
	 */
	@Override
	public String getTopCardName() {
		Card topCard = this.hand.get(0);
		String s =  "You drew " + topCard.getName() + "\n";
		s = s + topCard.viewCard();
		return s;
	}
	
	

//	public static void main(String[] args) {
//		Deck d = new Deck();
//		ArrayList<Card> list = new ArrayList<Card>();
//		ArrayList<Card> hand = new ArrayList<Card>();
//		Human kevin = new Human();
//		list = d.createDeck("StarCitizenDeck.txt");
//		//This method shuffles the list
//		Collections.shuffle(list);
//		for (int i = 0; i< 5; i++) {
//			Card c = list.get(i);
//			hand.add(c);
//		}
//		kevin.setHand(hand);
//		System.out.println(kevin.getHandSize());
//		//				kevin.displayEntireHand();
//		for (Card card : kevin.getHand()) {
//			System.out.println(card.viewCard());
//		}
//		while (kevin.getHandSize() != 0) {	
//			System.out.println(kevin.getHand().get(0).viewCard());
//			kevin.discardTopCard();
//			System.out.println(kevin);
//		}
//	}

	@Override
	String getCriteriaName(Card topCard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	int getIndexofCriteriaWithHighestValue(Card topCard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String playerChoosesMessage(Card topCard) {
		// TODO Auto-generated method stub
		return null;
	}
}