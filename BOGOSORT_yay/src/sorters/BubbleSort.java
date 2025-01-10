package sorters;

public class BubbleSort {
	public static void sort(int[] array) {
		boolean sorted = false;
		boolean swaps;
		int iter = 0;
		while (!sorted) {
			iter++;
			swaps = false;
			for (int i = 0; i < array.length - 1; i++) {
				if(array[i]>array[i+1]) {
					int t = array[i];
					array[i] = array[i+1];
					array[i+1] = t;
					swaps = true;
				}
			}
			if(!swaps) {sorted=true;System.out.println("sorted after "+iter);}
		}
	}
}
