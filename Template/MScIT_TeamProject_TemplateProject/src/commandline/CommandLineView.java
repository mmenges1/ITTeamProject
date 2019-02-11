package commandline;

import java.util.ArrayList;

import CoreGameTopTrumps.GameManager;
import CoreGameTopTrumps.Human;
import CoreGameTopTrumps.InputReader;
import CoreGameTopTrumps.TurnStatsHelper;
import CoreGameTopTrumps.User;

public class CommandLineView {
	
	private ArrayList<User> players;
	private TurnStatsHelper turnStatsHelper;
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
		
		@SuppressWarnings("unused")
		boolean humanStillInGame = true;
		
		do {
			players = gm.getPlayers();
			
			if(!(players.get(0) instanceof Human) && (humanStillInGame == true)){
					notifyPlayerOut();	
					humanStillInGame = false;
			}
			
			if(gm.determinNextPlayer()) {
				gm.setCurrentChoice(userChooseAttribute());
			}else {
//				System.out.println("Not user turn");
				gm.applyAICardChoice();
			}
			
			displayStateOfPlay();
			
			gm.playRound();
			
			players = gm.getPlayers();
			turnStatsHelper = gm.getTurnStatsHelper();
			
			displayRoundSummery();
			
			gm.handleEndOfRound();
			
		}while(!gm.gameOver());
		
	
	}

	private void notifyPlayerOut() {
		System.out.println("\nYou are out of cards! AI taking over ");
		InputReader in = new InputReader();
		in.pressEnter();
	}

	private void displayStateOfPlay() {
		
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

	
	private void displayRoundSummery() {
		InputReader in = new InputReader();

		for(int i = 0; i < players.size(); i++) {
			System.out.printf("%s played....\t\t%s with %s\t\t\t\t(Remaining Cards : %d (%s))\n",
					turnStatsHelper.getPlayer(i).getName(),
					turnStatsHelper.getUserCardName(i),
					turnStatsHelper.getAnyCardTopAttribute(i),
					players.get(i).getHandSize(),
					turnStatsHelper.returnDifferenceHandSize(players.get(i), i));
		}

		String roundString = "";


		if(turnStatsHelper.getIsDraw()) {
			roundString = String.format("\nIts a draw!! Cards added to Community... "
					+ "\n\nCommunity deck size is currently: %d",
					gm.getCommunity().size());
		} else {
			roundString = String.format("\n%s won using %s with %s. "
					+ "\n\nCommunity deck size is currently: %d",
					players.get(gm.getLastWinner()).getName(), turnStatsHelper.getWinningCardName(), turnStatsHelper.getTopCardByAttribute(), gm.getCommunity().size());
		}

		System.out.println(roundString);
		
		if(players.get(0) instanceof Human) {
			in.pressEnter();
		}

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
