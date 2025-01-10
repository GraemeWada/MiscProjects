package sorters;

public class InsertionSort {
	public static void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			for (int j = i-1; j >=0; j--) {
				if(array[j+1] < array[j]) {
					int t = array[j+1];
					array[j+1] = array[j];
					array[j] = t;
				}else{break;}
			}
		}
	}
}
