package commandline;

import CoreGameTopTrumps.GameManager;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		
		System.out.println("From top Trumps CLI APP .java");
		CommandLineView CLIview = new CommandLineView();
		

		int playerChoice = 0;
		System.out.printf("Hello, Welcome to Top Trumps!\nWould you like to see previous game statistics, start a new game, or quit?\n");
		
		while(!userWantsToQuit) {			
			playerChoice = CLIview.initialPlayerChoice();
			
			if(playerChoice == 1) {
				
				CLIview.displayPreviousGameStats();
				
			} else if (playerChoice == 2){
				
				CLIview.dealAndPlay(4);
				
				if (writeGameLogsToFile){
					CLIview.printLogFile();
				}
				
			} else {
				userWantsToQuit=true;
				System.out.println("Goodbye!");
				break;
			}
		}
	}

}