package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Random;

public class GameManager {
	int totalPlayers = 0;
	int totalTurns = 0;
	int totalRounds = 0;
	int lastWinner = 0;
	int startingPlayer;
	int playerTurn;
	int currentChoice;
	boolean roundOne = true;
	ArrayList<TurnStatsHelper> turnStats = new ArrayList<TurnStatsHelper>();
	ArrayList<Card> community = new ArrayList<Card>();
	ArrayList<User> players = new ArrayList<User>() ;
	ArrayList<User> scores = new ArrayList<User>();

	GameStats gameStatsData = new GameStats(0,0,0,0,0);

	static TestLog testLog = new TestLog();


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
	public void deal(int numberOfAIPlayers) {
		Deck d = new Deck();
		ArrayList<Card> newdeck = d.createDeck("StarCitizenDeck.txt");
		testLog.addInitialDeck(d.startingDeck());
		testLog.addShuffledDeck(newdeck);
		players = new ArrayList<User>();
		totalPlayers = 1 + numberOfAIPlayers; //number of AI players should be 4 in CLI and
		// any number between 1 and 4 for GUI

		// 1)
		int mainCardEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;

		int divideCount = 0;

		// 2)
		for(int i = 0; i < totalPlayers; i++) {
			if(i==0) {
				players.add(new Human("You", new ArrayList<Card>(newdeck.subList(divideCount,divideCount + mainCardEach))));
				players.get(i).setName("You");
			}else {
				players.add(new AIPlayer("AI "+i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + mainCardEach))));
				players.get(i).setName("AI " + i);
			}

			divideCount += mainCardEach;
		}


		// 3)
		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
			divideCount++;
		}

		//test log player's decks
			testLog.addPlayerDeck(players);

	}


	
	/*
	 * Refactor - this method determins who the next player is - returning a true if the next player is human
	 */
	

	public boolean determinNextPlayer() {
		Random r = new Random();
		
		
		if(totalRounds == 0) {
			startingPlayer = r.nextInt(totalPlayers);
			lastWinner = startingPlayer;
			
			if(startingPlayer == 0) return true;
		}
		
		totalRounds++;
		
		System.out.println("determinNextPlayer = " + lastWinner + " total rounds " + totalRounds);
		
		// If true that lastWinner == 0 AND player[0] is human
		if((lastWinner == 0 && players.get(0) instanceof Human)) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * Refactoring! get the AI to choose their card
	 */
	
	public void applyAICardChoice() {
		// condition to check that this is not effective if the last winner (therefore current chooser) is a human
		// Allows AI to play
		if(!(players.get(lastWinner) instanceof Human)) {			
			currentChoice = players.get(lastWinner).getIndexofCriteriaWithHighestValue(players.get(lastWinner).getTopCard());// - 1; 
			System.out.println("applyAICardChoice TRUE = " + currentChoice);
		}
		
		

	}

//	private int getCardChoice() {
//		Random r = new Random();
//		// generate a random number between 0 and 4 to assign a starting player
//		this.startingPlayer = r.nextInt(4);   
//		/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");
//	
//		int playerChoice;
//		User currentAIOpponent;
//		//Last winner stay at zero for now for testing, so that user is always
//// the one controlling.
////
////		if(totalRounds == 1) {
////			lastWinner = 0;
////		}
////		int playerChoice = 0;
////		if(lastWinner ==0 && !players.get(0).userLoses()) {
////			playerChoice = getUserInput(); // I RECOMMEND just choosing an integer for testing! (There can be 200-400 rounds)
////		}else {
////			//Seperate method for AI choosing card goes here
////			playerChoice = r.nextInt(5) + 1;
////		}
//
//		if (roundOne) {
//			// if it's round one and the 0th index is chosen and the 0th index is a human
//			if(this.startingPlayer == 0 && players.get(0) instanceof Human ) {	
//				System.out.println(players.get(this.startingPlayer).getName() + " will make the first choice ! \n");
//				playerChoice = getUserInput();
//				this.currentChoice = this.startingPlayer;
//				roundOne = false;
//			} 
//			// if it's round one and a number between 1-4 is chosen
//			else {
//				currentAIOpponent = this.players.get(this.startingPlayer); // current ai player from 1-4
//				System.out.println(currentAIOpponent.getName() + " will make the first choice!  \n");
//				Card topCard = currentAIOpponent.getTopCard(); // that ai player's top card
//				playerChoice = currentAIOpponent.getIndexofCriteriaWithHighestValue(topCard); 
//				if (this.players.get(0) instanceof Human) {
//					System.out.println("Here is the top card of your deck: ");
//					System.out.println(this.players.get(0).showTopCard()+ "\n"); //show human player's top card even when ai is choosing
//				}
//				System.out.println(currentAIOpponent.playerChoosesMessage(topCard)); // prints the category that ai has chosen (i.e. highest in their card)
//				System.out.println();
//				this.currentChoice = this.startingPlayer; // person to make next choice will be current player unless another player wins
//				roundOne = false;
//			}
//		} // if the lastwinner is 0th index in players' list and the 0th index in that list is a human 
//		else if (lastWinner == 0 && players.get(0) instanceof Human) {	
//				System.out.println("\n" + this.players.get(this.lastWinner).getName() + " will choose the category for this round.  \n");
//				playerChoice = getUserInput();
//				this.currentChoice = lastWinner;
//			}
//			else {
//				currentAIOpponent = this.players.get(lastWinner);
//				System.out.println("\n" + currentAIOpponent.getName() + " will choose the category for this round  \n.");
//				Card topCard = currentAIOpponent.getTopCard();
//				playerChoice = currentAIOpponent.getIndexofCriteriaWithHighestValue(topCard);
//				if (this.players.get(0) instanceof Human) {
//					System.out.println("Here is the top card of your deck: ");
//					System.out.println(this.players.get(0).showTopCard()+ "\n"); //show human player's top card even when ai is choosing
//				}
//				System.out.println(currentAIOpponent.getName() + " has chosen " + currentAIOpponent.getCriteriaName(topCard));
//				System.out.println();
//				this.currentChoice = lastWinner;
//			}
//		totalRounds++;
//		return playerChoice;
//	}


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
	 *   And also removes players who have an empty hand.
	 * it is phrased as "return !gameOver();" because the loop continues if the game is NOT over
	 * (its dependent on a true). if the gameOver() returns a true (aka --> the game is indeed over),
	 *  then this becomes a false which ends the loop!
	 */
	
	
	/* Refactor
	 * The playRoundNew will be split into 3 parts
	 * 
	 * handle game logic
	 * 
	 * _pass turnstats etc to view
	 * 
	 * handle quitting
	 * 
	 * 
	 */
	
	public void playRoundNew() {

		gameStatsData.setNumberOfRoundsInGamePlusOne();
		
		System.out.println("playRoundNew at start - lastWinner = " + lastWinner);

		// 1)
		turnStats.add(new TurnStatsHelper(totalTurns, currentChoice, this.players, this.currentChoice));

		// 1)
//		turnStats.add(new TurnStatsHelper(totalRounds, cardChoice, players));
		int currentTurnStats = turnStats.size()-1;

		// 2)
		for(int i = 0; i < players.size(); i++ ) {
//		testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
//			System.out.println(this.players.get(i).getName()); //checks who's cards are added to deck
			turnStats.get(currentTurnStats).addPlayerHandSize(this.players.get(i).getHandSize());
			turnStats.get(currentTurnStats).addCardToCardsPlayed(this.players.get(i).getTopCard());	
			players.get(i).discardTopCard();
	}
		testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
		// 3)
		turnStats.get(currentTurnStats).determineWinner();
		
		// 4)
		if(!turnStats.get(currentTurnStats).getIsDraw()) {
			lastWinner = turnStats.get(currentTurnStats).getWinner();
			
			players.get(lastWinner).addCards(turnStats.get(currentTurnStats).passCardsPlayed());
			players.get(lastWinner).addCards(community);
			players.get(lastWinner).incrementScore();
			testLog.addCommunalDeck(community);
			community.clear();
		testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
		} else {
			// 5)
			gameStatsData.setNumberOfDrawsInGamePlusOne();
			community.addAll(turnStats.get(currentTurnStats).passCardsPlayed());
			testLog.addCommunalDeck(community);
		}
		
		System.out.println("playRoundNew at end - lastWinner = " + lastWinner);
	}
	
	public void handleEndOfRound() {
		
		int currentTurnStats = turnStats.size()-1;

//		System.out.println("\n" + players.get(lastWinner).getName() + " will choose the category for the next round.");
//		String command = null;
//		if (players.get(0) instanceof Human) {
//			System.out.println();
//			System.out.println("Press enter to continue or QUIT to exit the game early");
//			Scanner s = new Scanner(System.in);
//			 command = s.nextLine();
//			}
		
//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(--cardPlayedIndex));


		
//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(lastWinner));
		

//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(lastWinner));

		if (turnStats.get(currentTurnStats).getWinner() == 0) {
			System.out.println(false);
			gameStatsData.setNumberOfPlayerRoundWinsPlusOne();
		}
		else if (turnStats.get(currentTurnStats).getWinner() > 0) {
			System.out.println(true);
			gameStatsData.setNumberOfCPURoundWinsPlusOne();
		}

		// 7)

//		if (players.get(0) instanceof Human && command.equals("QUIT")) {
//			return false;
//		} else {


	}
	

	//This helps playRound
	// It checks if the game is over AND deletes players with no cards
	/*
	 * This implements an iterator because by simply looping through the players ArrayList
	 * was causing it to randomly skip a player on rare occasions. The iterater is much safer.
	 * 
	 * 
	 * adjustLastWinner counts the number of players being removed BEFORE the iterator gets to the winner
	 * Since the winner's index will shift down players are removed, the last winner = lastWinner-adjustLastWinner
	 */
	public boolean gameOver() {
		//		System.out.println(gameStatsData.getNumberOfPlayerRoundWins());
		//		System.out.println(gameStatsData.getNumberOfCPURoundWins());
		//		System.out.println(gameStatsData.getNumberOfDrawsInGame());
		//		System.out.println(gameStatsData.getNumberOfRoundsInGame());
		//		System.out.println(gameStatsData.getGameWinner());

		Iterator<User> playersIterator = players.iterator();
		
		System.out.println("gameOver - lastWinner = " + lastWinner);
		
		int adjustLastWinner = 0;
		int counter = 0;

		while(playersIterator.hasNext()){
			counter++;

			User p = playersIterator.next();

			if(p.userLoses()) {
				System.out.println("gameOver() removing user" + p);
				if (p instanceof Human) {
					System.out.println("\nYou are out of cards! AI taking over ");
					InputReader in = new InputReader();
					in.pressEnter();
				}
				playersIterator.remove();
				
				// adjustLastWinner counts the number of players being removed BEFORE the iterator gets to the winner
				// if a player is being removed and the counter has not passed the index of the last winner
				// then incrment adjustLastWinner.
				
				if(counter<=lastWinner) {
					adjustLastWinner++;
				}
				
			}else {
			System.out.println("gameOver() NOT removing user" + p);
			}
		}
		
		System.out.printf("gameOver() - adjust last winner. lastWinner %d adjust %d\n\n", lastWinner, adjustLastWinner);
		
		lastWinner = lastWinner - adjustLastWinner;

		if(players.size() == 1) {
			this.scores.add(players.get(0));
			System.out.println(players.get(0).getName() + " is the overall winner!!!\n");
			String s = getRoundScores();
			System.out.println(s);
			gameStatsData.setGameWinner(lastWinner);
			testLog.addWinner(players.get(0));
//			gameStatsData.insertCurrentGameStatisticsIntoDatabase();

			return true;
		}
		return false;
	}
	
	//This is called by initialPLayerChoice, to be populated with database info
	public void displayPriviousGameStats() {
		PreviousStats  previousGamesStatistics = new PreviousStats();
	}

	public static void printLogFile() {
		testLog.printToFile();
	}
	/**
	 * Get the scores for a game, every time a player is eliminated, their details are added to a new arraylist of user for the purposes of keeping a record of their scores
	 * This arraylist is reverse sorted to get highest wins at the start
	 * @return a string representation of the scores
	 */
	public String getRoundScores() {
		this.totalRounds -= 1;
		int temp = 0;
		Collections.reverse(this.scores);
		String s = "Scores: \n";
		for (int i = 0; i < this.scores.size(); i++) {
			s = s + "\t" + this.scores.get(i).getName() + ": " +  this.scores.get(i).getScore() + " rounds\n";
			temp += this.scores.get(i).getScore();
		}
		s = s + "\t Drawn Games: " + (totalRounds - temp);
		return s;
	}

	public void setCurrentChoice(int userChooseAttribute) {
		this.currentChoice = userChooseAttribute;
	}
	
	public  ArrayList<User> getPlayers() {
		return players;
	}
	
	public ArrayList<TurnStatsHelper> getTurnStats(){
		return turnStats;
	}
	
	public ArrayList<Card> getCommunity(){
		return community;
	}
	
	public int getLastWinner() {
		return lastWinner;
	}
	
	public int getTotalRounds() {
		return totalRounds;
	}
	
	public int getCurrentChoice() {
		return currentChoice;
	}
}

