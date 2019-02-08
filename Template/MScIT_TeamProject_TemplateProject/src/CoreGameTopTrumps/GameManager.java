package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Random;

import CoreGameTopTrumps.GameStats.PointsTracker;

import java.util.Iterator;

public class GameManager {
	int totalPlayers = 0;
	int totalTurns = 0;
	int totalRounds = 0;
	int lastWinner = 0;
	int startingPlayer;
	int playerTurn;
	int currentChoice;
	TurnStatsHelper turnStatsHelper;
	ArrayList<Card> community;
	ArrayList<User> players;
	GameStats gameStatsData;

	private TestLog testLog;

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


		int mainCardEach = newdeck.size() / totalPlayers;
		int remainderCards = newdeck.size() % totalPlayers;
		int divideCount = 0;


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



		for(int i = 0; i < remainderCards; i++) {
			players.get(i).addSingleCard(newdeck.get(divideCount));
			divideCount++;
		}

		//test log player's decks
			testLog.addPlayerDeck(players);

	}


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

		if((lastWinner == 0 && players.get(0) instanceof Human)) {
			return true;
		}
		
		return false;
	}
	
	
	public void applyAICardChoice() {
		// condition to check that this is not effective if the last winner (therefore current chooser) is a human
		// Allows AI to play
		if(!(players.get(lastWinner) instanceof Human)) {			
			currentChoice = players.get(lastWinner).getIndexofCriteriaWithHighestValue(players.get(lastWinner).getTopCard());// - 1; 
			System.out.println("applyAICardChoice TRUE = " + currentChoice);
		}
	}

	
	public void playRoundNew() {

		gameStatsData.setNumberOfRoundsInGamePlusOne();
		
		System.out.println("playRoundNew at start - lastWinner = " + lastWinner);

		// TODO: update this constructor. some are not used
		turnStatsHelper = new TurnStatsHelper(totalTurns, currentChoice, this.players, this.currentChoice, lastWinner);

		for(int i = 0; i < players.size(); i++ ) {
			turnStatsHelper.addPlayerHandSize(this.players.get(i).getHandSize());
			turnStatsHelper.addCardToCardsPlayed(this.players.get(i).getTopCard());	
			players.get(i).discardTopCard();
		}
		testLog.addCardsInPlay(turnStatsHelper.cardsPlayed);

		turnStatsHelper.determineWinner();
		

		if(!turnStatsHelper.getIsDraw()) {
			lastWinner = turnStatsHelper.getWinner();
			
			players.get(lastWinner).addCards(turnStatsHelper.passCardsPlayed());
			players.get(lastWinner).addCards(community);
			
			gameStatsData.incrementPoint(turnStatsHelper.getWinnerName());
			
			testLog.addCardsInPlay(turnStatsHelper.cardsPlayed);
			testLog.addCommunalDeck(community);
			
			community.clear();
		} else {

			gameStatsData.setNumberOfDrawsInGamePlusOne();
			community.addAll(turnStatsHelper.passCardsPlayed());
			testLog.addCommunalDeck(community);
		}

		
		System.out.println("playRoundNew at end - lastWinner = " + lastWinner);
	}
	
	public void handleEndOfRound() {
		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStatsHelper.getTopCardByAttribute());
		
		if (players.get(lastWinner) instanceof Human) {
			gameStatsData.setNumberOfPlayerRoundWinsPlusOne();
		} else {
			gameStatsData.setNumberOfCPURoundWinsPlusOne();
		}
	}
	

	public boolean gameOver() {

		Iterator<User> playersIterator = players.iterator();
		boolean gameOver; 
		
		
		int adjustLastWinner = 0;
		int counter = 0;

		while(playersIterator.hasNext()){
			counter++;

			User p = playersIterator.next();

			if(p.userLoses()) {
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
			}
		}
		
		lastWinner = lastWinner - adjustLastWinner;

		if(players.size() == 1) {
			System.out.println(players.get(0).getName() + " is the overall winner!!!\n");
			gameStatsData.setGameWinner();
			testLog.addWinner(players.get(0));
			gameStatsData.insertCurrentGameStatisticsIntoDatabase();
			
			gameOver = true;
			turnStatsHelper.setGameOver(gameOver);
			return gameOver;
		}
		// TODO: make more elegent
		gameOver = false;
		turnStatsHelper.setGameOver(gameOver);		
		return gameOver;
	}

	public PreviousStats getPreviousGameStats() {
		PreviousStats  previousGamesStatistics = new PreviousStats();

		// MOCK DB Connection for front end testing
		//previousGamesStatistics.mockDBConnection();
		
		previousGamesStatistics.fetchPreviousGameData();
		
		return previousGamesStatistics;
	}

	public void printLogFile() {
		testLog.printToFile();
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
	
	public TurnStatsHelper getTurnStatsHelper(){
		return turnStatsHelper;
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
		return gameStatsData.getPoints();
	}
}

