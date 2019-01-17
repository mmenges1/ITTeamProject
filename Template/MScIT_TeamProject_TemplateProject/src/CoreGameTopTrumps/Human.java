package CoreGameTopTrumps;

import java.util.ArrayList;

/*
 * This class is responsible for the user playing the game
 * Hypothetically they might be asked for their name. Each human will also have a hand, which is their
 * allocated cards.
 * This will be an arraylist of card objects
 *
 */
public class Human extends User {
	private String name;
	private ArrayList<Card> hand;

	public Human(String name, ArrayList<Card> hand) {
		super(name, hand);
	}
	
	// To display user and their number of cards
			public String toString() {
				return this.name + ": You have " + this.hand.size() + " cards in your hand.";
			}

			@Override
			void getTopCardName() {
				System.out.println("You drew " + this.hand.get(0).getName());
			}
	
}