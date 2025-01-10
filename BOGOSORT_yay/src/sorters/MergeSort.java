package sorters;

public class MergeSort {
	public static void mergeSort(int[] array) {
		mergeSort(array, 0, array.length-1);
	}
	
	public static void merge(int[] array, int low, int mid, int high) {
		//beginning of right part of array
		int low1 = mid+1;
		
		while (low <= mid && low1 <= high) {
			//if left pointer < right pointer
			if(array[low]<array[low1]) {
				//increment left pointer; array[low] is in the right place
				low++;
			} else {
				//store value of array[low1];
				int n = array[low1];
				int i = low1;
				
				//shift all right until low
				while (i != low) {
					array[i] = array[i-1];
					i--;
				}
				//put stored value at low
				array[low] = n;
				
				//increment all
				low++; mid++; low1++;
			}
		}
	}
	
	public static void mergeSort(int[] array, int begin, int end) {
		//if more than 0 elements
		if(end-begin >= 1) {
			int p = (begin+end)/2;
			//split
			mergeSort(array, begin, p);
			mergeSort(array, p+1, end);
			//merge and sort halves
			merge(array,begin,p,end);
		}
	}
	
}
