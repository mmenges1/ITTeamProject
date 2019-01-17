package CoreGameTopTrumps;

public class Card {
	protected int attribute1;
	protected int attribute2;
	protected int attribute3;
	protected int attribute4;
	protected int attribute5;
	protected String name;

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

	public String viewCard()
	{
		String cardString = "Name: " + getName() +
										" Att 1: " + attribute1+
										" Att 2: " + attribute2+
										" Att 3: " + attribute3+
										" Att 4: " + attribute4+
										" Att 5: " + attribute5;
		System.out.println(cardString);
		
		return cardString;
	}
	
	
}
