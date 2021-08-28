package comicConLotterySimulator.console.printer;

import java.util.Map;

import comicConLotterySimulator.object.LotteryEntry;
import comicConLotterySimulator.object.LotteryEvent;
import comicConLotterySimulator.object.Person;

public class ComicConLotterySimulatorConsolePrinter {
	
	private static ComicConLotterySimulatorConsolePrinter ccLotterySimConsolePrinter;
	
	private ComicConLotterySimulatorConsolePrinter() {}
	
	public static ComicConLotterySimulatorConsolePrinter getInstance() {
		if(ccLotterySimConsolePrinter == null)
			ccLotterySimConsolePrinter = new ComicConLotterySimulatorConsolePrinter();
		return ccLotterySimConsolePrinter;
	}
	
	public void printAllData(Map<Integer, LotteryEvent> lotteryEventMap, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap) {
		for(int i : lotteryEventMap.keySet()) {
			System.out.println(lotteryEventMap.get(i).toString());
		}
		for(int i : lotteryEntryMap.keySet()) {
			System.out.println(lotteryEntryMap.get(i).toString());
		}
		for(int i : personMap.keySet()) {
			personMap.get(i).toString();
		}
	}
	
}
