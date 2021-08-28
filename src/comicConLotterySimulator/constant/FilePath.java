package comicConLotterySimulator.constant;

public enum FilePath {
	RES_PATH(System.getProperty("user.dir") + "\\res\\"),
	LOTTERY_RESULTS_PATH(System.getProperty("user.dir") + "\\results\\");
	
	private final String filePath;	
	
	FilePath(String filePath){
		this.filePath = filePath;
		
	}

	public String getFilePath() {
		return filePath;
	}
			
}
