/*
The contents of the communal pile when cards are added or removed from it
The category selected and corresponding values when a user or computer selects a category
*/
package CoreGameTopTrumps;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TestLog
{
	String fileString = "";
	/**
	 * Information from round events are currently stored in the String variable, name fileString.
	 * This method will write that to file
	 */
  public void printToFile()
  {
    FileWriter fileWriter;
	try {
		fileWriter = new FileWriter("toptrumps.log");
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write(fileString);
		bufferedWriter.close();
	} catch (IOException e) {
	}
  }
  /**
   * The contents of the complete deck once it has been read in and constructed 
   * @param initDeck - is the initial deck read from file
   */
  
  public void addInitialDeck(ArrayList<Card> initDeck)
  {
	  fileString += "Initial Deck imported:\n" + initDeck.toString();
	  fileString += "\n---------------------------------------------------------------\n";
  }
/**
 * add the contents of the complete deck after it has been shuffled
 * @param newDeck - shuffled deck
 */
  public void addShuffledDeck(ArrayList<Card> newDeck)
  {
	  fileString += "Shuffled Deck:\n" + newDeck.toString();
	  fileString += "\n---------------------------------------------------------------\n";
  }
/**
 *  The contents of the user's deck and the computer's deck(s) once they have been allocated.
 * @param player - each active player
 */
  public void addPlayerDeck(ArrayList<User> player)
  {
	  for(int i = 0; i < player.size(); i++)
	  {
		  fileString += player.get(i).getName() +"\n";
		  for(int j = 0; j < player.get(i).getHandSize(); j++)
		  {
			  fileString += player.get(i).getHand().get(j).toString() + "\n";
		  }
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
/**
 * The contents of the communal pile when cards are added or removed from it
 * @param community - list of cards that are in community if a round ends in draw
 */
  public void addCommunalDeck(ArrayList<Card> community)
  {
	  if (community.size() > 0)
	  {
		  fileString += "Communal Deck has:\n" + community.toString();
	  }
	  else
	  {
		  fileString += "Communal Deck is empty.";
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
 /** add the contents of the current cards in play (the cards from the top of the each players' deck)
  * @param turnStatsHelper retrieves the data from the round
  */
  public void addCardsInPlay(TurnStatsHelper turnStatsHelper)
  {
	  fileString += "Cards in play are:\n";
	  for(int i = 0; i < turnStatsHelper.getCardPlayedSize(); i++)
	  {
		 fileString += turnStatsHelper.getPlayer(i).getName() + "'s " + turnStatsHelper.cardsPlayed.get(i) + "\n";
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }

/**
 * Add the winner of a game to the log
 * @param user is remaining player
 */
  public void addWinner(User user)
  {
	  fileString += "Winner: " + user.getName();
	  fileString += "\n---------------------------------------------------------------\n";
  }

//The category selected and corresponding values when a user or computer selects a category
public void addCategorySelected(int playerSize, ArrayList<User> players, TurnStatsHelper turnStatsHelper) {
		for(int i = 0; i < playerSize; i++){
			fileString += players.get(i).getName() +" played "+ turnStatsHelper.getAnyCardTopAttribute(i) +"\n";
		}
		fileString += "\n---------------------------------------------------------------\n";
	}

/**
 * This method returns the criteria that was chosen by a player
 * @param turnStatsHelper - retrieves teh data from the current round
 */
public void addPlayerChoice(TurnStatsHelper turnStatsHelper) {
		fileString += turnStatsHelper.getPlayer(turnStatsHelper.getLastWinner()).getName() + " chose " + turnStatsHelper.getChoosingPlayerCriteria() + "\n";
	}
}