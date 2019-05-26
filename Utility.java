/*
 * CSE5ALG
 * 19190687
 * Prasad Belhe
 * Utility
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Utility {

	public static String removeSpecialCharacters(String wordToModify, ArrayList<Word> wordList) {
		// remove all the special characters from word using regex pattern
		String modifiedWord = wordToModify.replaceAll("[^a-zA-Z]+", "").toLowerCase();
		for(Word word: wordList) {
			if(word.getWord().toLowerCase().equals(modifiedWord)) {
				word.setFrequency();
				return "-1";
			}
		}
		return modifiedWord;
	}



	// For finding neighbouring elements
	public static boolean neighbourFoundCode(String mainWord, String neighborWord) {

		boolean isNeighbor = false;
		String word1 = mainWord.toLowerCase().trim();
		String word2 = neighborWord.toLowerCase().trim();

		int counter = 0;
		for(int i=0; i<word1.length();i++) {

			if(word1.charAt(i) !=  word2.charAt(i)) {
				counter++;
			}

			if(counter > 1){
				break;
			}

		}
		if(counter == 1) {

			isNeighbor = true;

		}

		return isNeighbor;
	}


	// To find matching words for the pattern supplied to this method
	public static void findMatchingPatternWords(String pattern, ArrayList<Word> wordList, ArrayList<String> patternOut)throws Exception { 

		String regexPattern = "";
		boolean patternMatched = false;

		String firstLine = "\nThe pattern:\n"+pattern+"\n May result in the words below";
		System.out.println(firstLine);
		patternOut.add(firstLine);

		regexPattern = pattern.replaceAll("\\?",".");
		regexPattern = regexPattern.replaceAll("\\*","\\\\\\D\\*");

		for(Word word: wordList) {

			if(Pattern.matches(regexPattern,word.getWord())) {
				System.out.println(word.getWord()+" "+word.getFrequency());
				patternOut.add(word.getWord()+" "+word.getFrequency());
				patternMatched = true;
			}

		}

		if(!patternMatched) {
			System.out.println("No Words in the lexicon match the pattern");
			patternOut.add("No Words in the lexicon match the pattern");
		}
	}

	// write data to the file
	public static void writeTofile(ArrayList patternList, String fileName)throws Exception {

		PrintWriter pw = new PrintWriter(new FileOutputStream(new File(fileName), true));
		for(Object word: patternList){
			pw.println(word.toString());
		}
		pw.close();
	}


}