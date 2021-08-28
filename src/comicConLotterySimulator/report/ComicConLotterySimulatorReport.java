package comicConLotterySimulator.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import comicConLotterySimulator.constant.FileName;
import comicConLotterySimulator.constant.FilePath;
import comicConLotterySimulator.object.LotteryEntry;
import comicConLotterySimulator.object.LotteryEvent;
import comicConLotterySimulator.object.Person;

public class ComicConLotterySimulatorReport {
	
	private static ComicConLotterySimulatorReport ccLotterySimulatorReport;
	
	private ComicConLotterySimulatorReport() {}
	
	public static ComicConLotterySimulatorReport getInstance() {
		if(ccLotterySimulatorReport == null)
			ccLotterySimulatorReport = new ComicConLotterySimulatorReport();
		return ccLotterySimulatorReport;
	}
	
	public void saveAllDataToFiles(Map<Integer, LotteryEvent> lotteryEventsMap, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap) throws IOException {
		for(int i : lotteryEventsMap.keySet()) {
			String fileName = lotteryEventsMap.get(i).getName().replaceAll("[^a-zA-Z0-9&]", "_") + FileName._WINNERS;
			saveFileData(fileName, lotteryEventsMap.get(i).getIdentifier(), lotteryEntryMap, personMap);
		}
		saveFileData(FileName.LOTTERY_LOSSES.getFileName(), 0, lotteryEntryMap, personMap);
	}

	
	private static void saveFileData(String fileName, int eventIdentifier, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap) throws IOException {
		File directory = new File(FilePath.LOTTERY_RESULTS_PATH.getFilePath());
		File file = new File(FilePath.LOTTERY_RESULTS_PATH + fileName);
		
		if(!directory.exists())
			directory.mkdir();
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(int i : personMap.keySet()) {
			Person person = personMap.get(i);
			if(person.getChoiceWonIdentifier() == eventIdentifier) {
				bw.write(person.toString());
				bw.newLine();
			}
		}
		
		bw.close();
	}
	
}
