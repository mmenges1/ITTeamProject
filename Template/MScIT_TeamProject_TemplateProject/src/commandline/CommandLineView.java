package commandline;

import java.util.ArrayList;

import CoreGameTopTrumps.GameManager;
import CoreGameTopTrumps.Human;
import CoreGameTopTrumps.InputReader;
import CoreGameTopTrumps.TurnStatsHelper;
import CoreGameTopTrumps.User;

/**
 * This class is responsible for retrieving and presenting round events for the user playing CLI format
 *
 */
public class CommandLineView {

	private ArrayList<User> players;
	private TurnStatsHelper turnStats;
	GameManager gm;

	public static void main(String[] args) {
		CommandLineView CLIview = new CommandLineView();
		CLIview.manageTurn();

	}

	public CommandLineView(){
		gm = new GameManager();
	}

	/**
	 * This method handles the initial selection round
	 */
	public void manageTurn() {

		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
		while(true) {			
			playerChoice = initialPlayerChoice();

			//get player choice within a set player choice
			if(playerChoice == 1) {
				displayPreviousGameStats();
			} else if (playerChoice == 2){
				dealAndPlay(4);
			} else {
				System.out.println("Goodbye!");
				break;
			}
		}

	}

	/**
	 * Display previous game stats if user chooses the 1st option in CLI.
	 */
	public void displayPreviousGameStats() {
		//Currently set to a mock method!!
		String previousStats = gm.getPreviousGameStats().toString();
		System.out.println(previousStats);
	}

	/**
	 * Play the game with appropriate number of players
	 * @param players 
	 */
	public void dealAndPlay(int players) {
		gm.deal(players);
		playGame();
	}

	/**
	 * This method will print log to file if appropriate
	 */
	public void printLogFile() {
		gm.printLogFile();
	}

	private void playGame() {
		do {
			players = gm.getPlayers();

			if(gm.determinNextPlayer()) {
				gm.setCurrentChoice(userChooseAttribute());
			}else {
				gm.applyAICardChoice();
			}
			displayStateOfPlay();
			gm.playRound();
			players = gm.getPlayers();
			turnStats = gm.getTurnStatsHelper();
			displayRoundSummery();
			gm.handleEndOfRound();

		}while(!gm.gameOver());
	}
	
	private void displayStateOfPlay() {
		int currentChoice = gm.getCurrentChoice();
		int lastWinner = gm.getLastWinner();
		int totalRounds = gm.getTotalRounds();

		InputReader in = new InputReader();

		String preRoundString = "";

		if(players.get(0) instanceof Human && lastWinner !=0) {
			/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");

			preRoundString = String.format("%s is the next player!\n\nYour current card is:\n%s\n", players.get(lastWinner).getName(), players.get(0).getTopCard().viewCard());
			System.out.println(preRoundString);

			in.pressEnter();
		} else if(!(players.get(0) instanceof Human)) {
			/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");

			preRoundString = String.format("%s is the next player!", players.get(lastWinner).getName());

			System.out.println(preRoundString);

		}
	}


	/**
	 * displayRoundSummery() displays the text that the user sees on the screen using turnStats to get the necessary data
	 *  Loops through players to print format their name, card, attribute and remaining deck size
	 */
	private void displayRoundSummery() {
		InputReader in = new InputReader();

		for(int i = 0; i < players.size(); i++) {
			System.out.printf("%s played....\t\t%s with %s\t\t\t\t(Remaining Cards : %d (%s))\n",
					turnStats.getPlayer(i).getName(),
					turnStats.getUserCardName(i),
					turnStats.getAnyCardTopAttribute(i),
					players.get(i).getHandSize(),
					turnStats.returnDifferenceHandSize(players.get(i), i));
		}

		String roundString = "";

		// 4)
		if(turnStats.getIsDraw()) {
			roundString = String.format("\nIts a draw!! Cards added to Community... "
					+ "\n\nCommunity deck size is currently: %d",
					gm.getCommunity().size());
		} else {
			roundString = String.format("\n%s won using %s with %s. "
					+ "\n\nCommunity deck size is currently: %d",
					players.get(gm.getLastWinner()).getName(), turnStats.getWinningCardName(), turnStats.getTopCardByAttribute(), gm.getCommunity().size());
		}

		System.out.println(roundString);

		if(players.get(0) instanceof Human) {
			in.pressEnter();
		}
	}
	/**
	 * This method will return the initial player selection
	 * @return number selection
	 */
	public int initialPlayerChoice() {

		InputReader in = new InputReader();


		System.out.println("\nPress 1 for previous game stats, or 2 to start a new game, or 3 to quit");

		int choice = 0;

		while (true) {
			choice = in.parseInt();
			if(choice <= 3 && choice > 0) {
				return choice; 
			} else {
				System.out.println("Please enter within the range");
			}
		}
	}
	
	/**
	 * This method handles a human player's choice of criteria to play for a round
	 * @return the index of the criterion in list of criteria
	 */
	private int userChooseAttribute() {
		InputReader reader = new InputReader();

		/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (gm.getTotalRounds()) + " ~~~~~~~\n");

		System.out.printf("You are the next player! \n\nHere is the card at the top of your deck...\n"
				+ players.get(0).showTopCard()
				+ "\nWhich attribute would you like to trump your enemies with?\n\nPlease type a number between 1 and 5 and press enter!\n");
		int choice = 0;

		while (true) {
			choice = reader.parseInt();
			if(choice <= 5 && choice > 0) {
				return choice;
			} else {
				System.out.println("Please enter within the range");
			}
		}
	}
}
