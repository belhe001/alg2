/*
 * CSE5ALG
 * 19190687
 * Prasad Belhe
 * Wordmatcher
 * Main
 * 
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WordMatcher {

	private static ArrayList<Word> 		wordList = new ArrayList<Word>();
	private static ArrayList<String> patternList  = new ArrayList<String>();
	private static ArrayList<String> patternOut  = new ArrayList<String>();

	private static String inputFile1 = "";
	private static String inputFile2 = "";
	private static String outputFile1 = "";
	private static String outputFile2 = "";

	public static void main(String[] args)throws Exception {

		try{
			inputFile1 = args[0];
			outputFile1 = args[1];
			inputFile2 = args[2];
			outputFile2 = args[3];

			//inputFile1 = "in1.txt";
			//inputFile2 = "in2.txt";
			//outputFile1 = "out1.txt";
			//outputFile2 = "out2.txt";
			new File(outputFile1).delete();
			new File(outputFile2).delete();
		}catch(Exception e) {
			throw new Exception("You forgot to specify the names of the input and output files");
		}


		Thread threadload = new Thread(){
			public void run(){
				threadloader();
			}
		};
		threadload.run();
		threadload.join();
		System.out.println("Finished reading files");


		//sort alphabetically
		SortNSearch.quickSort(wordList);
		SortNSearch.findNeighbouringElements(wordList);

		//find pattern in patternOut
		for(String pl : patternList ) {
			Utility.findMatchingPatternWords(pl, wordList,patternOut);
		}


		//store words and patternOut
		Thread threadstore = new Thread(){
			public void run(){
				threadstorer();
			}
		};

		threadstore.run();

	}

	// For threaded loading data from the file specified in the filename
	public static void threadloader()

	{
		Thread threadloadData = new Thread(){
			public void run(){
				try {
					loadData(inputFile1);
				} catch (Exception e) {
					System.err.println("Error in loading data: "+e.getMessage());
				}
			}
		};

		Thread threadloadPattern = new Thread(){
			public void run(){
				try {
					loadPattern(inputFile2);
				} catch (Exception e) {
					System.err.println("Error in loading pattern: "+e.getMessage());
				}
			}
		};
		threadloadData.run();
		threadloadPattern.run();
	}

	// For threaded loading data from the file specified in the filename
	public static void threadstorer()

	{
		Thread threadstoreData = new Thread(){
			public void run(){
				try {
					//loadData(inputFile1);
					Utility.writeTofile(wordList, outputFile1);
					System.out.println("Word Stored");

				} catch (Exception e) {
					System.err.println("Error in storing data: "+e.getMessage());
				}
			}
		};

		Thread threadstorePattern = new Thread(){
			public void run(){
				try {
					Utility.writeTofile(patternOut, outputFile2);
					System.out.println("Pattern stored");
				} catch (Exception e) {
					System.err.println("Error in storing pattern: "+e.getMessage());
				}
			}
		};
		threadstoreData.run();
		threadstorePattern.run();
	}

	// For loading data from the file specified in the filename
	public static void loadData(String fileData) throws IOException

	{
		Scanner infile1 = new Scanner(new File(fileData));

		while(infile1.hasNext())
		{
			String name = infile1.nextLine();

			String [ ] tokens = name.split(" ");

			for (String s : tokens )
			{
				String newWord = Utility.removeSpecialCharacters(s, wordList);

				if(newWord.length() > 0 && !newWord.equalsIgnoreCase("-1")) {
					Word words = new Word(newWord,1);
					wordList.add(words);
				}
			}
		}
	}



	static void loadPattern(String filePattern) throws IOException

	{

		Scanner infile = new Scanner(new File(filePattern));
		while(infile.hasNext())
		{
			//matching pattern, one per line
			patternList.add( infile.nextLine());
		}
	}

	public static void writeData(String fileName)
			throws IOException
	{
		PrintWriter outFile = new PrintWriter(new File(fileName));

		outFile.println();

		outFile.close();
	}

}