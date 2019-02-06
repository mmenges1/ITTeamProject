package CoreGameTopTrumps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is responsible for reading the text file containing information of cards
 * Each row is to be stored as a card object which will form the basis of the deck
* The top row will be stored as a list of attributes which is then used for each card.
 */
public class Deck {
	ArrayList<String> criteria = new ArrayList<String>();
	ArrayList<Card> startingDeck = new ArrayList<Card>();

	/*
	 * This method creates list of cards from the deck text file
	 */
	public ArrayList<Card> createDeck(String fileName) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader buffer = new BufferedReader(fileReader);
			String line;
			int count = 0;
			while ((line = buffer.readLine()) != null) {
				int i = 0;
				String data[] = new String[6];
				for (String value : line.split(" ")) {
					if (!value.equals(" "))
						data[i] = value;
					i++;
				}
				if (count > 0) {
					cardList.add(createCard(data));
				} else {
					readCriterias(data);
				}
				count++;
			}

			buffer.close();
		} catch (FileNotFoundException e) {
			System.out.println("This file is not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Collections.shuffle(cardList);

		return cardList;
	}
	 /**
	  * read the top line and stored in an arraylist of strings
	  * @param data is the info read from the first row
	  */
	public void readCriterias(String[] data) {
		for (int j = 1; j<data.length; j++) {
			criteria.add(data[j]);
		}
	}
	
    /*
     * Creates a new card object from the file data and sets the name, list of criteria and list of attributes 
     *  @param data is the info read from a row
     *  @returns a new card object
     */
	public Card createCard(String[] data) {
		Card newCard = new Card();
		ArrayList<Integer> attributes = new ArrayList<Integer>();
		newCard.setName(data[0]);
		for (int i = 1; i < data.length; i++) {
			attributes.add(Integer.parseInt(data[i]));

		}
		newCard.setAttributes(attributes);
		newCard.setCriteria(criteria); //kw
		this.startingDeck.add(newCard);
		return newCard;
	}
	
	/*
	 * Method to get the starting deck size
	 * returns a number
	 */
	public int startingDeckSize() {
		return this.startingDeck.size();
	}

	/**
	 * Method to retrieve the starting deck
	 * @return an arraylist of card objects
	 */
	public ArrayList<Card> startingDeck()
	{
		return this.startingDeck;
	}
//A temporary test
	public static void main(String[] args) {
		Deck d = new Deck();
		ArrayList<Card> list = new ArrayList<Card>();
		list = d.createDeck("StarCitizenDeck.txt");
		//This method shuffles the list
		Collections.shuffle(list);
//		for (Card c : list) {
//			c.viewCard();
		int i = 0;
		Card c = list.get(i);
//			String s = "";
//			for (int j = list.size()-1; j >0; j--) {
//			s = list.get(i).getName() + ": ";
//			for (int j = 0; j<c.getAttributes().size(); j++) {
		for (int j = 0; j <= c.getAttributes().size(); j++) {
			System.out.println(c.getAttribute(j));
		}
		c.viewCard();
//			}
//		}
		}
	}