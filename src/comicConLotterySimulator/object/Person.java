package comicConLotterySimulator.object;

public class Person {
	private int identifier;
	private String firstName;
	private String lastName;
	private String email;
	private String choiceWon;
	private int choiceWonIdentifier;
	private int choiceWonPreference;
	
	public Person(String identifier, String firstName, String lastName, String email) {
		this.identifier = Integer.parseInt(identifier);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public boolean setChoiceWon(String choiceWon, int choiceWonIdentifier, int choiceWonPreference) {
		this.choiceWon = choiceWon;
		this.choiceWonIdentifier = choiceWonIdentifier;
		this.choiceWonPreference = choiceWonPreference;
		return true;
	}
	
	public int getChoiceWonIdentifier() {
		return choiceWonIdentifier;
	}

	public void printData() {
		System.out.println("Person Data for Identifier: " + identifier 
				+ "\n	First Name: " + firstName 
				+ "\n	Last Name: "+ lastName
				+ "\n	Email: " + email
				+ "\n	Choice Won Identifier: " + choiceWonIdentifier
				+ "\n	Choice Won: " + choiceWon
				+ "\n	Choice Won Preference: " + choiceWonPreference);
	}
	
	public String toString() {
		String s = Integer.toString(identifier) + "," + firstName + "," + lastName + "," + email;
		return s;
	}
	
}
