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
//The contents of the complete deck once it has been read in and constructed
  public void addInitialDeck(ArrayList<Card> initDeck)
  {
	  fileString += "Initial Deck imported:\n" + initDeck.toString();
	  fileString += "\n---------------------------------------------------------------\n";
  }
// The contents of the complete deck after it has been shuffled
  public void addShuffledDeck(ArrayList<Card> newDeck)
  {
	  fileString += "Shuffled Deck:\n" + newDeck.toString();
	  fileString += "\n---------------------------------------------------------------\n";
  }
//  The contents of the user's deck and the computer's deck(s) once they have been allocated.
  public void addPlayerDeck(ArrayList<User> player)
  {
	  for(int i = 0; i < player.size(); i++)
	  {
		  fileString += player.get(i).getName() +": \n";
		  for(int j = 0; j < player.get(i).getHandSize(); j++)
		  {
			  fileString += player.get(i).getHand().get(j).communityString() + "\n";
		  }
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
//  The contents of the communal pile when cards are added or removed from it
  public void addCommunalDeck(ArrayList<Card> community)
  {		String cards = "";
	  if (community.size() > 0)
	  {  for (int i = 0; i < community.size(); i++) {
		  if (i == (community.size()-1)) {
			  cards += community.get(i).getName();
		  } else {
			  cards += community.get(i).getName() + " | ";
		  }
	  }
		  fileString += "Community Deck has: [" + cards + "]";
	  }
	  else
	  {
		  fileString += "Communal Deck is empty.";
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
//The contents of the current cards in play (the cards from the top of the user's deck and the computer's deck(s))
  public void addCardsInPlay(ArrayList<Card> cardsPlayed)
  {
	  fileString += "Cards in play are:\n";
	  for(int i = 0; i < cardsPlayed.size(); i++)
	  {
		 fileString +=  cardsPlayed.get(i).toString() + "\n";
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }

  // The category selected and corresponding values when a user or computer selects a category
  public void addCategorySelected(String name, String topCardByAttribute)
  {
	  fileString += name + " chose " + topCardByAttribute;
	  fileString += "\n---------------------------------------------------------------\n";
  }

  /*
   * This method will write to file, the winner of the game
   * @param the remaining user
   */
  public void addWinner(User user)
  {
	  fileString += "Winner: " + user.getName();
	  fileString += "\n---------------------------------------------------------------\n";
  }
  
  /**
   * This method will write to file, the cards that were played including the card name and attributes
   * @param turnStatsHelper containing the player and played card details for a round
   */
public void addCardsInPlay(TurnStatsHelper turnStatsHelper) {
	  {
		  fileString += "Cards in play are:\n";
		  for(int i = 0; i < turnStatsHelper.cardsPlayed.size(); i++)
		  {
			 fileString += turnStatsHelper.getPlayer(i).getName() + "'s : " +  turnStatsHelper.cardsPlayed.get(i).toString() + "\n";
		  }
		  fileString += "\n---------------------------------------------------------------\n";
	  }
	}

/**
 * This method writes to file, each players deck. It is called after each round is played
 * @param players that played in the previous round
 */
public void addDeckAfterRound(ArrayList<User> players) {
	  {
		  fileString += "Player decks after previous round:\n";
		  for(int i = 0; i < players.size(); i++)
		  {
			  String s = "";
			  for (int j = 0; j < players.get(i).getHandSize(); j++) {
				  if (j == (players.get(i).getHandSize()-1)) {
				  s += players.get(i).getHand().get(j).name;
			  } else {
				  s += players.get(i).getHand().get(j).name + " | ";
			  }
			  }
			 fileString += players.get(i).getName() + "'s deck: [" + s  + "]" + "\n";
		  }
		  fileString += "\n---------------------------------------------------------------\n";
	  }
	}
}
