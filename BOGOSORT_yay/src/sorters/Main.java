package sorters;

import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random r = new Random();
		int[] array = new int[1000];
		for (int i = 0; i < array.length; i++) {array[i]=r.nextInt(1000);}
		//int[] array = {4,3,2,1};
		printArray(array);
		
		//BubbleSort.sort(array);
		//SelectionSort.sort(array);
		//InsertionSort.sort(array);
		// array = CountingSort.sort(array);
		//RadixSort.sort(array);
		QuickSort.sort(array, 0, array.length-1);
		//MergeSort.mergeSort(array);
		
		printArray(array);
		System.out.println(checkSorted(array) ? "Sorted" : "Unsorted");
		//if(checkSorted(array)) {System.out.println("Sorted");}else {System.out.println("unsorted");}
	}
	
	public static void printArray(int[] array) {
		for (int i : array) 
			System.out.print(i+", ");
		System.out.println("\n");
	}
	
	static boolean checkSorted(int[] array) {
		for (int i = 0; i < array.length - 1; i++)
			if (array[i]>array[i+1]) 
				return false;
		return true;
	}
}
