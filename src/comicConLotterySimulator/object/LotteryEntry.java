package comicConLotterySimulator.object;

public class LotteryEntry {
	private int personIdentifier;
	private int firstChoice;
	private int secondChoice;
	private int thirdChoice;
	
	public LotteryEntry(String personIdentifier, String firstChoice, String secondChoice, String thirdChoice) {
		this.personIdentifier = Integer.parseInt(personIdentifier);
		this.firstChoice = Integer.parseInt(firstChoice);
		this.secondChoice = Integer.parseInt(secondChoice);
		this.thirdChoice = Integer.parseInt(thirdChoice);
	}

	public int getFirstChoice() {
		return firstChoice;
	}

	public int getSecondChoice() {
		return secondChoice;
	}

	public int getThirdChoice() {
		return thirdChoice;
	}

	public String toString() {
		return "Lottery Entry Data for Identifier: " + personIdentifier 
				+ "\n	First Choice: " + firstChoice 
				+ "\n	Second Choice: "+ secondChoice
				+ "\n	Third Choice: " + thirdChoice;
	}
	
}
