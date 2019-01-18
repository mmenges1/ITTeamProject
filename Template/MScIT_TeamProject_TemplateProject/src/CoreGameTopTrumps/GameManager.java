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
	
	ArrayList<TurnStats> turnStats = new ArrayList<TurnStats>();
	ArrayList<Card> community = new ArrayList<Card>();
	ArrayList<User> players;
	
	//TEMP MAIN for testing
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		
		//Inputting the number of desired AI players.
		gm.deal(2);
		
		gm.manageTurn();
		
		//gm.displayStats or choices
		
	}
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
	 * Method for dealing the new deck of cards evenly. It is already shuffled;
	 */
	private void deal(int numberOfAIPlayers) {		
		Deck d = new Deck();
		ArrayList<Card> newdeck = d.createDeck("StarCitizenDeck.txt");		
		players = new ArrayList<User>();		
		totalPlayers = 1 + numberOfAIPlayers;
		
		/* Divide up deck
		 * 
		 * mainCardEach does integer division on the  size of the 
		 * deck to count the amount of cards which evenly divide between all players
		 * 
		 * remainderCards captures the number remainder cards to be shared
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
		
		
		// this loop distributes the spare cards one by one. players[0] is the user
		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
			divideCount++;
		}
		
		//Test printing!!
		
//		for(User p : players) {
//			System.out.println("This is the test print - Game Manager : " + p.getName());
//			p.displayEntireHand();
//		}
		
	}
	
	/*
	 * Methods focused on getting the user / AI players choice of card
	 */
	
	private int getCardChoice() {
		Random r = new Random();
		
		
		//This will stay at zero for now for testing, so that user is always 
		// the one controlling
		lastWinner = 0;
		
		int playerChoice = 0;
		if(lastWinner ==0 && !players.get(0).userLoses()) {			
			playerChoice = getUserInput();
		}else {
			//temporary shitty AI for now, can replace with a function from within AiPlayer
			//Seperate method for AI choosing card goes here
			playerChoice = r.nextInt(5) + 1;
		}
		
		return playerChoice;
	}
	
	//This class helps getCardChoice()
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
	private boolean playRound(int cardChoice) {
		//The actual stuff goes here
		
		turnStats.add(new TurnStats(totalTurns, cardChoice));
		
		
		
		for(int i = 0; i < players.size(); i++ ) {
			//This accesses the most recently added TurnStats object, and puts the players top cards into it
//			System.out.println(players.get(i).getTopCard().viewCard());	
			
			
			
			turnStats.get(turnStats.size()-1).addCardToCardsPlayed(players.get(i).getTopCard());
			
			//display players hand size
//			System.out.printf("   Player %d deck size is %d ", i, players.get(i).getHandSize());
			
			players.get(i).discardTopCard();
		}
		
		//to string for turn stats
//		System.out.println(turnStats.get(turnStats.size()-1));
		
//		System.out.println(" playRound");
		//Find the winner or if its a draw
//		System.out.println("Winner method returns: " + turnStats.get(turnStats.size()-1).determineWinner());;
		
		//If it is not a draw, then put the cards played and community into the winners hand;
		if(!turnStats.get(turnStats.size()-1).getIsDraw()) {
			
			
			lastWinner = turnStats.get(turnStats.size()-1).getWinner();
			
//			System.out.printf("playRound: LastWinner = %d turnstats %d\n", lastWinner, turnStats.get(turnStats.size()-1).getWinner());
			
			players.get(lastWinner).addCards(turnStats.get(turnStats.size()-1).passCardsPlayed());
			
			//pass the community cards and clear
			players.get(lastWinner).addCards(community);			
			community.clear();
		} else {
			//lastWinner does not change if there is a draw
			
			community.addAll(turnStats.get(turnStats.size()-1).passCardsPlayed());
		}
		
		System.out.println(turnStats.get(turnStats.size()-1).getRoundString() + "\n\n");
		
		// If its NOT game over = true. If this returns false then the game loop is stopped
		return !gameOver();
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
