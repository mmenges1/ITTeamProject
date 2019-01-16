package CoreGameTopTrumps;

import java.util.ArrayList;

// this will be the ai player

public class AIPlayer extends User {

	private String name;
	private ArrayList<Cards> hand;
	
	public AIPlayer(String name, ArrayList<Cards> hand) {
		super(name, hand);
	}
	
	// if ai win, this method will print their card name
	@Override
	void getTopCardName() {
		System.out.println("The winning card is " + this.hand.get(0).getName());
	}
}
