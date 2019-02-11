package CoreGameTopTrumps;

import java.util.ArrayList;
/**
 * This class is responsible for creating an instance of each card object.
 */
public class Card {
	protected String name;
	protected ArrayList<String> criterias; // list of criterias (e.g. Size Speed Range Firepower Cargo)
	private ArrayList<Integer> attributes; // list of values for each criteria (Values for Size Speed Range Firepower Cargo respectively)
	
	/**
	 * Each card object is to contain a name, a list of attribute names and their values
	 */
	public Card() {
		this.name = "";
		this.attributes = new ArrayList<Integer>();
		this.criterias = new ArrayList<String>();
	}
	
	/**
	 * This method is to return the name of the card
	 * @return name of a card
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method is for setting the name of a card, which is obtained by reading the text file
	 * @param name of the card 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method will return a list of the attribute names
	 * @return a list of attribute names
	 */
	public ArrayList<String> getCriterias() {
		return this.criterias;
	}
	
	/**
	 * This method is to set the names for the list of criteria after reading the deck text file
	 * @param criteria is a list of strings
	 */
	public void setCriteria(ArrayList<String> criteria) {
		this.criterias = criteria;
	}
	/*
	 * This method is to set the attributes for each card. Values are read from the .txt file
	 */
	public void setAttributes(ArrayList<Integer> attributes) {
		this.attributes = attributes;
	}
	
	/*
	 * This method is to return the attributes, a list of integers, for a card
	 */
	public ArrayList<Integer> getAttributes() {
		return this.attributes;
	}
	
	/*
	 * This method is to return a value associated with a particular criterion which is determined by it's position within the list
	 * @param index is the position in the list 
	 */
	public int getAttributeFromArrayList(int index) {
		return this.attributes.get(index);
	}
	
	
	public void setAttribute(int index, Integer value) {
		this.attributes.set(index, value);
	}

	/**
	 * This method is to get the name of a criteria determined by a position in the list
	 * @param index - index to retrieve criteria from list
	 * @return string of the name
	 */
	public String getCriteriaName(int index) {
		return this.criterias.get(index);
	}
	
	/**
	 * This method returns the position in the list of integers for skills based on the value passed to it.
	 * @param Value
	 * @return - if a card has the same value for two criterias, this method will return the one that comes last - for smart ai choosing purposes
	 */
	public int getIndexOfValue(int Value) {
		return this.attributes.lastIndexOf(Value);
	}
	/**
	 * Get an attribute based on the selected criteria number
	 * @param selectionCriteriaNumber
	 * @return 
	 */
	public int getAttribute(int selectionCriteriaNumber) {
		int value = 0;
		int n = 1;
		switch (selectionCriteriaNumber) {
		case 1: {
			value = this.getAttributeFromArrayList(1-n);
			 break;
		}
		case 2: {
			value = this.getAttributeFromArrayList(2-n);
			break;
		}
		case 3: {
			value = this.getAttributeFromArrayList(3-n);
			 break;
		}
		case 4: {
			value = this.getAttributeFromArrayList(4-n);
			 break;
		}
		case 5: {
			value = this.getAttributeFromArrayList(5-n);
			break;
		}
		default: {
			break;
		}
		}
		return value;
	}
	
	public String communityString() {
		return "Card [name=" + name + "]";
	}
	@Override
	public String toString() {
		return "Card [name=" + name + " | " + this.criterias.get(0) + ": " + this.getAttribute(1) + " | " +
				this.criterias.get(1) + ": " + this.getAttribute(2)  + " | " +
				this.criterias.get(2) + ": "+ this.getAttribute(3) + " | " +
				this.criterias.get(3) + ": "+ this.getAttribute(4)+ " | " +
				this.criterias.get(4)+ ": " + this.getAttribute(5) + "]";
	}	
	
	/**
	 * This method is to get a string representation of a card
	 * @return a string representation of a card
	 */ 
	public String viewCard() {
		String cardString = "";
		{
			cardString = "Name: " + getName() + "\n" +
					this.criterias.get(0) + ": " + this.getAttribute(1)+ "\n" +
					this.criterias.get(1) + ": " + this.getAttribute(2)+ "\n" + 
					this.criterias.get(2) + ": "+ this.getAttribute(3)+"\n" + 
					this.criterias.get(3) + ": "+ this.getAttribute(4)+"\n" + 
					this.criterias.get(4)+ ": " + this.getAttribute(5);
			return cardString;
		}
	}
	//temporary test
	public static void main (String[] args) {
		Card card = new Card();
		card.setName("Kevin");
		ArrayList<String> criterias = new ArrayList<String>();
		criterias.add("Size");
		criterias.add("Speed");
		criterias.add("Range");
		criterias.add("Strength");
		criterias.add("Height");
		card.setCriteria(criterias);
//		card.setAttribute1(10);
//		card.setAttribute2(2);
//		card.setAttribute3(3);
//		card.setAttribute4(5);
//		card.setAttribute5(5);
		ArrayList<Integer> attributes = new ArrayList<Integer>();
		attributes.add(10);
		attributes.add(2);
		attributes.add(3);
		attributes.add(5);
		attributes.add(5);
		card.setAttributes(attributes);
//		card.viewCard();
		System.out.println(card);
//		System.out.println(card.getAttribute(0));
	}
}
