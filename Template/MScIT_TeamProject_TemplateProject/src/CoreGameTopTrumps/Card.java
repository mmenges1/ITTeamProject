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
	public int getAttribute(int selectionCriteriaNumber) {
		int attribute = 0;
		switch (selectionCriteriaNumber) {
		case '1': {
			attribute = this.attribute1;
		}
		case '2': {
			attribute = this.attribute2;
		}
		case '3': {
			attribute =this.attribute3;
		}
		case '4': {
			attribute  = this.attribute4;
		}
		case '5': {
			attribute = this.attribute5;
		}
		default: {
			break;
		}
		}
		return attribute;
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
	
	
	public String viewCard() {
		String cardString = "";
		{
			cardString =  "Name: " + getName() + "\n" + 
					this.criterias.get(0) + ": "+ attribute1+"\n" + 
					this.criterias.get(1) + ": " + attribute2+ "\n" + 
					this.criterias.get(2) + ": "+ attribute3+"\n" + 
					this.criterias.get(3) + ": "+ attribute4+"\n" + 
					this.criterias.get(4)+ ": " + attribute5;
			System.out.println(cardString);

			return cardString;
		}
	}
}
