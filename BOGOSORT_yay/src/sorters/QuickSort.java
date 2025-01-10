package sorters;

public class QuickSort {
	public static void sort(int[] array, int low, int high) {
		if (low < high) {
			int pivot = getPivot(array, low, high);
			sort(array, low, pivot - 1);
			sort(array, pivot + 1, high);
		}
	}
	
	static int getPivot(int[] array, int low, int high) {
		int pivot = low;
		for (int j = low + 1; j <= high; j++) {
			if(array[j] < array[low])
				swap(array, ++pivot, j);
		}
		swap(array, low, pivot);
		return pivot;
	}
	
	static void swap(int[] array, int i1, int i2) {
		int t = array[i1];
		array[i1] = array[i2];
		array[i2] = t;
	}
}
