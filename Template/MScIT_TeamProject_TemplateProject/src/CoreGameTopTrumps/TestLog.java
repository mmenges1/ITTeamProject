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
		  fileString += player.get(i).getName() +"\n";
		  for(int j = 0; j < player.get(i).getHandSize(); j++)
		  {
			  fileString += player.get(i).getHand().get(j).toString() + "\n";
		  }
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
//  The contents of the communal pile when cards are added or removed from it
  public void addCommunalDeck(ArrayList<Card> community)
  {
	  if (community.size() > 0)
	  {
		  fileString += "Community Deck has:\n" + community.toString();
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
		 fileString += cardsPlayed.get(i).toString() + "\n";
	  }
	  fileString += "\n---------------------------------------------------------------\n";
  }
// The category selected and corresponding values when a user or computer selects a category
  public void addCategorySelected(String name, String topCardByAttribute)
  {
	  fileString += name + " chose " + topCardByAttribute;
	  fileString += "\n---------------------------------------------------------------\n";
  }
//The winner of the game
  public void addWinner(User user)
  {
	  fileString += "Winner: " + user.getName();
	  fileString += "\n---------------------------------------------------------------\n";
  }

}