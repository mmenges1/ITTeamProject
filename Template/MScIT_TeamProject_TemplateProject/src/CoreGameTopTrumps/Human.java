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