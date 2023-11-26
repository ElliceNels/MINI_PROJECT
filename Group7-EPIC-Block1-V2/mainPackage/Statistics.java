package mainPackage;

public class Statistics {
	public static history[] get_player_history(String user_id) {

        return DB_PlayHistory.returnAllHistoryOfUser(user_id);
	}

	public static history[] get_all_history() {
		return DB_PlayHistory.returnAllHistory();
	}
	public static int[] history_to_scores(history[] player_history) {
		int[] scores = new int[player_history.length];
		for (int i = 0; i < player_history.length; i++) {
			scores[i] = player_history[i].getScore_of_round();
		}
		return scores;
	}
	public static void main(String[]args) {
		//At the end of each round:
		//- The number of rounds played should increase by 1 (a per user measure, each user has their own)
		//- The Total score should be increased by round score (which is also stored in play mainPackage.history i think)(per user measure)
		//- The popRoundsPlayed should increase by one (for every user)
		//- The popTotalScore should also be increased by round score (for every user)
		history[] player_history = DB_PlayHistory.returnAllHistoryOfUser(launcher.user_ID);
		int[] sean_historyScore = new int[player_history.length];
		for (int i = 0; i < player_history.length; i++) {
			sean_historyScore[i] = player_history[i].getScore_of_round();
		}

		history[] totalHistory = DB_PlayHistory.returnAllHistory();
		int[] totalPlayHistory = new int[totalHistory.length];
		for (int i = 0; i < totalHistory.length; i++) {
			totalPlayHistory[i] = totalHistory[i].getScore_of_round();
		}
		//Population (idk whether theres a special name for the area holding population details
		
		//DATA DISPLAY
		int roundsPlayed = sean_historyScore.length;
		int popRoundsPlayed = totalPlayHistory.length;
		System.out.println("Rounds Played by this User: " + roundsPlayed);
		System.out.println("Total Score by this User: " + tScore(sean_historyScore));
		System.out.println("Mean Score: " + (soloMean(tScore(sean_historyScore), roundsPlayed)));
		System.out.println("Median Score: " + soloMedian(sean_historyScore));
		System.out.println("Standard Deviation: " + soloStatndardDev(sean_historyScore, roundsPlayed));
		
		System.out.println("");
		
		System.out.println("Population Total Rounds Played: " + popRoundsPlayed);
		System.out.println("Population Total Score: " + ptScore(totalPlayHistory));
		System.out.println("Population Mean Score: " + (popMean(ptScore(totalPlayHistory), popRoundsPlayed)));
		System.out.println("Population Median Score: " + popMedian(totalPlayHistory));
		System.out.println("Population Standard Deviation: " + popStandardDev(totalPlayHistory, popRoundsPlayed));
		
	}

	public static stat[] createPlayerStats(String User_ID){
		// get player mainPackage.history
		history[] player_history = DB_PlayHistory.returnAllHistoryOfUser(User_ID);
		int[] player_history_scores = history_to_scores(player_history);

		// create array of stats
		stat[] player_stats = new stat[5];

		// rounds played
		player_stats[0] = new stat(User_ID, "number of rounds played", player_history_scores.length);

		// total score
		player_stats[1] = new stat(User_ID, "total score", tScore(player_history_scores));

		// mean score
		player_stats[2] = new stat(User_ID, "mean score", soloMean(tScore(player_history_scores), player_history_scores.length));

		// median score
		player_stats[3] = new stat(User_ID, "median score", soloMedian(player_history_scores));

		// standard deviation
		player_stats[4] = new stat(User_ID, "standard deviation", soloStatndardDev(player_history_scores, player_history_scores.length));

		return player_stats;

	}

	public static double soloMean(int totalScore, int roundsPlayed) {//*****
		double mean = (double) totalScore/roundsPlayed; //Calculates mean
		return Math.round(mean * 100.0) / 100.0;
		}
	
	public static int tScore(int[] playHistory) { //Total Score
		int totalScore = 0;
		for (int i = 0; i <= playHistory.length-1; i++) { //Adds all of the scores together
			totalScore += playHistory[i];
		}
		return Math.round(totalScore * 100)/100;
	}

	public static void Sort(int[] playHistory) {
		for (int i = 0; i <= playHistory.length - 1; i++) {
			int currentMin = playHistory[i];//assume first number is smallest
			int currentMinIndex = i; //and its index
			
			for (int j = i + 1; j < playHistory.length; j++) {
				if (currentMin > playHistory[j]) {//if the next number is smaller
					currentMin = playHistory[j];//then that becomes smallest number
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) { //if the smaller number is not in the correct position then switch
				int temp = playHistory[currentMinIndex];
				playHistory[currentMinIndex]= playHistory[i]; //change position to the first number
				playHistory[i] = temp;
			}
		}

	}

	public static double soloMedian(int[] playHistory) {
		Sort(playHistory); //sorts array
		double median = 0;
		int start = 0;
		int end = playHistory.length-1;
		int mid = start +(end - start)/2; //finds index of middle
		if (playHistory.length % 2 == 0) { //if array is even
			double temp = (playHistory[mid] + playHistory[mid+1]);
			median = temp/2;
		}else {
			median = playHistory[mid];//if array is odd
		}
		return Math.round(median * 100)/100;
	}

	public static double soloStatndardDev(int[] playHistory, int roundsPlayed) {//*****
		double step1 = 0;
		for (int i = 0; i <=playHistory.length-1;i++) {
			 step1 = (double)(playHistory[i] - ((double)soloMean(tScore(playHistory), roundsPlayed)))*(double)(playHistory[i] - (soloMean(tScore(playHistory), roundsPlayed)));
		}
		double step2 = (double) step1/playHistory.length;
		double standDev = (double)Math.pow(step2, 0.5);
		return Math.round(standDev * 100)/100;
	}

	public static int ptScore(int[] totalPlayHistory) { 
		int popTotalScore = 0;
		for (int i = 0; i <= totalPlayHistory.length-1; i++) { //Adds all of the scores together
			popTotalScore += totalPlayHistory[i];
		}
		return Math.round(popTotalScore * 100)/100;
	}
	
	public static double popMean(int popTotalScore, int popRoundsPlayed) {//*****
		double mean = (double)popTotalScore/popRoundsPlayed; //Calculates mean
		return Math.round(mean * 100)/100;
	}

	public static void popSort(int[] totalPlayHistory) {
		for (int i = 0; i <= totalPlayHistory.length - 1; i++) {
			int currentMin = totalPlayHistory[i];//assume first number is smallest
			int currentMinIndex = i; //and its index
			
			for (int j = i + 1; j < totalPlayHistory.length; j++) {
				if (currentMin > totalPlayHistory[j]) {//if the next number is smaller
					currentMin = totalPlayHistory[j];//then that becomes smallest number
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) { //if the smaller number is not in the correct position then switch
				int temp = totalPlayHistory[currentMinIndex];
				totalPlayHistory[currentMinIndex]= totalPlayHistory[i]; //change position to the first number
				totalPlayHistory[i] = temp;
			}
		}

	}
	
	public static double popMedian(int[] totalPlayHistory) {
		Sort(totalPlayHistory); //sorts array
		double median = 0;
		int start = 0;
		int end = totalPlayHistory.length-1;
		int mid = start +(end - start)/2; //finds index of middle
		if (totalPlayHistory.length % 2 == 0) { //if array is even
			double temp = (double)(totalPlayHistory[mid] + totalPlayHistory[mid+1]);
			median = (double)temp/2;
		}else {
			median = totalPlayHistory[mid];//if array is odd
		}
		return Math.round(median * 100)/100;
	}

	public static double popStandardDev(int[] totalPlayHistory, int popRoundsPlayed) {//*****
		double step1 = 0;
		for (int i = 0; i <=totalPlayHistory.length-1;i++) {
			 step1 = (double)(totalPlayHistory[i] - ((double)popMean(ptScore(totalPlayHistory), popRoundsPlayed)))*(double)(totalPlayHistory[i] - (popMean(ptScore(totalPlayHistory), popRoundsPlayed)));
		}
		double step2 = (double) step1/totalPlayHistory.length;
		double standDev = (double)Math.pow(step2, 0.5);
		return Math.round(standDev * 100)/100 ;
	}
	


}
