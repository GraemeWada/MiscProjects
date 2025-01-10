public class Plinko {

  Node[] topRow = new Node[9];
  Node[] bottomRow = new Node[9];
  int rows = 0;

  public Plinko(int numOddRows) {
    rows = numOddRows * 2 - 1;

    for (int i = 0; i < 9; i++){
      topRow[i] = new Node();
      topRow[i].setLeft(new Node());
      topRow[i].getLeft().setUpR(topRow[i]);
    }
    for (int i = 0; i < 9; i++){
      if(i == 8){
        topRow[i].setRight(new Node());
        topRow[i].getRight().setUpL(topRow[i]);
      } else {
        topRow[i].setRight(topRow[i+1].getLeft());
      }
      topRow[i].getRight().setUpL(topRow[i]);
    }
    
    Node rowMarker = topRow[0].getLeft();
    Node colMarker = rowMarker;

    for(int i = 2; i < (numOddRows*2)-1; i++){
      
      if(i % 2 == 0){
        for (int j = 0; j < 9; j++){
          colMarker.setRight(new Node());
          colMarker.getRight().setUpL(colMarker);
          try { colMarker = colMarker.getUpR().getRight(); } catch (NullPointerException e) {}
        }
        colMarker = rowMarker.getUpR().getRight();
        for (int j = 0; j < 9; j++){
          colMarker.setLeft(colMarker.getUpL().getLeft().getRight());
          colMarker.getLeft().setUpR(colMarker);
          try { colMarker = colMarker.getUpR().getRight(); } catch (NullPointerException e) {}
        }
        
        if(i == (numOddRows*2)-2){
          colMarker = rowMarker;
          for (int j = 0; j < 9; j++){
            bottomRow[j] = colMarker.getRight();
            bottomRow[j].setPrize(j);
            colMarker = colMarker.getUpR().getRight();
          }
        }

        rowMarker = rowMarker.getRight();
        colMarker = rowMarker;
      } else {
        for (int j = 0; j < 9; j++){
          colMarker.setLeft(new Node());
          colMarker.getLeft().setUpR(colMarker);
          try { colMarker = colMarker.getUpR().getRight(); } catch (NullPointerException e) {}
        }
        colMarker = rowMarker;
        for (int j = 0; j < 9; j++){
          if(j == 8) {
            colMarker.setRight(new Node());
            colMarker.getRight().setUpL(colMarker);
          } else {
            colMarker.setRight(colMarker.getUpR().getRight().getLeft());
            colMarker.getRight().setUpL(colMarker);
            try { colMarker = colMarker.getUpR().getRight(); } catch (NullPointerException e) {}
          }
        }
        rowMarker = rowMarker.getLeft();
        colMarker = rowMarker;
      }
    }
  }

  void simulateRandomStart (int iterations){
    int[] results = new int[9];
    int[] scores = {100, 500, 1000, 0, 100000, 0, 1000, 500, 100};
    long total = 0;
    System.out.println("Start Slot: Random     Iterations: "+iterations + "     Rows: "+rows);
    for (int i = 0; i < iterations; i++){
      int start = (int)(Math.random()*9);
      results[simulate(start)]++;
    }
    for (int i = 0; i < 9; i++){
      System.out.println("SLOT " + i + ": "+results[i]);
      System.out.println("SLOT " + i + " Percentage: "+((float)results[i]/(float)(iterations)) * 100);
      total+=(long)results[i]*(long)scores[i];
    }
    System.out.println("AVG: "+total/iterations);
  }

  void simulate (int start, int iterations){
    int[] results = new int[9];
    int[] scores = {100, 500, 1000, 0, 100000, 0, 1000, 500, 100};
    long total = 0;
    System.out.println("Start Slot: "+start+"     Iterations: "+iterations + "     Rows: "+rows);
    for (int i = 0; i < iterations; i++){
      results[simulate(start)]++;
    }
    for (int i = 0; i < 9; i++){
      System.out.println("SLOT " + i + ": "+results[i]);
      System.out.println("SLOT " + i + " Percentage: "+((float)results[i]/(float)(iterations)) * 100);
      total+=(long)results[i]*(long)scores[i];
    }
    System.out.println("AVG: "+total/iterations);
  }

  int simulate(int start){
    Node n = topRow[start];
    for (int i = 0; i < rows; i++) {
      Node prevN = n;
      n = n.getNext();
      if(n == null) {
        // System.out.println("finished" + prevN.getPrize());
        return prevN.getPrize();
      }
    }
    return 0; 
  }

}