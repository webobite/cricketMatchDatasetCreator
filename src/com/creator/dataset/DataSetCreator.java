package com.creator.dataset;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author subham 
 * 		   Data Set Creator a service on the cloud of your choice that
 *         accepts the text file containing the scores of all the cricketers for
 *         the last 10 years and publishes the top 5 batsman basis for each of
 *         the below criteria
 */

public class DataSetCreator {
	
	/**
	 * playerList[]
	 * matchType[]
	 * noOfRow
	 */
	private static String[] playerList;
	private static String[] matchType;
	private static int noOfRow = 50000;

	static {
		playerList = new String[5];
		playerList[0] = "Sachin";
		playerList[1] = "Virat";
		playerList[2] = "Rohit";
		playerList[3] = "Shikhar";
		playerList[4] = "Dravid";

		matchType = new String[3];
		matchType[0] = "T20";
		matchType[1] = "1Day";
		matchType[2] = "Test";
	}
	
	/**
	 * get Random Number with min and max range 
	 * @param min
	 * @param max
	 * @return randomNumber
	 */
	public int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	
	/**
	 * create row for appending the data in CSV file
	 * @param dataSet
	 * @return
	 */
	public static List<List<String>> createRow(DataSetCreator dataSet) {
		int selectedPlayer = dataSet.getRandomNumber(0, 4);
		int selectedMatchType = dataSet.getRandomNumber(0, 2);
		String year = Integer.toString(dataSet.getRandomNumber(2000, 2020));
		String score = Integer.toString(dataSet.getRandomNumber(25, 220));
		return Arrays.asList(Arrays.asList(playerList[selectedPlayer], matchType[selectedMatchType], score, year));
	}

	public static void main(String[] args) {

		System.out.print("Creating Dataset");
		try {
			DataSetCreator dataSet = new DataSetCreator();
			FileWriter csvWriter = new FileWriter("match.csv");
			System.out.println(createRow(dataSet));
			
			List<List<String>> rows = null;
			for (int i = 0; i < noOfRow; i++) {
				rows = createRow(dataSet);
				for (List<String> rowData : rows) {
					csvWriter.append(String.join(",", rowData));
					csvWriter.append("\n");
				}
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
