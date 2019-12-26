import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LotterySimulator {
	private static Map<Integer, LotteryEvents> lotteryEvents = new HashMap<Integer, LotteryEvents>();
	private static Map<Integer, LotteryEntryData> lotteryEntries = new HashMap<Integer, LotteryEntryData>();
	private static Map<Integer, PersonData> persons = new HashMap<Integer, PersonData>();
	private static List<Integer> personIdentifiers = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		loadAllFileData();
		startLottery();
		SaveAllDataToFiles();
		printAllData();
	}
	
	private static void loadAllFileData() throws NumberFormatException, IOException {
		loadFileData(LotterySimulatorConstants.LOTTERY_ITEMS_DIR);
		loadFileData(LotterySimulatorConstants.LOTTERY_ENTRY_DATA_DIR);
		loadFileData(LotterySimulatorConstants.PERSONS_DATA_DIR);
	}
	
	private static void startLottery() {
		while(personIdentifiers.size() != 0) {
			boolean prizeSet = false;
			int choice = 1;
			Random rand = new Random();
			int x = rand.nextInt(personIdentifiers.size());
			while(prizeSet == false && choice <= 3) {
				prizeSet = setPrize(personIdentifiers.get(x), choice);
				choice++;
			}
			personIdentifiers.remove(x);
		}
	}
	
	private static void loadFileData(String filePath) throws NumberFormatException, IOException {
		String fileName = filePath.replace(LotterySimulatorConstants.RES_DIR, "");
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);
		String[] lotteryData = null;
		String line;
		
		br.readLine();
		while((line=br.readLine())!= null) {
			lotteryData=line.split(",");
			if(fileName.matches(LotterySimulatorConstants.LOTTERY_ITEMS))
				lotteryEvents.put(Integer.parseInt(lotteryData[0]), new LotteryEvents(lotteryData[0], lotteryData[1], lotteryData[2]));
			else if(fileName.matches(LotterySimulatorConstants.LOTTERY_ENTRY_DATA))
				lotteryEntries.put(Integer.parseInt(lotteryData[0]), new LotteryEntryData(lotteryData[0], lotteryData[1], lotteryData[2], lotteryData[3]));
			else if(fileName.matches(LotterySimulatorConstants.PERSONS_DATA)) {
				persons.put(Integer.parseInt(lotteryData[0]), new PersonData(lotteryData[0], lotteryData[1], lotteryData[2], lotteryData[3]));
				personIdentifiers.add(Integer.parseInt(lotteryData[0]));
			}
		}
		
		fr.close();
		br.close();
	}
	
	private static void SaveAllDataToFiles() throws IOException {
		for(int i : lotteryEvents.keySet()) {
			String fileName = lotteryEvents.get(i).getName().replaceAll("[^a-zA-Z0-9&]", "_") + LotterySimulatorConstants._WINNERS;
			saveFileData(fileName, lotteryEvents.get(i).getIdentifier());
		}
		saveFileData(LotterySimulatorConstants.LOTTERY_LOSSES, 0);
	}

	
	private static void saveFileData(String fileName, int eventIdentifier) throws IOException {
		File directory = new File(LotterySimulatorConstants.LOTTERY_RESULTS_DIR);
		File file = new File(LotterySimulatorConstants.LOTTERY_RESULTS_DIR + fileName);
		
		if(!directory.exists())
			directory.mkdir();
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i : persons.keySet()) {
			if(persons.get(i).getChoiceWonIdentifier() == eventIdentifier) {
				bw.write(persons.get(i).toString());
				bw.newLine();
			}
		}
		
		bw.close();
	}
	
	private static boolean setPrize(int identifier, int choice) {
		LotteryEntryData entryData = lotteryEntries.get(identifier);
		boolean prizeSet = false;
		LotteryEvents event = null;
		
		try {

			if(choice == 1)
				event = lotteryEvents.get(entryData.getFirstChoice());
			else if(choice == 2)
				event = lotteryEvents.get(entryData.getSecondChoice());
			else if(choice == 3)
				event = lotteryEvents.get(entryData.getThirdChoice());
			
			if(event.getSpacesLeft() != 0) {
				PersonData person = persons.get(identifier);
				prizeSet = person.setChoiceWon(event.getName(),event.getIdentifier(),choice);
				
				event.setSpacesLeft(event.getSpacesLeft()-1);
			}
		}catch(NullPointerException e) {
			if(entryData == null)
				System.out.println("There is no lottery entry data for person identifier " + identifier);
			else if(event == null){
				System.out.print("There is no event with identifier ");
				if(choice == 1)
					System.out.println(entryData.getFirstChoice());
				else if(choice == 2)
					System.out.println(entryData.getSecondChoice());
				else if(choice == 3)
					System.out.println(entryData.getThirdChoice());
			}else
				e.printStackTrace();
		}
		
		return prizeSet;
	}

	private static void printAllData() {
		for(int i : lotteryEvents.keySet()) {
			lotteryEvents.get(i).printString();
		}
		for(int i : lotteryEntries.keySet()) {
			lotteryEntries.get(i).printString();
		}
		for(int i : persons.keySet()) {
			persons.get(i).printData();
		}
	}
}
