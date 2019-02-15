package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Random;

import CoreGameTopTrumps.GameStats.PointsTracker;
import java.util.Iterator;

/**
 * This class is responsible for managing the game activities, round by round.
 */

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
	TurnStatsHelper turnStatsHelper;
	ArrayList<Card> community;
	ArrayList<User> players;
	GameStats gameStatsData;
	private TestLog testLog;
	
	/**
	 * Instantiates variables and deals the cards to players
	 * @param numberOfAIPlayers
	 */
	public void deal(int numberOfAIPlayers) {
		totalRounds = 0;
		gameStatsData = new GameStats(0,0,0,0);
		community = new ArrayList<Card>();
		players = new ArrayList<User>() ;
		testLog = new TestLog();

		Deck d = new Deck();
		ArrayList<Card> newdeck = d.createDeck("StarCitizenDeck.txt");
		testLog.addInitialDeck(d.startingDeck());
		testLog.addShuffledDeck(newdeck);
		totalPlayers = 1 + numberOfAIPlayers;


		int cardsEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;
		int divideCount = 0;


		for(int i = 0; i < totalPlayers; i++) {
			if(i==0) {
				players.add(new Human("You", new ArrayList<Card>(newdeck.subList(divideCount,divideCount + cardsEach))));
			}else {
				players.add(new AIPlayer("AI "+i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + cardsEach))));
			}

			divideCount += cardsEach;
		}

		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
			divideCount++;
		}

		testLog.addPlayerDeck(players);

	}

	/**
	 * This method determines the player who'll choose the attribute of each round
	 * @return true if human is to play
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

		if((lastWinner == 0 && players.get(0) instanceof Human)) {
			return true;
		}

		return false;
	}

	/**
	 * Retrieves the ai players choice if they are to play
	 */
	public void applyAICardChoice() {
		// condition to check that this is not effective if the last winner (therefore current chooser) is a human
		// Allows AI to play
		if(!(players.get(lastWinner) instanceof Human)) {
			currentChoice = players.get(lastWinner).getIndexofCriteriaWithHighestValue(players.get(lastWinner).getTopCard());// - 1;
		}
	}

	/**
	 * Responsible for handling each round.
	 */
	public void playRound() {

		gameStatsData.setNumberOfRoundsInGamePlusOne();

		turnStatsHelper = new TurnStatsHelper(currentChoice, this.players, lastWinner);

		for(int i = 0; i < players.size(); i++ ) {
			turnStatsHelper.addPlayerHandSize(this.players.get(i).getHandSize());
			turnStatsHelper.addCardToCardsPlayed(this.players.get(i).getTopCard());
			players.get(i).discardTopCard();
		}
		testLog.addCardsInPlay(turnStatsHelper);
		testLog.addPlayerChoice(turnStatsHelper);
		turnStatsHelper.determineWinner();


		if(!turnStatsHelper.getIsDraw()) {
			lastWinner = turnStatsHelper.getWinner();

			players.get(lastWinner).addCards(turnStatsHelper.passCardsPlayed());
			players.get(lastWinner).addCards(community);

			gameStatsData.incrementPoint(turnStatsHelper.getWinnerName());

			testLog.addCategorySelected(turnStatsHelper.getPlayerSize(), players, turnStatsHelper);
			testLog.addCommunalDeck(community);
			
			community.clear();
		} else {

			gameStatsData.setNumberOfDrawsInGamePlusOne();
			community.addAll(turnStatsHelper.passCardsPlayed());
			testLog.addCategorySelected(turnStatsHelper.getPlayerSize(), players, turnStatsHelper);
			testLog.addCommunalDeck(community);
		}
		
		turnStatsHelper.setCommunitySize(community.size());

	}
	/**
	 * This method is responsible for handling the end of the round
	 * by setting variables within helper classes
	 */
	public void handleEndOfRound() {
		for(int i = 0; i < players.size(); i++ ) {
			turnStatsHelper.addPlayerHandSize(this.players.get(i).getHandSize());
		}

		if (players.get(lastWinner) instanceof Human) {
			gameStatsData.setNumberOfPlayerRoundWinsPlusOne();
		} else {
			gameStatsData.setNumberOfCPURoundWinsPlusOne();
		}
		testLog.addPlayerDeck(players);
	}

	/**
	 * This method will remove any players that have ran out of cards, it'll also determine if the game is over - when all players are out
	 * @return true if game is finished - 1 remaining player
	 */
	public boolean gameOver() {

		Iterator<User> playersIterator = players.iterator();
		int adjustLastWinner = 0;
		int counter = 0;

		while(playersIterator.hasNext()){
			counter++;

			User p = playersIterator.next();

			if(p.userLoses()) {

				playersIterator.remove();

				// adjustLastWinner counts the number of players being removed BEFORE the iterator gets to the winner
				// if a player is being removed and the counter has not passed the index of the last winner
				// then incrment adjustLastWinner.

				if(counter<=lastWinner) {
					adjustLastWinner++;
				}

			}else {
			}
		}

		lastWinner = lastWinner - adjustLastWinner;

		if(players.size() == 1) {
			System.out.println(players.get(0).getName() + " is the overall winner!!!\n");
			gameStatsData.setGameWinner();
			testLog.addWinner(players.get(0));
			gameStatsData.insertCurrentGameStatisticsIntoDatabase();
			return true;
		}

		return false;
	}
	
	/**
	 * Creates a new PreviousStatis object and returns it
	 * @return PreviousStats
	 */

	public PreviousStats getPreviousGameStats() {
		PreviousStats  previousGamesStatistics = new PreviousStats();

		// MOCK DB Connection for front end testing
		//previousGamesStatistics.mockDBConnection();

		previousGamesStatistics.fetchPreviousGameData();

		return previousGamesStatistics;
	}
	
	/**
	 * Prints the testLog
	 */
	public void printLogFile() {
		testLog.printToFile();
	}

	/**
	 * Sets the current choice of the user, safe to call if it is not the users turn
	 * @param userChooseAttribute
	 */
	public void setCurrentChoice(int userChooseAttribute) {
		if(players.get(lastWinner) instanceof Human) {
			this.currentChoice = userChooseAttribute;
//			System.out.println("GameManger.setCurrentChoice is activated : " + userChooseAttribute);
		}

	}
	
	/**
	 * Returns the current players
	 * @return players
	 */
	public  ArrayList<User> getPlayers() {
		return players;
	}
	
	/**
	 * Returns the current TurnStatsHelper
	 * @return
	 */
	public TurnStatsHelper getTurnStatsHelper(){
		return turnStatsHelper;
	}
	
	/**
	 * Returns the community deck
	 * @return community
	 */
	public ArrayList<Card> getCommunity(){
		return community;
	}
	
	/**
	 * Returns the index of the last winner
	 * @return
	 */
	public int getLastWinner() {
		return lastWinner;
	}
	
	/**
	 * Returns number of rounds
	 * @return
	 */
	public int getTotalRounds() {
		return totalRounds;
	}
	
	/**
	 * Returns the current choice which either AI or Human has played
	 * @return
	 */
	public int getCurrentChoice() {
		return currentChoice;
	}
	
	/**
	 * Returns the PointsTracker object
	 * @return
	 */
	public PointsTracker getPoints() {
		return gameStatsData.getPoints();
	}
}
