package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Arrays;

public class GameManager {
	int totalPlayers = 0;
	
	ArrayList<User> players;
	
	//TEMP MAIN for testing
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		
		gm.deal(2);
		
		
		
	}
	/*
	 * This method is for dealing the new deck of cards evenly. It is already shuffled;
	 */
	private void deal(int numberOfAIPlayers) {		
		Deck d = new Deck();
		ArrayList<Card> newdeck = d.createDeck("StarCitizenDeck.txt");		
		players = new ArrayList<User>();		
		totalPlayers = 1 + numberOfAIPlayers;
		
		/* Divide up deck
		 * 
		 */
		
		int mainCardEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;
		
		System.out.println("Manager - mainCardEach: " + mainCardEach + " remCard: " + remainderCards);
		
		int divideCount = 0;
		
		for(int i = 0; i < totalPlayers; i++) {	
			//divideCount always represents the first index in current sublist			
			//Human goes first! - however many AI players are added next
			if(i==0) {
				// add a new user plus the appropriate 'sublist' of the deck
				players.add(new Human("BobsHisName", new ArrayList<Card>(newdeck.subList(divideCount,divideCount + mainCardEach))));
			}else {
				players.add(new AIPlayer("Val "+i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + mainCardEach))));	
			}
			
			divideCount += mainCardEach;
			
		}
		
		//Test printing!!
		
		for(User p : players) {
			System.out.println("This is the test print - Game Manager");
			p.getName();
			p.displayEntireHand();
		}
		
		
		
		
	}

}
