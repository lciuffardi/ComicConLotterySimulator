package comicConLotterySimulator.object;

public class LotteryEvent {
	private int identifier;
	private String name;
	private int maxSpaces;
	private int spacesLeft;
	
	public LotteryEvent(String identifier, String name, String maxSpaces) {
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

	public String toString() {
		return "Event Data for Identifier: " + identifier 
				+ "\n	Name: " + name 
				+ "\n	Max Spaces: "+ maxSpaces
				+ "\n	Spaces Left: " + spacesLeft;
	}
	
}
