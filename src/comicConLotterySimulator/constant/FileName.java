package comicConLotterySimulator.constant;

import java.util.Arrays;
import java.util.List;

public enum FileName {
	LOTTERY_ITEM_DATA("Lottery_Item_Data"),
	LOTTERY_ENTRY_DATA("Lottery_Entry_Data"),
	PERSON_DATA("Person_Data"),
	LOTTERY_LOSSES("Lottery_Losses"),
	_WINNERS("_Winners");
	
	private final String fileName;
	private static List<FileName> resFileNameList;
	
	FileName(String fileName){
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public static List<FileName> getResFileNameList(){
		if(resFileNameList == null)
			resFileNameList = Arrays.asList(LOTTERY_ITEM_DATA, LOTTERY_ENTRY_DATA, PERSON_DATA);
		return resFileNameList;
	}
	
}
