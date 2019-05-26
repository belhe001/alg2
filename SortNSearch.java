/*
 * CSE5ALG
 * 19190687
 * Prasad Belhe
 * SortNSearch
 * For Sorting and searching word
 */


import java.util.List;

public class SortNSearch {
	
	
	// Generic method for Shell Sort Generic Data
	public static <E extends Comparable<E>>void shellShellSort(List<E> list) {
		
		int[] gaps = getShellGapSequence(list.size());
		shellSort(list, gaps);

	}
	
	//shell gap sequence used for creating gap
	public static int[] getShellGapSequence(int n){
		
		int count = (int)Math.floor(Math.log(n)/Math.log(2));
		int[] gaps = new int[count];
		int h = n/2;
		
		for(int i=0; i<gaps.length;i++){
			
			gaps[i] = h;
			h = h/2;
			
		}
		
		
		return gaps;		
	}
	
	
	public static <E extends Comparable<E>>void shellSort(List<E> list, int[] gaps){
		//int left = 0;
		//int right = list.size()-1;
		for(int i=0;i<gaps.length;i++){
			
			
			hSort(list, gaps[i]);
			
		}
		
	}
	
	public static <E extends Comparable<E>>void hSort(List<E> list, int h) {
		
		for(int i=0; i<h; i++){
			
			sortGappedSubArray(list, h, i);
			
		}
	}
	
	private static <E extends Comparable<E>>void sortGappedSubArray(List<E> list,int h, int first) {
		
		for(int i = first+h;i<list.size();i=i+h){
			
			insertElements(list,h,first,i);
			
		}
		
	}
	
	private static <E extends Comparable<E>>void insertElements(List<E> list, int h, int first, int next) {
		
		E value = list.get(next);
		int i = next;
		
		while(true) {
			
			if(i == first) {
				
				list.set(first,value);
				return;
				
			}else if(list.get(i-h).compareTo(value) <= 0) {
				
				list.set(i, value);
				return;
				
			}else {
				
				list.set(i, list.get(i-h));
				i = i-h;
				
			}
			
		}
		
	}
	
	/*
	* Quick sort for Generic data
	*/
	public static <E extends Comparable<E>>void quickSort(List<E> list) {
		
		quickSort(list, 0,list.size()-1);
		
	}
	
	
	private static <E extends Comparable<E>>void quickSort(List<E> list, int left, int right) {
		int pivotIndex = -1;
		if(left < right) {
			
			pivotIndex = partition(list, left, right);
			quickSort(list,left, pivotIndex-1);
			quickSort(list,pivotIndex+1, right);
		}
		
	}
	
	private static <E extends Comparable<E>>int partition(List<E> list, int left, int right) {
		
		int mid = (left+right) / 2;
		E pivotElement = list.get(mid);
		swap(list, mid, right);
		
		while(left < right) {
			
			while(left < right && list.get(left).compareTo(pivotElement) <= 0){
				
				left++;
			}
			if(left < right){
				
				swap(list, left, right);
				right--;
			}
		  
		    while(right > left && list.get(right).compareTo(pivotElement) >= 0) {
				
				right--;
			}
			if(right > left) {
				
				swap(list,left,right);
				left++;
			}
		  
		}
		return left;
	}
	
	// Swap elments at positions i and j
	//
	private static<E extends Comparable<E>>
	void swap(List<E> list, int i, int j)
	{
		E temp = list.get(i);
		list.set(i, list.get(j));
	 	list.set(j, temp);
	}
	
	public static void findNeighbouringElements(List<Word> wordList) { 
		// find elements which is different by one element at one particular position
		
		for (int i=0; i< wordList.size(); i++) {
			
			for(int j = 0; j<wordList.size(); j++) {
				
				if(wordList.get(i).getWord().length() == wordList.get(j).getWord().length() 
						&& Utility.neighbourFoundCode(wordList.get(i).getWord(), wordList.get(j).getWord())) {
					
					wordList.get(i).addNeighbour(wordList.get(j).getWord());
					
				}
				
			}
			
		}
		
	}
	
}