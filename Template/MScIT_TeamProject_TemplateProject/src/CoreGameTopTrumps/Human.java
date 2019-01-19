package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Collections;

/*
 * This class is responsible for the user playing the game
 * Hypothetically they might be asked for their name. Each human will also have a hand, which is their
 * allocated cards.
 * The hand will be an arraylist of card objects
 */
public class Human extends User {
	private String name;
	private ArrayList<Card> hand;

	public Human(String name, ArrayList<Card> hand) {
		super(name, hand);
	}
	
	public Human() {
		super();
	}

	// To display user and their number of cards
			public String toString() {
				return this.name + ": You have " + this.getHandSize() + " cards in your hand.";
			}
			
			//should either be sys.out or return the argument back??
			@Override
			public String getTopCardName() {
				Card topCard = this.hand.get(0);
				String s =  "You drew " + topCard.getName() + "\n";
				s = s + topCard.viewCard();
				return s;
			}

			public static void main(String[] args) {
				Deck d = new Deck();
				ArrayList<Card> list = new ArrayList<Card>();
				ArrayList<Card> hand = new ArrayList<Card>();
				Human kevin = new Human();
				list = d.createDeck("StarCitizenDeck.txt");
				//This method shuffles the list
				Collections.shuffle(list);
				for (int i = 0; i< 5; i++) {
					Card c = list.get(i);
					hand.add(c);
				}
				kevin.setHand(hand);
				System.out.println(kevin.getHandSize());
//				kevin.displayEntireHand();
				for (Card card : kevin.getHand()) {
					System.out.println(card.viewCard());
				}
				while (kevin.getHandSize() != 0) {	
					System.out.println(kevin.getHand().get(0).viewCard());
					kevin.discardTopCard();
					System.out.println(kevin);
//				}
				
			}
			}	
}