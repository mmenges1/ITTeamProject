package commandline;

import java.util.ArrayList;

import CoreGameTopTrumps.GameManager;
import CoreGameTopTrumps.Human;
import CoreGameTopTrumps.InputReader;
import CoreGameTopTrumps.TurnStatsHelper;
import CoreGameTopTrumps.User;

public class CommandLineView {
	
	private ArrayList<User> players;
	private ArrayList<TurnStatsHelper> turnStats;
	GameManager gm;

	public static void main(String[] args) {
		
		CommandLineView CLIview = new CommandLineView();
		System.out.println("FROM COMMAND LINE VIEW");
		CLIview.manageTurn();

	}
	
	public CommandLineView(){
		gm = new GameManager();
	}
	
	public void manageTurn() {

		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
		while(true) {			
			playerChoice = initialPlayerChoice();
			
			//get player choice within a set player choice
			
//			System.out.println("CLUview - init Player Choice " + playerChoice);

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
	
	
	public void displayPreviousGameStats() {
		
		//Currently set to a mock method!!
		String previousStats = gm.getPreviousGameStats().toString();
		
		
		System.out.println(previousStats);
		
	}
	
	public void dealAndPlay(int players) {
		gm.deal(players);
		
		playGame();
	}
	
	public void printLogFile() {
		gm.printLogFile();
	}
	
	private void playGame() {
		
		do {
			players = gm.getPlayers();
			
			if(gm.determinNextPlayer()) {
				gm.setCurrentChoice(userChooseAttribute());
			}else {
//				System.out.println("Not user turn");
				gm.applyAICardChoice();
			}
			
			displayStateOfPlay();
			
			gm.playRoundNew();
			
			players = gm.getPlayers();
			turnStats = gm.getTurnStats();
			
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

	private boolean askUserQuit() {
		System.out.println("Ask user quit");
		return false;
	}

	/* displayRoundSummery() displays the text that the user sees on the screen.
	*  it uses turnStats to get the necissary data
	*
	*  1) Intantiates the integer which represents the current turn within the turnStats arraylist
	*  2) Loops through players to print format their name, card, attribute and remaining deck size.
		The if condition can probibly be removed as now gameOver() removes players with no cards
	*  3) This condition displays either the winning hand or declares a draw, & displays the size of the community deck

	*/
	private void displayRoundSummery() {
		InputReader in = new InputReader();
		
		// 1)
		int currentTurnStats = turnStats.size()-1;

		// 2)
		for(int i = 0; i < players.size(); i++) {
			System.out.printf("%s played....\t\t%s with %s\t\t\t\t(Remaining Cards : %d (%s))\n",
					turnStats.get(currentTurnStats).getPlayer(i).getName(),
					turnStats.get(currentTurnStats).getUserCardName(i),
					turnStats.get(currentTurnStats).getAnyCardTopAttribute(i),
					players.get(i).getHandSize(),
					turnStats.get(currentTurnStats).returnDifferenceHandSize(players.get(i), i));
		}

		String roundString = "";

		// 3)
		if(turnStats.get(currentTurnStats).getIsDraw()) {
			roundString = String.format("\nIts a draw!! Cards added to Community... "
					+ "\n\nCommunity deck size is currently: %d",
					gm.getCommunity().size());
		} else {
			roundString = String.format("\n%s won using %s with %s. "
					+ "\n\nCommunity deck size is currently: %d",
					players.get(gm.getLastWinner()).getName(), turnStats.get(currentTurnStats).getWinningCardName(), turnStats.get(currentTurnStats).getTopCardByAttribute(), gm.getCommunity().size());
		}

		System.out.println(roundString);
		
		if(players.get(0) instanceof Human) {
			in.pressEnter();
		}
		
		
		/**
		 * Iterate through list of players
		 * If a player has no more cards left, a message displays that they've been knocked out
		 * If this is true and that player comes before the winning player, the index of that winning player
		 * is adjusted accordingly for the next round
		 * The losing player is then removed from the list
		 * i needs to be decremented as the size is shortened when a player is removed on each iteration
		 */
//		for (int i = 0; i < players.size();i++) {
//			if (this.players.get(i).userLoses()) {
//				System.out.println("\n" + this.players.get(i).getName() + " has been knocked out!");
//				if (i < this.lastWinner) {
//					this.lastWinner--;
//				}
//				this.players.remove(i);
//				i--;
//			} else {
//				System.out.println("\n" + this.players.get(i).getName() + " to play next round!");
//			}
//		}

		
	}

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
