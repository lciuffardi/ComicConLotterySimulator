package comicConLotterySimulator.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import comicConLotterySimulator.constant.FileName;
import comicConLotterySimulator.constant.FilePath;
import comicConLotterySimulator.object.LotteryEntry;
import comicConLotterySimulator.object.LotteryEvent;
import comicConLotterySimulator.object.Person;

public class ComicConLotterySimulatorDAO {
	
	private static ComicConLotterySimulatorDAO ccLotterySimDAO;
	
	private ComicConLotterySimulatorDAO() {}
	
	public static ComicConLotterySimulatorDAO getInstance() {
		if(ccLotterySimDAO == null)
			ccLotterySimDAO = new ComicConLotterySimulatorDAO();
		return ccLotterySimDAO;
	}
	
	public void loadAllFileData(Map<Integer, LotteryEvent> lotteryEventMap, Map<Integer, LotteryEntry> lotteryEntryMap, Map<Integer, Person> personMap, List<Integer> personIdentifierList) throws NumberFormatException, IOException {

		for(FileName fileName : FileName.getResFileNameList()) {
			FileReader fr = new FileReader(FilePath.RES_PATH.getFilePath() + fileName.getFileName());
			BufferedReader br = new BufferedReader(fr);
			String[] lotteryData = null;
			String line;
			
			br.readLine();
			while((line=br.readLine())!= null) {
				lotteryData=line.split(",");
				switch(fileName) {
				case LOTTERY_ITEM_DATA:
					lotteryEventMap.put(Integer.parseInt(lotteryData[0]), new LotteryEvent(lotteryData[0], lotteryData[1], lotteryData[2]));
					break;
				case LOTTERY_ENTRY_DATA:
					lotteryEntryMap.put(Integer.parseInt(lotteryData[0]), new LotteryEntry(lotteryData[0], lotteryData[1], lotteryData[2], lotteryData[3]));
					break;
				case PERSON_DATA:
					personMap.put(Integer.parseInt(lotteryData[0]), new Person(lotteryData[0], lotteryData[1], lotteryData[2], lotteryData[3]));
					personIdentifierList.add(Integer.parseInt(lotteryData[0]));
					break;
				default:
					break;
				}
			}
			
			fr.close();
			br.close();
		}
		
	}
	
}
