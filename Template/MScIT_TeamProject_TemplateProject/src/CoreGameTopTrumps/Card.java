package CoreGameTopTrumps;

import java.util.ArrayList;

public class Card {
	protected int attribute1;
	protected int attribute2;
	protected int attribute3;
	protected int attribute4;
	protected int attribute5;
	protected String name;
	protected ArrayList<String> criterias = new ArrayList<String>(); //adding a criterias/attributes
	
	
	public void setCriteria(ArrayList<String> criteria) {
		this.criterias = criteria;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public String getAttributeName(int index) {
		return this.criterias.get(index);
	}
	/**kw
	 * Get an attribute based on the selected criteria number
	 * @param selectionCriteriaNumber
	 * @return 
	 */
	 /*
	public int getAttribute(int selectionCriteriaNumber) {
		int value = 0;
		switch (selectionCriteriaNumber) {
		case 1: {
			value = this.getAttribute1();
			 break;
		}
		case 2: {
			value = this.getAttribute2();
			break;
		}
		case 3: {
			 value =this.getAttribute3();
			 break;
		}
		case 4: {
			 value  = this.getAttribute4();
			 break;
		}
		case 5: {
			value = this.getAttribute5();
			break;
		}
		default: {
			break;
		}
		}
		return value;
	}
	*/
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
			cardString =  "Name: " + getName() + "\n" + 
					this.criterias.get(0) + ": "+ attribute1+"\n" + 
					this.criterias.get(1) + ": " + attribute2+ "\n" + 
					this.criterias.get(2) + ": "+ attribute3+"\n" + 
					this.criterias.get(3) + ": "+ attribute4+"\n" + 
					this.criterias.get(4)+ ": " + attribute5;
			System.out.println(cardString);

			return cardString;
	}
	//temporary test
	public static void main (String[] args) {
		Card card = new Card();
		card.setName("Kevin");
		card.setAttribute1(10);
		card.setAttribute2(2);
		card.setAttribute3(3);
		card.setAttribute4(5);
		card.setAttribute5(5);
		System.out.println(card.getAttribute(1));
	}
}
