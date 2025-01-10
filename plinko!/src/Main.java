
public class Main {
    public static void main(String[] args) {
       Plinko plinko = new Plinko(7);
       for (int i = 0; i < 9; i++)
         plinko.simulate(i, 1000000);
    	
//      for (int i = 2; i < 51; i+=2){
//        Plinko plinko = new Plinko(i);
//        plinko.simulate(4,100000); 
//      }
    	
//    	for (int i = 2; i < 21; i+=2){
//            Plinko plinko = new Plinko(i);
//            plinko.simulateRandomStart(10000); 
//        }
      
    }
}
 