package sorters;

public class SelectionSort {
	public static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int smallest = i;
			for (int j = i+1; j < array.length; j++) {
				
				if (array[smallest] > array[j]) {
					smallest = j;
				}
			}
			int t = array[i];
			array[i] = array[smallest];
			array[smallest] = t;
		}
	}
}
