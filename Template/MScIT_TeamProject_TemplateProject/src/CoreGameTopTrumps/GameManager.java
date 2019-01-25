package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	int totalPlayers = 0;
	int totalTurns = 0;
	int totalRounds = 1;
	boolean roundOne = true;
	int lastWinner = 0;
	int startingPlayer;
	int playerTurn;
	String lastWinner2;
	int currentChoice;
	ArrayList<TurnStatsHelper> turnStats = new ArrayList<TurnStatsHelper>();
	ArrayList<Card> community = new ArrayList<Card>();
	ArrayList<User> players = new ArrayList<User>();
	
	//TEMP MAIN for testing
	public static void main(String[] args) {
		GameManager gm = new GameManager();
		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
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
		
		System.out.println("\nPress 1 for previous game stats, or 2 to start a new game, or 3 to quit");
		
		int choice = 0;
		
		while (true) {			
			choice = in.parseInt();
			if(choice <= 3 && choice > 0) {
				return choice; // with this line, don't need lines 68 - 74
//				break;
			} else {
				System.out.println("Please enter within the range");
			}
		}
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
		totalPlayers = 1 + numberOfAIPlayers;
		
		// 1)
		int mainCardEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;
		int divideCount = 0;
		
		// 2)
		for(int i = 0; i < totalPlayers; i++) {	
			if(i==0) {
				this.players.add(new Human("You", new ArrayList<Card>(newdeck.subList(divideCount,divideCount + mainCardEach))));
				this.players.get(0).setName("You");
			}else {
				this.players.add(new AIPlayer("AI "+ i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + mainCardEach))));	
				this.players.get(i).setName("AI " + i);
			}
			divideCount += mainCardEach;
		}
		
		// 3)
		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
//			players2.get(i).addSingleCard(newdeck.get(divideCount));
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
		do {
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).userLoses()) {
					players.remove(i);	
				}
			}
			if (gameOver()) {
				break;
			}
		} while(playRound(getCardChoice()));
	}

	/*
	 * This method gets the players choice.
	 * It uses lastWinner to determine if it is the users turn
	 * 
	 * Currently the AI is a case of choosing a random number
	 */
	
	private int getCardChoice() {
		Random r = new Random();		
		this.startingPlayer = r.nextInt(4);  // generates random starting player
//		this.startingPlayer = r.nextInt(4) + 1;
		User currentAIOpponent;
		
		/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");
		
		
		//Last winner stay at zero for now for testing, so that user is always 
		// the one controlling.
			
		int playerChoice = 0;
		if (roundOne) {
			// if it's round one and the 0th index is chosen and the 0th index is a human
			if(this.startingPlayer == 0 && players.get(0) instanceof Human ) {	
				System.out.println(players.get(this.startingPlayer).getName() + " will make the first choice ! \n");
				playerChoice = getUserInput(); // I RECOMMEND just choosing an integer for testing! (There can be 200-400 rounds)
				currentChoice = this.startingPlayer;
				roundOne = false;
			} 
			// if it's round one and a number between 1-4 is chosen
			else {
				//Seperate method for AI choosing card goes here
				currentAIOpponent = this.players.get(this.startingPlayer); // current ai player from 1-4
				System.out.println(currentAIOpponent.getName() + " will make the first choice!  \n");
				Card topCard = currentAIOpponent.getTopCard(); // that ai player's top card
				playerChoice = currentAIOpponent.getIndexofCriteriaWithHighestValue(topCard); 
				if (this.players.get(0) instanceof Human) {
					System.out.println(this.players.get(0).showTopCard()+ "\n"); //show human player's top card even when ai is choosing
				}
				System.out.println(currentAIOpponent.playerChoosesMessage(topCard)); // prints the category that ai has chosen (i.e. highest in their card)
				System.out.println();
				currentChoice = this.startingPlayer; // person to make next choice will be current player unless another player wins
				roundOne = false;
			}
		} // if the lastwinner is 0th index in players' list and the 0th index in that list is a human 
		else if (lastWinner == 0 && players.get(0) instanceof Human) {	
				System.out.println(this.players.get(this.lastWinner).getName() + " will choose the category for this round.  \n");
				playerChoice = getUserInput(); // I RECOMMEND just choosing an integer for testing! (There can be 200-400 rounds)
				currentChoice = lastWinner;
			}
		/*else if (players.get(0) instanceof Human) {
				//Separate method for AI choosing card goes here
				currentAIOpponent = this.players.get(lastWinner);	
				System.out.println(currentAIOpponent.getName() + " will choose the category for this round.  \n");
				Card topCard = currentAIOpponent.getTopCard();
				playerChoice = currentAIOpponent.getIndexofCriteriaWithHighestValue(topCard);
				if (!(this.players.get(0).getHandSize() < 0)) {
				System.out.println(this.players.get(0).showTopCard() + "\n");
				}
				currentChoice = lastWinner;
				System.out.println(currentAIOpponent.getName() + " has chosen " + currentAIOpponent.getCriteriaName(topCard));
				System.out.println();
				//			playerChoice = r.nextInt(5) + 1;
			}*/
			else {
				currentAIOpponent = this.players.get(lastWinner);
				System.out.println(currentAIOpponent.getName() + " will choose the category for this round  \n.");
				Card topCard = currentAIOpponent.getTopCard();
				playerChoice = currentAIOpponent.getIndexofCriteriaWithHighestValue(topCard);
				if (this.players.get(0) instanceof Human) {
					System.out.println(this.players.get(0).showTopCard()+ "\n"); //show human player's top card even when ai is choosing
				}
				System.out.println(currentAIOpponent.getName() + " has chosen " + currentAIOpponent.getCriteriaName(topCard));
				System.out.println();
				currentChoice = lastWinner;
			}
		totalRounds++;
		return playerChoice;
	}
	
	/*
	 * This Class helps getCardChoice by getting the user input
	 * It displays the current card on the top of their deck and asks the user to choose 
	 * which attribute to play with
	 */
	private int getUserInput() {
		InputReader reader = new InputReader();
		
		System.out.printf("Here is the card at the top of your deck...\n"
				+ players.get(0).showTopCard()
				+ "\nWhich attribute would you like to trump your enemies with?\n\nPlease type a number between 1 and 5 and press enter!\n");
		
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
		
//		if (gameOver()) {
//			return gameOver();
//		} else {
		// 1)turnstats if a list of turnstatshelpers
//		for (int i = 0; i < players.size(); i++) {
//			if (players.get(i).getHandSize() <= 0) {
//				players.remove(i);
//			}
//		}
		turnStats.add(new TurnStatsHelper(totalTurns, cardChoice, players, currentChoice));
		int currentTurnStats = turnStats.size()-1; 
		
		// 2)
		for(int i = 0; i < players.size(); i++) {
				System.out.println(players.get(i).getName());
				turnStats.get(currentTurnStats).addCardToCardsPlayed(players.get(i).getTopCard());	
				players.get(i).discardTopCard();
		}
		
		// 3)
		turnStats.get(currentTurnStats).determineWinner();
		int sum = 0;
		// 4)
		if(!turnStats.get(currentTurnStats).getIsDraw()) {	
			lastWinner = turnStats.get(currentTurnStats).getWinner();
			for (int i = 0; i < players.size(); i++) {
				if (players.get(i).userLoses()) {
					sum += 1;
				} 
			}
			if (lastWinner >0) {
				lastWinner -= sum;
			}
			players.get(lastWinner).addCards(turnStats.get(currentTurnStats).passCardsPlayed());
			players.get(lastWinner).addCards(community);			
			community.clear();
			
		} else {
			// 5)	
			lastWinner = turnStats.get(currentTurnStats).getWinner();
			community.addAll(turnStats.get(currentTurnStats).passCardsPlayed());
		}
		
		// 6) 
		displayRoundSummery();
//		if (gameOver()) {
//			return gameOver();
//		} 
//		else 
			if (players.get(0) instanceof Human) {
		System.out.println();
		System.out.println("Press enter to continue");
		Scanner s = new Scanner(System.in);
		s.nextLine();
		}
		
		
		// 7)
		return !gameOver();
	}
//	}
	
	
	private void displayRoundSummery() {
		String s = "";
		int currentTurnStats = turnStats.size()-1;
//		int cardPlayedIndex = 0; legacy code??
				
		for(int i = 0; i < players.size(); i++) {
//			if(!turnStats.get(currentTurnStats).getPlayer(i).userLoses()) {
//			if (turnStats.get(currentTurnStats).getPlayer(i).getHandSize() == 0) {
//				s = s + turnStats.get(currentTurnStats).getPlayer(i).getName() + "  is eliminated!  \n";
//				System.out.println(s);
//				this.players.remove(i);
//				break;
//			}
//			else if (turnStats.get(currentTurnStats).getPlayer(i).getHandSize() > 0) {
				System.out.printf("%s played....\t\t%s with %s\t\t\t\t(Remaining Cards : %d)\n", 
						turnStats.get(currentTurnStats).getPlayer(i).getName(), 
						turnStats.get(currentTurnStats).getUserCardName(i),
						turnStats.get(currentTurnStats).getAnyCardTopAttribute(i), 
						players.get(i).getHandSize());
						
//				if (turnStats.get(currentTurnStats).getPlayer(i).userLoses()) {
//					s = s + turnStats.get(currentTurnStats).getPlayer(i).getName() + "  is eliminated! + \n";
//				}
				
//			} 
//			cardPlayedIndex++; legacy code??
		}
		
		//TODO Implement a GameStats here to convey a points system for each player
		
		String roundString = "";
		
		if(turnStats.get(currentTurnStats).isDraw) {			
			roundString = String.format("\nIts a draw!! Cards added to Community... "
					+ "\n\nCommunity deck size is currently: %d", 
					community.size());			
		} else {
			roundString = String.format("\n%s won using %s. "
					+ "\n\nCommunity deck size is currently: %d", 
//					players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getTopCardByAttribute(), community.size());
					players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getUserCardName(lastWinner), community.size());
					
		}
		
		System.out.println(roundString);
		for (int i = 0; i < players.size();i++) {
			if (this.players.get(i).getHandSize() == 0) {
				System.out.println("\n" + this.players.get(i).getName() + " has been knocked out!");
			}
		}
	}
	
	//This helps playRound 
	// It checks if the game is over AND deletes players with no cards
	private boolean gameOver() {
		boolean gameIsOver = false;
		for (int i = 0 ; i < players.size(); i++) {
			if (players.get(i).getHandSize() == 40) {
				System.out.println(players.get(i).getName() + " is the overall winner!");
				gameIsOver = true;
			} else if (players.get(i).getHandSize() + this.community.size() == 40) {
				System.out.println(players.get(i).getName() + " is the overall winner!");
			gameIsOver =  true;
			}
		}
		return gameIsOver;
	}
}
