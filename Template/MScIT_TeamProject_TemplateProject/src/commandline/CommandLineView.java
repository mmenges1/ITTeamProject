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
		CLIview.manageTurnNew();

	}
	
	public CommandLineView(){
		gm = new GameManager();
	}
	
	public void manageTurnNew() {

		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
		while(true) {			
			playerChoice = initialPlayerChoice();
			
			//get player choice within a set player choice
			
			System.out.println("CLUview - init Player Choice " + playerChoice);

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
	
	
	public String displayPreviousGameStats() {
		gm.displayPriviousGameStats();
		
		return "Game stats here";
		
	}
	
	public void dealAndPlay(int players) {
		gm.deal(players);
		
		playGame();
	}
	
	private void playGame() {
		
		do {
			players = gm.getPlayers();
			
			if(gm.determinNextPlayer()) {
				gm.setCurrentChoice(userChooseAttribute());
			}else {
				System.out.println("Not user turn");
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
		
		/*Make the people feel at home :) -->*/ System.out.println("\n\n~~~~~~~  R O U N D : " + (totalRounds) + " ~~~~~~~\n");
		
		if(players.get(0) instanceof Human) {
				String preRoundString = String.format("%s is the next player!\n\nYour current card is:\n%s\n", players.get(lastWinner).getName(), players.get(0).getTopCard().viewCard());
				
				System.out.println(preRoundString);
				
				in.pressEnter();
		}
		
		
		
		
		
	}

	private boolean askUserQuit() {
		System.out.println("Ask user quit");
		return false;
	}

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

		// 3)
		//TODO Implement a GameStats here to convey a points system for each player

		String roundString = "";

		// 4)
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
