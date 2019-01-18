package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameManager {
	int totalPlayers = 0;
	int totalTurns = 0;
	int totalRounds = 0;
	int lastWinner = 0;
	int startingPlayer;
	int playerTurn;
	
	ArrayList<TurnStatsHelper> turnStats = new ArrayList<TurnStatsHelper>();
	ArrayList<Card> community = new ArrayList<Card>();
	ArrayList<User> players;
	
	//TEMP MAIN for testing
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		
		
		
		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n\n");
		while(true) {
			playerChoice = gm.initialPlayerChoice();
			
			if(playerChoice == 1) {
				System.out.println(gm.displayPriviousGameStats());
			} else if (playerChoice == 2){
				//Inputting the number of desired AI players.
				gm.deal(4);				
				gm.manageTurn();
			} else {
				System.out.println("Goodbye!");
				break;
			}
		}
		
	}
	
	/*
	 * The methods focus on managing the initial part of the game
	 */

	private int initialPlayerChoice() {
		
		InputReader in = new InputReader();
		
		
		System.out.println("Press 1 for previous game stats, or 2 to start a new game, or 3 to quit");
		
		int choice = 0;
		
		while (true) {			
			choice = in.parseInt();
			if(choice <= 3 && choice > 0) {
				break;
			} else {
				System.out.println("Please enter within the range");
			}
		}
		
		if(choice == 1) {
			return 1;
		} else if (choice == 2) {
			return 2;
		} 		
		return 3;
	}
	
	//This is called by initialPLayerChoice, to be populated with database info
	private String displayPriviousGameStats() {
		return "Put your object here\n";		
	}
	
	/*
	 * Method for dealing the new deck of cards evenly. It is already shuffled;
	 * It follows this order:
	 * 
	 * 1) mainCardEach gets the basic division of cards so that each players deck is even.
	 * remainderCards gets the summer of spare cards to be split up as evenply as possible.
	 * dividedCount is used to count the start and finish indexes of the newdeck,
	 * the cards are taken from the newdeck and shared between the players.
	 * 
	 * 2) loops though the newdeck and distributes each according to the above variables. 
	 * Human player always goes first
	 * 
	 * 3) this loop distributes the spare cards one by one. players[0] is the user, so the user 
	 * always gets a spare card in this setting
	 */
	private void deal(int numberOfAIPlayers) {		
		Deck d = new Deck();
		ArrayList<Card> newdeck = d.createDeck("StarCitizenDeck.txt");		
		players = new ArrayList<User>();		
		totalPlayers = 1 + numberOfAIPlayers;
		
		// 1)
		int mainCardEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;
		
		int divideCount = 0;
		
		// 2)
		for(int i = 0; i < totalPlayers; i++) {	
			if(i==0) {
				players.add(new Human("BobsHisName", new ArrayList<Card>(newdeck.subList(divideCount,divideCount + mainCardEach))));
			}else {
				players.add(new AIPlayer("Val "+i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + mainCardEach))));	
			}
			
			divideCount += mainCardEach;
		}
		
		
		// 3)
		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
			divideCount++;
		}
		
	}
	
	/*
	 * Methods focused on getting the user / AI players choice of card
	 */
	
	
	/*
	 * This method loops the playRound and getChardChoice
	 * Within these are the game logic
	 */
	private void manageTurn() {
		int counter = 0;
		
		while(playRound(getCardChoice())) {
			counter ++;
			
//			//This is here for testing
//			if(counter>=3) {
//				break;
//			}
		}
	}
	
	/*
	 * This method gets the players choice.
	 * It uses lastWinner to determine if it is the users turn
	 * 
	 * Currently the AI is a case of choosing a random number
	 */
	
	private int getCardChoice() {
		Random r = new Random();		
		
		//Last winner stay at zero for now for testing, so that user is always 
		// the one controlling.
		
		if(true/*totalRounds == 0*/) {
			lastWinner = 0;
		}
			
			
		int playerChoice = 0;
		if(lastWinner ==0 && !players.get(0).userLoses()) {			
			playerChoice = getUserInput();
		}else {
			//Seperate method for AI choosing card goes here
			playerChoice = r.nextInt(5) + 1;
		}
		
		return playerChoice;
	}
	
	/*
	 * This Class helps getCardChoice by getting the user input
	 * 
	 * It displays the current card on the top of their deck and asks the user to choose 
	 * which attribute to play with
	 */
	private int getUserInput() {
		InputReader reader = new InputReader();
		
		System.out.printf("Here is the card at the top of your deck...\n"
				+ players.get(0).showTopCardCriteria()
				+ "\nwhich attribute would you like to trump your enemies with?\n\nPlease type a number between 1 and 5 and press enter!\n");
		
		int choice = 0;
		
		while (true) {			
			choice = reader.parseInt();
			if(choice <= 5 && choice > 0) {
				break;
			} else {
				System.out.println("Please enter within the range");
			}
		}
		return choice;
	}
	
	/*
	 * Methods focused on conducting the round
	 */
	
	/*
	 * playRound() conducts the most central game logic
	 * It is helped by a TurnStatsHelper, which abstracts some of the logic away 
	 * 
	 * I have put numbers because the comments from within the method were so messy.
	 * It follows the below order:
	 * 
	 * 1) It starts by creating the TurnStatsHelper (henceforth: turnStats)
	 * 
	 * 2) It adds the top cards to turnStats, and removes them from players
	 * 
	 * 3) turnStats determines who the winner is or if its a draw
	 * 
	 * 4) If there is a winner, the winner gets the cards FROM turnStats and community
	 * and lastWinner gets updated
	 * 
	 * 5) else if its a draw, cards go to community, and lastWinner stays the same
	 * 
	 * 6) trigger displayRoundSummery() to print the key info to the player
	 * 
	 * 7) Finally, gameOver() is used to check if the game is over (only one player has cards)
	 * it is phrased as "return !gameOver();" because the loop continues if the game is NOT over 
	 * (its dependent on a true). if the gameOver() returns a true (aka --> the game is indeed over),
	 *  then this becomes a false which ends the loop! 
	 */
	private boolean playRound(int cardChoice) {
		int currentTurnStats = turnStats.size()-1;
		// 1)
		turnStats.add(new TurnStatsHelper(totalTurns, cardChoice));
		
		// 2)
		for(int i = 0; i < players.size(); i++ ) {			
			turnStats.get(currentTurnStats).addCardToCardsPlayed(players.get(i).getTopCard());			
			players.get(i).discardTopCard();
		}
		
		// 3)
		turnStats.get(currentTurnStats).determineWinner();
		
		// 4)
		if(!turnStats.get(currentTurnStats).getIsDraw()) {
			
			lastWinner = turnStats.get(currentTurnStats).getWinner();			
			players.get(lastWinner).addCards(turnStats.get(currentTurnStats).passCardsPlayed());
			
			players.get(lastWinner).addCards(community);			
			community.clear();
			
		} else {
			// 5)		
			community.addAll(turnStats.get(currentTurnStats).passCardsPlayed());
		}
		
		// 6) 
		displayRoundSummery();

		
		// 7)
		return !gameOver();
	}
	
	private void displayRoundSummery() {
		
		int currentTurnStats = turnStats.size()-1;
		
//		turnStats.get(currentTurnStats).setCommunitySize(community.size());
		
		String roundString = String.format(String.format("\n%s won using %s. "
				+ "\n\nCommunity deck size is currently:%d "
				+ "\n\nThe cards that everyone played are:\n%s ", 
				players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getTopCardByAttribute(), community.size(), cardsPlayed);
		
		System.out.println(turnStats.get(currentTurnStats).getRoundString() + "\n\n");
		
		
		
		System.out.println("Top Card Att test " + turnStats.get(currentTurnStats).getTopCardByAttribute() );
		
		//This loop shows how big the deck is for each player
		
		for(int i = 0; i < players.size(); i++) {
			System.out.printf("Player %d's deck size is %d\n", players.get(i).getName(), players.get(i).getHandSize());
		}
		
		System.out.println("\n\n");
	}
	
	//This helps playRound
	private boolean gameOver() {
		// counts how many users are OUT
		int count = 0;
		
		for(User p : players) {
			if(p.userLoses()) {
				
				count++;
			}
		}
		
		if(count == totalPlayers - 1) {
			return true;
		}
		
		return false;
	}

}
