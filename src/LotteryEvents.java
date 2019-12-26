
public class LotteryEvents {
	private int identifier;
	private String name;
	private int maxSpaces;
	private int spacesLeft;
	
	public LotteryEvents(String identifier, String name, String maxSpaces) {
		this.identifier = Integer.parseInt(identifier);
		this.name = name;
		this.maxSpaces = Integer.parseInt(maxSpaces);
		this.spacesLeft = this.maxSpaces;
	}

	public int getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}
		
	public int getSpacesLeft() {
		return spacesLeft;
	}

	public void setSpacesLeft(int spacesLeft) {
		this.spacesLeft = spacesLeft;
	}

	public void printString() {
		System.out.println("Event Data for Identifier: " + identifier 
				+ "\n	Name: " + name 
				+ "\n	Max Spaces: "+ maxSpaces
				+ "\n	Spaces Left: " + spacesLeft);
	}
	
}
