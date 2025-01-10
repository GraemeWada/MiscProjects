package sorters;

public class CountingSort {
	public static int[] sort(int[] in) {
		int max = getMax(in);
		int[] count = new int[max+1];
		int[] out = new int[in.length];
		//get counts
		for (int i = 0; i < in.length; i++) {
			count[in[i]] += 1;
		}
		//cumulative sum
		for (int i = 1; i < count.length; i++) {
			count[i] = count[i]+count[i-1];
		}
		//sort
		for (int i = in.length-1; i >= 0; i--) {
			out[count[in[i]]-1] = in[i];
			count[in[i]]--;
		}
		return out;
	}
	
	static int getMax(int[] a) {
		int max = a[0];
		for (int i : a) {
			if(i > max) { max = i; }
		}
		return max;
	}
}
