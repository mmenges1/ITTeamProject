package CoreGameTopTrumps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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
					//read the headers and stores in an arraylist of strings
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

	public void readCriterias(String[] data) {

		for (int j = 1; j<data.length; j++) {
			criteria.add(data[j]);
		}
	}
    //Creates a card from the file data and includes the attributes as well
	public Card createCard(String[] data) {
		Card newCard = new Card();
		ArrayList<Integer> attributes = new ArrayList<Integer>();

		newCard.setName(data[0]);
		for (int i = 1; i < data.length; i++) {
			attributes.add(Integer.parseInt(data[i]));
		}
		newCard.setAttributes(attributes);
		newCard.setCriteria(criteria); 
		this.startingDeck.add(newCard);
		return newCard;
	}

	public int startingDeckSize() {
		return this.startingDeck.size();
	}

	public ArrayList<Card> startingDeck()
	{
		return this.startingDeck;
	}

	}