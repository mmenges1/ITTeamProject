package CoreGameTopTrumps;

import java.util.ArrayList;

public class Card {
	protected String name;
	protected ArrayList<String> criterias; // list of criterias (e.g. Size Speed Range Firepower Cargo)
	private ArrayList<Integer> attributes; // list of values for each criteria (Values for Size Speed Range Firepower Cargo respectively)
	protected int attribute1;
	protected int attribute2;
	protected int attribute3;
	protected int attribute4;
	protected int attribute5;
	
	public Card() {
		this.name = "";
		this.attributes = new ArrayList<Integer>();
		this.criterias = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<String> getCriterias() {
		return this.criterias;
	}
	
	public void setAttributes(ArrayList<Integer> attributes) {
		this.attributes = attributes;
	}
	public void setCriteria(ArrayList<String> criteria) {
		this.criterias = criteria;
	}
	
	
	public ArrayList<Integer> getAttributes() {
		return this.attributes;
	}
	public int getAttributeFromArrayList(int index) {
		return this.attributes.get(index);
	}
	
	public void setAttribute(int index, Integer value) {
		this.attributes.set(index, value);
	}
	public String getCriteriaName(int index) {
		return this.criterias.get(index);
	}
	
	/**
	 * 
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

	@Override
	public String toString() {
		return "Card [name=" + name + "]";
	}	
	
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
}
