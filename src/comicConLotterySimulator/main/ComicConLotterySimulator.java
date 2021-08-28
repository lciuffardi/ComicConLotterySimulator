package comicConLotterySimulator.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import comicConLotterySimulator.console.printer.ComicConLotterySimulatorConsolePrinter;
import comicConLotterySimulator.dao.ComicConLotterySimulatorDAO;
import comicConLotterySimulator.object.LotteryEntry;
import comicConLotterySimulator.object.LotteryEvent;
import comicConLotterySimulator.object.Person;
import comicConLotterySimulator.report.ComicConLotterySimulatorReport;

public class ComicConLotterySimulator {

	public static void main(String[] args) throws IOException {
		ComicConLotterySimulatorDAO ccLotterySimDAO = ComicConLotterySimulatorDAO.getInstance();
		ComicConLotterySimulatorReport ccLotterySimulatorReport = ComicConLotterySimulatorReport.getInstance();
		ComicConLotterySimulatorConsolePrinter ccLotterySimConsolePrinter = ComicConLotterySimulatorConsolePrinter.getInstance();
		
		Map<Integer, LotteryEvent> lotteryEventMap = new HashMap<>();
		Map<Integer, LotteryEntry> lotteryEntryMap = new HashMap<>();
		Map<Integer, Person> personMap = new HashMap<>();
		List<Integer> personIdentifiersList = new ArrayList<>();
		
		ccLotterySimDAO.loadAllFileData(lotteryEventMap, lotteryEntryMap, personMap, personIdentifiersList);
		startLottery(personIdentifiersList, lotteryEventMap, lotteryEntryMap, personMap);
		ccLotterySimulatorReport.saveAllDataToFiles(lotteryEventMap, lotteryEntryMap, personMap);
		ccLotterySimConsolePrinter.printAllData(lotteryEventMap, lotteryEntryMap, personMap);
	}
	
	private static void startLottery(List<Integer> personIdentifiersList, Map<Integer, LotteryEvent> lotteryEventMap, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap) {
		while(personIdentifiersList.size() != 0) {
			boolean prizeSet = false;
			int choice = 1;
			Random rand = new Random();
			int x = rand.nextInt(personIdentifiersList.size());
			while(prizeSet == false && choice <= 3) {
				prizeSet = setPrize(personIdentifiersList.get(x), choice, lotteryEventMap, lotteryEntryMap, personMap);
				choice++;
			}
			personIdentifiersList.remove(x);
		}
	}
	
	private static boolean setPrize(int identifier, int choice, Map<Integer, LotteryEvent> lotteryEventMap, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap) {
		LotteryEntry entryData = lotteryEntryMap.get(identifier);
		boolean prizeSet = false;
		LotteryEvent event = null;
		
		try {

			if(choice == 1)
				event = lotteryEventMap.get(entryData.getFirstChoice());
			else if(choice == 2)
				event = lotteryEventMap.get(entryData.getSecondChoice());
			else if(choice == 3)
				event = lotteryEventMap.get(entryData.getThirdChoice());
			
			if(event.getSpacesLeft() != 0) {
				Person person = personMap.get(identifier);
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

}
