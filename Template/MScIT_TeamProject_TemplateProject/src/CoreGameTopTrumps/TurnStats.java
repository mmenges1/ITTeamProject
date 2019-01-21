package CoreGameTopTrumps;

import java.util.ArrayList;

//Specification
//The program should then print to standard out the selected category, who won (or
//	whether it was a draw), the winning card and whether the player has been eliminated (they have no cards left).



/*
 * Class is responsible for holding the statistics related to each turn of the game played
 */
public class TurnStats {
	ArrayList<User> players;
	String selectedCategory;
	String result;
	String winningCard;
	boolean playerEliminated;
	User winningPlayer;

	//Constructor parameters are the individual game turn statistics
	//Set methods for each attribute

	public TurnStats(String selectedCategory, String result, String winningCard, boolean playerEliminated) {
	setSelectedCategory(selectedCategory);
	setResult(result);
	setWinningCard(winningCard);
	setPlayerEliminated(playerEliminated);
	winningPlayer = null;
	this.players = new ArrayList<User>();
	}

	//This method sets the card category selected during the turn
	public void setSelectedCategory(String selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	//This method sets the winner (player or CPU) or draw result for the turn
	public void setResult(String result) {
		this.result = result;
	}

	//This method sets the winningCard attribute
	public void setWinningCard(String winningCard) {
		this.winningCard = winningCard;
	}

	//This method sets playerEliminated attribute which states whether the player was eliminated at the end of the turn
	public void setPlayerEliminated(boolean playerEliminated) {
		this.playerEliminated = playerEliminated;
	}

	//This method gets the selectedCategory attribute
	public String getSelectedCategory() {
		return selectedCategory;
	}

	//This method gets the result attribute
	public String getResult() {
		return result;
	}

	//This method gets the winningCard attribute
	public String getWinningCard() {
		return winningCard;
	}

	//This method gets the playerEliminated attribute
	public boolean getPlayerEliminated() {
		return playerEliminated;
	}
	
	public void setWinningPlayer(int index) {
		this.winningPlayer = this.players.get(index);
	}
	
	public void setPlayers(ArrayList<User> playerList) {
		this.players = playerList;
	}

	//This method is called at the end of each turn
	//It prints the a title and four relevant statistics regarded the round just played
	public void turnResults() {
		String wasThePlayerEliminatedAtTheEndOfTheRound;

		if (getPlayerEliminated() == true) {
			wasThePlayerEliminatedAtTheEndOfTheRound = "The player is eliminated";
		}
		else {
			wasThePlayerEliminatedAtTheEndOfTheRound = "The player has not been eliminated";
		}

		System.out.println("Round Statistics:\n"
				+ "Selected category: " + getSelectedCategory()
				+ "Result:  " + getResult()
				+ "Winning Card:  " + getWinningCard()
				+ "Player status:  " + wasThePlayerEliminatedAtTheEndOfTheRound);
	}
}
