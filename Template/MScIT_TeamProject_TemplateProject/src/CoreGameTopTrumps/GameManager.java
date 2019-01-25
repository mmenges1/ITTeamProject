package CoreGameTopTrumps;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
	int totalPlayers = 0;
	int totalTurns = 0;
	int totalRounds = 1;
	int lastWinner = 0;
	int startingPlayer;
	int playerTurn;
	int currentChoice;
	boolean roundOne = true;
	ArrayList<TurnStatsHelper> turnStats = new ArrayList<TurnStatsHelper>();
	ArrayList<Card> community = new ArrayList<Card>();
	ArrayList<User> players = new ArrayList<User>() ;

//	GameStats gameStatsData = new GameStats(0,0,0,0,0);

	TestLog testLog = new TestLog();

	//TEMP MAIN for testing
	public static void main(String[] args) {
		GameManager gm = new GameManager();



		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
		while(true) {
			playerChoice = gm.initialPlayerChoice();

			if(playerChoice == 1) {
				gm.displayPriviousGameStats();
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

//		if(choice == 1) {
//			return 1;
//		} else if (choice == 2) {
//			return 2;
//		}
//		return 3;
//	}

	//This is called by initialPLayerChoice, to be populated with database info
	private void displayPriviousGameStats() {
		PreviousStats  previousGamesStatistics = new PreviousStats();
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
			}else {
				players.add(new AIPlayer("AI "+i,new ArrayList<Card>(newdeck.subList(divideCount, divideCount + mainCardEach))));
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
	 * Methods focused on getting the user / AI players choice of card
	 */


	/*
	 * This method loops the playRound and getChardChoice
	 * Within these are the game logic
	 */
	private void manageTurn() {
		// i'm not sure what's counter for
//		int counter = 0; 

		do {
			//			counter ++;
//			for (int i = 0; i < this.players.size(); i++) {
//				if (this.players.get(i).userLoses()) {
//					this.players.remove(i);	
//				} else {
//					System.out.println(this.players.get(i).getName() + " to play next round.");
//				}
//				
//				if (gameOver()) {
//					break;
//				}
				
//			}
		}while(playRound(getCardChoice()));
	}

	/*
	 * This method gets the players choice.
	 * It uses lastWinner to determine if it is the users turn
	 *
	 * Currently the AI is a case of choosing a random number
	 */

	private int getCardChoice() {
//		for (int i = 0; i < this.players.size(); i++) {
//			if (this.players.get(i).userLoses()) {
//				this.players.remove(i);	
//			} else {
//				System.out.println(this.players.get(i).getName() + " to play next round.");
//			}
//			
//			if (gameOver()) {
//				
//				return 6;
////				break;
////			}
//		}
		Random r = new Random();
		this.startingPlayer = r.nextInt(4);  // generates random starting player
//		this.startingPlayer = r.nextInt(4) + 1;
		/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");
	
		int playerChoice;
		User currentAIOpponent;
		//Last winner stay at zero for now for testing, so that user is always
// the one controlling.
//
//		if(totalRounds == 1) {
//			lastWinner = 0;
//		}
//		int playerChoice = 0;
//		if(lastWinner ==0 && !players.get(0).userLoses()) {
//			playerChoice = getUserInput(); // I RECOMMEND just choosing an integer for testing! (There can be 200-400 rounds)
//		}else {
//			//Seperate method for AI choosing card goes here
//			playerChoice = r.nextInt(5) + 1;
//		}
//
//		totalRounds++;
//
//		return playerChoice;
		if (roundOne) {
			// if it's round one and the 0th index is chosen and the 0th index is a human
			if(this.startingPlayer == 0 && players.get(0) instanceof Human ) {	
				System.out.println(players.get(this.startingPlayer).getName() + " will make the first choice ! \n");
				playerChoice = getUserInput();
				this.currentChoice = this.startingPlayer;
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
				this.currentChoice = this.startingPlayer; // person to make next choice will be current player unless another player wins
				roundOne = false;
			}
		} // if the lastwinner is 0th index in players' list and the 0th index in that list is a human 
		else if (lastWinner == 0 && players.get(0) instanceof Human) {	
				System.out.println(this.players.get(this.lastWinner).getName() + " will choose the category for this round.  \n");
				playerChoice = getUserInput();
				this.currentChoice = lastWinner;
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
				this.currentChoice = lastWinner;
			}
		totalRounds++;
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
				+ players.get(0).showTopCard()
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
	 *   And also removes players who have an empty hand.
	 * it is phrased as "return !gameOver();" because the loop continues if the game is NOT over
	 * (its dependent on a true). if the gameOver() returns a true (aka --> the game is indeed over),
	 *  then this becomes a false which ends the loop!
	 */
	private boolean playRound(int cardChoice) {

//		gameStatsData.setNumberOfRoundsInGamePlusOne();

		// 1)
		turnStats.add(new TurnStatsHelper(totalTurns, cardChoice, this.players, this.currentChoice));
		int currentTurnStats = turnStats.size()-1;

		// 2)
		for(int i = 0; i < players.size(); i++ ) {
//			if(!turnStats.get(currentTurnStats).getPlayer(i).userLoses()) {
//				turnStats.get(currentTurnStats).addCardToCardsPlayed(players.get(i).getTopCard());
//				players.get(i).discardTopCard();
//			}
//		}
//		testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
			System.out.println(this.players.get(i).getName()); //checks who's cards are added to deck
			turnStats.get(currentTurnStats).addCardToCardsPlayed(this.players.get(i).getTopCard());	
			players.get(i).discardTopCard();
	}
		testLog.addCardsInPlay(turnStats.get(currentTurnStats).cardsPlayed);
		// 3)
		turnStats.get(currentTurnStats).determineWinner();
		int sum = 0;

		// 4)
		if(!turnStats.get(currentTurnStats).getIsDraw()) {
			System.out.println(lastWinner);
			lastWinner = turnStats.get(currentTurnStats).getWinner();
			System.out.println(lastWinner);
//			players.get(lastWinner).addCards(turnStats.get(currentTurnStats).passCardsPlayed());
			for (int i = 0; i <= lastWinner; i++) {
				if (players.get(i).userLoses()) {
					sum = sum + 1;
				} 
			}
			if (lastWinner >0) {
				lastWinner = lastWinner - sum;
			}
			System.out.println(lastWinner);
			players.get(lastWinner).addCards(turnStats.get(currentTurnStats).passCardsPlayed());
			players.get(lastWinner).addCards(community);
			testLog.addCommunalDeck(community);
			community.clear();

		} else {
			// 5)
			System.out.println(lastWinner);
			lastWinner = turnStats.get(currentTurnStats).getWinner();
			for (int i = 0; i < lastWinner; i++) {
				if (players.get(i).userLoses()) {
					sum = sum + 1;
				} 
			}
			if (lastWinner >0) {
				lastWinner = lastWinner - sum;
			}
			System.out.println(lastWinner);
//			gameStatsData.setNumberOfDrawsInGamePlusOne();
			community.addAll(turnStats.get(currentTurnStats).passCardsPlayed());
			testLog.addCommunalDeck(community);
		}

		// 6)
		displayRoundSummery();
		
		System.out.println(players.get(lastWinner).getName() + " will choose the category for the next round.");
		
		if (players.get(0) instanceof Human) {
			System.out.println();
			System.out.println("Press enter to continue");
			Scanner s = new Scanner(System.in);
			s.nextLine();
			}

//		if (turnStats.get(currentTurnStats).getWinner() == 0) {
//			System.out.println(false);
//			gameStatsData.setNumberOfPlayerRoundWinsPlusOne();
//		}
//		else if (turnStats.get(currentTurnStats).getWinner() > 0) {
//			System.out.println(true);
//			gameStatsData.setNumberOfCPURoundWinsPlusOne();
//		}

		// 7)
		return !gameOver();
		
	}

	/* displayRoundSummery() displays the text that the user sees on the screen.
	*  it uses turnStats to get the necissary data
	*
	*  1) Intantiates the integer which represents the current turn within the turnStats arraylist
	*  2) Loops through players to print format their name, card, attribute and remaining deck size.
		The if condition can probibly be removed as now gameOver() removes players with no cards
	*  3) I offer this place as a suggestion to put the game stats object, to relay the number of points
		each player has
	*  4) This condition displays either the winning hand or declares a draw, & displays the size of the community deck

	*/
	private void displayRoundSummery() {

		// 1)
		int currentTurnStats = turnStats.size()-1;
		int cardPlayedIndex = 0;

		// 2)
		for(int i = 0; i < players.size(); i++) {
//			if(!turnStats.get(currentTurnStats).getPlayer(i).userLoses()) {

				System.out.printf("%s played....\t\t%s with %s\t\t\t\t(Remaining Cards : %d)\n",
						turnStats.get(currentTurnStats).getPlayer(i).getName(),
						turnStats.get(currentTurnStats).getUserCardName(i),
						turnStats.get(currentTurnStats).getAnyCardTopAttribute(i),
						players.get(i).getHandSize() );


				cardPlayedIndex++;
			}
//		}

		// 3)
		//TODO Implement a GameStats here to convey a points system for each player

		String roundString = "";

		// 4)
		if(turnStats.get(currentTurnStats).isDraw) {
			roundString = String.format("\nIts a draw!! Cards added to Community... "
					+ "\n\nCommunity deck size is currently: %d",
					community.size());
		} else {
			roundString = String.format("\n%s won using %s. "
					+ "\n\nCommunity deck size is currently: %d",
					players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getTopCardByAttribute(), community.size());
		}
		testLog.addCategorySelected(players.get(lastWinner).getName(), turnStats.get(currentTurnStats).getAnyCardTopAttribute(--cardPlayedIndex));

		System.out.println(roundString);
		for (int i = 0; i < players.size();i++) {
			if (this.players.get(i).getHandSize() == 0) {
				System.out.println("\n" + this.players.get(i).getName() + " has been knocked out!");
				this.players.remove(i);
				i--;
			} else {
				System.out.println("\n" + this.players.get(i).getName() + " to play next round!");
			}
		}
	}

	//This helps playRound
	// It checks if the game is over AND deletes players with no cards
	private boolean gameOver() {

//		System.out.println(gameStatsData.getNumberOfPlayerRoundWins());
//		System.out.println(gameStatsData.getNumberOfCPURoundWins());
//		System.out.println(gameStatsData.getNumberOfDrawsInGame());
//		System.out.println(gameStatsData.getNumberOfRoundsInGame());
//		System.out.println(gameStatsData.getGameWinner());

		int count = 0;

//		for(int i = 0; i< players.size(); i++){
//			if(players.get(i).userLoses()) {
//				players.remove(i);
//			}
//		}

		if(this.players.size() == 1) {

//			gameStatsData.setGameWinner(lastWinner);
			System.out.println(players.get(0) + " is the overall winner!!!");
			testLog.addWinner(players.get(0));
			testLog.printToFile();
//			gameStatsData.insertCurrentGameStatisticsIntoDatabase();
			return true;
		}

		return false;
	}

}
