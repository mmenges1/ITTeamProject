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
	public int getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(int attribute1) {
		this.attribute1 = attribute1;
	}
	public int getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(int attribute2) {
		this.attribute2 = attribute2;
	}
	public int getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(int attribute3) {
		this.attribute3 = attribute3;
	}
	public int getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(int attribute4) {
		this.attribute4 = attribute4;
	}
	public int getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(int attribute5) {
		this.attribute5 = attribute5;
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
	/**kw
	 * Get an attribute based on the selected criteria number
	 * @param selectionCriteriaNumber
	 * @return 
	 */
	public int getAttribute(int selectionCriteriaNumber) {
		int value = 0;
		int n = 1;
		switch (selectionCriteriaNumber) {
		case 1: {
//			value = this.getAttribute1();
			value = this.getAttributeFromArrayList(1-n);
			 break;
		}
		case 2: {
//			value = this.getAttribute2();
			value = this.getAttributeFromArrayList(2-n);
			break;
		}
		case 3: {
//			 value =this.getAttribute3();
			value = this.getAttributeFromArrayList(3-n);
			 break;
		}
		case 4: {
//			 value  = this.getAttribute4();
			value = this.getAttributeFromArrayList(4-n);
			 break;
		}
		case 5: {
//			value = this.getAttribute5();
			value = this.getAttributeFromArrayList(5-n);
			break;
		}
		default: {
			break;
		}
		}
		return value;
	}
	
//	public String viewCard()
//	{
//		String cardString = "Name: " + getName() +
//										" Att 1: " + attribute1+
//										" Att 2: " + attribute2+
//										" Att 3: " + attribute3+
//										" Att 4: " + attribute4+
//										" Att 5: " + attribute5;
//		System.out.println(cardString);
//		
//		return cardString;
//	}
	
	//TODO: remove this method and replace all references to it with getAttribute
	public int returnSelectedAttr(int att) {
		
		if(att == 1) {
			return getAttribute1();
		}else if(att == 2) {
			return getAttribute2();
		}else if(att == 3) {
			return getAttribute3();
		}else if(att == 4) {
			return getAttribute4();
		}else if(att == 5) {
			return getAttribute5();
		}
		
		return -1;
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
//					System.out.println(cardString);
			return cardString;
		}
	}
	//temporary test
	public static void main (String[] args) {
		Card card = new Card();
		card.setName("Kevin");
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
		System.out.println(card.getAttribute(3));
	}
}
