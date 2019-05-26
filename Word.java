/*
 * CSE5ALG
 * 19190687
 * Prasad Belhe
 * Word 
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {

	private String word;
	private int frequency;
	private ArrayList<String> neighbourList = new ArrayList<String>();


	public Word(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;

	}

	public int compareTo(Word words) {

		int wordDifference = word.compareToIgnoreCase(words.getWord());
		return wordDifference;
	}


	public String getWord() {
		return word;
	}

	public void setLength(int length) {
		this.frequency = length;
	}

	public int getFrequency() {

		return frequency;

	}

	public void setFrequency(){
		frequency++;
	}

	public void addNeighbour(String neighbor) {

		neighbourList.add(neighbor);

	}


	public ArrayList<String> getNeighbors() {

		return neighbourList;

	}


	public String toString() {

		return word+ " "+frequency+" "+neighbourList+"\n";

	}

}