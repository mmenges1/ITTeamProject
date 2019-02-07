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
	PointsTracker points;
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
		points = new PointsTracker();
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
			
			if(startingPlayer == 0) {
				totalRounds++;
				return true;
			}
			
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
			points.increment(turnStats.get(currentTurnStats).getWinnerName());
			testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
		} else {
			// 5)
			gameStatsData.setNumberOfDrawsInGamePlusOne();
			community.addAll(turnStats.get(currentTurnStats).passCardsPlayed());
			testLog.addCommunalDeck(community);
			points.increment("Draw");
		}

		
		System.out.println("playRoundNew at end - lastWinner = " + lastWinner);
	}
	
	public void handleEndOfRound() {
		
		int currentTurnStats = turnStats.size()-1;


		
//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(--cardPlayedIndex));


//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(lastWinner));
		

//		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(lastWinner));

		if (turnStats.get(currentTurnStats).getWinner() == 0) {
//			System.out.println(false);
			gameStatsData.setNumberOfPlayerRoundWinsPlusOne();
		}
		else if (turnStats.get(currentTurnStats).getWinner() > 0) {
//			System.out.println(true);
			gameStatsData.setNumberOfCPURoundWinsPlusOne();
		}

		// 7)

		// TODO move this idea to the view
		/*
		System.out.println("\n" + players.get(lastWinner).getName() + " will choose the category for the next round.");
		String command = null;
		if (players.get(0) instanceof Human) {
			System.out.println();
			System.out.println("Press enter to continue or QUIT to exit the game early");
			Scanner s = new Scanner(System.in);
			 command = s.nextLine();
			}
			
		if (players.get(0) instanceof Human && command.equals("QUIT")) {
			return false;
		} else {
		*/


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
		boolean gameOver; 
		
//		System.out.println("gameOver - lastWinner = " + lastWinner);
		
		int adjustLastWinner = 0;
		int counter = 0;

		while(playersIterator.hasNext()){
			counter++;

			User p = playersIterator.next();

			if(p.userLoses()) {
//				System.out.println("gameOver() removing user" + p);
				if (p instanceof Human) {
					
					/* TODO: Move this idea to the view
					System.out.println("\nYou are out of cards! AI taking over ");
					InputReader in = new InputReader();
					in.pressEnter();
					*/
				}
				playersIterator.remove();
				
				// adjustLastWinner counts the number of players being removed BEFORE the iterator gets to the winner
				// if a player is being removed and the counter has not passed the index of the last winner
				// then incrment adjustLastWinner.
				
				if(counter<=lastWinner) {
					adjustLastWinner++;
				}
				
			}else {
//			System.out.println("gameOver() NOT removing user" + p);
			}
		}
		
//		System.out.printf("gameOver() - adjust last winner. lastWinner %d adjust %d\n\n", lastWinner, adjustLastWinner);
		
		lastWinner = lastWinner - adjustLastWinner;

		if(players.size() == 1) {
			this.scores.add(players.get(0));
			System.out.println(players.get(0).getName() + " is the overall winner!!!\n");
			String s = getRoundScores();
			System.out.println(s);
			gameStatsData.setGameWinner(lastWinner);
			testLog.addWinner(players.get(0));
//			gameStatsData.insertCurrentGameStatisticsIntoDatabase();
			
			gameOver = true;
			turnStats.get(turnStats.size()-1).setGameOver(gameOver);
			return gameOver;
		}
		
		gameOver = false;
		turnStats.get(turnStats.size()-1).setGameOver(gameOver);		
		return gameOver;
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
	
	// TODO: move this idea to the view? of use it to set variables that are accessable by the view
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
		if(players.get(lastWinner) instanceof Human) {
			this.currentChoice = userChooseAttribute;
			System.out.println("GameManger.setCurrentChoice is activated : " + userChooseAttribute);
		}
		
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
	
	public PointsTracker getPoints() {
		return points;
	}
	
	public static class PointsTracker {
		private int human;
		private int ai1;
		private int ai2;
		private int ai3;
		private int ai4;
		private int draw;
		
		public PointsTracker() {
			this.human = 0;
			this.ai1 = 0;
			this.ai2 = 0;
			this.ai3 = 0;
			this.ai4 = 0;
		}
		
		//increment player by name, because the player index changes as game progresses
		public void increment(String name) {
			if(name.equals("You")) {
				human++;
			} else if(name.equals("AI 1")){
				ai1++;
			} else if(name.equals("AI 2")){
				ai2++;
			} else if(name.equals("AI 3")){
				ai3++;
			} else if(name.equals("AI 4")){
				ai4++;
			} else if(name.equals("Draw")){
				draw++;
			}
			
//			System.out.println("points toString from within object : " + this.toString());
		}
		
		// To String
		
		@Override
		public String toString() {
			return "PointsTracker [human=" + human + ", ai1=" + ai1 + ", ai2=" + ai2 + ", ai3=" + ai3 + ", ai4=" + ai4
					+ ", draw=" + draw + "]";
		}
		
		// getters and setters for Jackson

		public int getHuman() {
			return human;
		}

		public void setHuman(int human) {
			this.human = human;
		}

		public int getAi1() {
			return ai1;
		}

		public void setAi1(int ai1) {
			this.ai1 = ai1;
		}

		public int getAi2() {
			return ai2;
		}

		public void setAi2(int ai2) {
			this.ai2 = ai2;
		}

		public int getAi3() {
			return ai3;
		}

		public void setAi3(int ai3) {
			this.ai3 = ai3;
		}

		public int getAi4() {
			return ai4;
		}

		public void setAi4(int ai4) {
			this.ai4 = ai4;
		}

		public int getDraw() {
			return draw;
		}

		public void setDraw(int draw) {
			this.draw = draw;
		}
		
		
		
	}
		
}

