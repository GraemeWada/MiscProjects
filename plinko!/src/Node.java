public class Node {
  private Node left;
  private Node right;
  private Node upL;
  private Node upR;
  private int prize;

  public Node() {
   prize = -1; 
  }

  public Node(int prize){
    this.prize = prize;
  }

  public void setPrize(int prize) {
    this.prize = prize;
  }

  public int getPrize(){
    return prize;
  }

  public void setLeft(Node left){
    this.left = left;
  }
  public void setRight(Node right){
    this.right = right;
  }

  public Node getLeft(){
    return left;
  }
  public Node getRight(){
    return right;
  }

   public void setUpL(Node left){
    upL = left;
  }
  public void setUpR(Node right){
    upR = right;
  }

  public Node getUpL(){
    return upL;
  }
  public Node getUpR(){
    return upR;
  }

  public Node getNext () {
    if(getLeft()==null)
      return getRight();
    else if (getRight() == null)
      return getLeft();
    else if (getRight() == null && getLeft() == null)
      return null;
    
    int r = (int)(Math.random()*2);

    if(r == 0){
      return getLeft();
    } else {
      return getRight();
    }
      
  }
}