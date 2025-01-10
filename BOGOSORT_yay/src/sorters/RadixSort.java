package sorters;

public class RadixSort {
	public static void sort(int[] array) {
		//get largest number
		int max = getMax(array);
		
		//starting at ones digit (i=1), sort
		//by ones place, then multiply i by 10 to now sort 10s place
		//repeat until max/i == 0 since that would mean reached max digits (e.g. 97/100 = 0 because of integer type)
		// int/int always rounds down
		for (int i = 1; max / i > 0; i *= 10) {
			digitCountSort(array, i);
		}
	}
	
	static void digitCountSort(int[] in, int digit) {
		int[] count = new int[10];
		int[] out = new int[in.length];
		//get counts
		for (int i = 0; i < in.length; i++) {
			//gets remainder after divide by digit
			//ex. 653/10 = 65 (because int) 65 % 10 = 5
			count[(in[i]/digit)%10] ++;
			System.out.println((in[i]/digit)%10);
		}
		//cumulative sum
		for (int i = 1; i < count.length; i++) 
			count[i] = count[i]+count[i-1];
		
		//sort
		for (int i = in.length-1; i >= 0; i--) {
			//get index of number in[i] in output array (count[(in[i]/digit)%10]-1) and set that index in output to input[i]
			out[count[(in[i]/digit)%10]-1] = in[i];
			//subtract one from the index number for next iteration
			count[(in[i]/digit)%10]--;
		}
		//write
		for (int i = 0; i < in.length; i++) 
			in[i] = out[i];
	}
	
	static int getMax(int[] a) {
		int max = a[0];
		for (int i : a) {
			if(i > max) { max = i; }
		}
		return max;
	}
}
