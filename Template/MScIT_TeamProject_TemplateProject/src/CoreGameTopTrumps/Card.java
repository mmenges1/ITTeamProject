package CoreGameTopTrumps;

public class Card {
	protected String attribute1;
	protected String attribute2;
	protected String attribute3;
	protected String attribute4;
	protected String attribute5;
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	
	public void viewCard()
	{
		String cardString = "Name: " + getName() +
										"\nAttribute 1: " + attribute1+
										"\nAttribute 2: " + attribute2+
										"\nAttribute 3: " + attribute3+
										"\nAttribute 4: " + attribute4+
										"\nAttribute 5: " + attribute5;
		System.out.println(cardString);
	}
}
