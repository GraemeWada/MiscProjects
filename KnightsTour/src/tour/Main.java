package tour;

public class Main {
	public static void main(String[] args) {
		LinkedGrid g = new LinkedGrid(6,6);
//		g.display();
		long start = System.currentTimeMillis();
		g.tour(g.getRoot(), 1);
		System.out.println(g.getNumSlns()+" solutions found in "+(System.currentTimeMillis()-start) + " ms");
//		g.display();
	}
}
