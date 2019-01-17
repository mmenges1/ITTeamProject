package CoreGameTopTrumps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

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
    //Creates a card from the file data
	public Card createCard(String[] data) {
		Card newCard = new Card();
		newCard.setName(data[0]);
		newCard.setAttribute1(Integer.parseInt(data[1]));
		newCard.setAttribute2(Integer.parseInt(data[2]));
		newCard.setAttribute3(Integer.parseInt(data[3]));
		newCard.setAttribute4(Integer.parseInt(data[4]));
		newCard.setAttribute5(Integer.parseInt(data[5]));
		return newCard;
	}
//A temporary test
	public static void main(String[] args) {
		Deck d = new Deck();
		ArrayList<Card> list = new ArrayList<Card>();
		list = d.createDeck("StarCitizenDeck.txt");
		//This method shuffles the list
		Collections.shuffle(list);
		for (Card c : list) {
			c.viewCard();
		}

	}

}