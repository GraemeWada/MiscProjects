package tour;

public class SNode {
	private SNode up, down, left, right;
	private int data;
	private int freedom;
	private SNode UUL, UUR, RRU, RRD, DDR, DDL, LLD, LLU;
	
	//  p1  p2
	//p8      p3
	//	  KN
	//p7      p4
	//  p6  p5
	
	public SNode getUUL() {
		return UUL;
	}

	public void setUUL(SNode uUL) {
		UUL = uUL;
	}

	public SNode getUUR() {
		return UUR;
	}

	public void setUUR(SNode uUR) {
		UUR = uUR;
	}

	public SNode getRRU() {
		return RRU;
	}

	public void setRRU(SNode rRU) {
		RRU = rRU;
	}

	public SNode getRRD() {
		return RRD;
	}

	public void setRRD(SNode rRD) {
		RRD = rRD;
	}

	public SNode getDDR() {
		return DDR;
	}

	public void setDDR(SNode dDR) {
		DDR = dDR;
	}

	public SNode getDDL() {
		return DDL;
	}

	public void setDDL(SNode dDL) {
		DDL = dDL;
	}

	public SNode getLLD() {
		return LLD;
	}

	public void setLLD(SNode lLD) {
		LLD = lLD;
	}

	public SNode getLLU() {
		return LLU;
	}

	public void setLLU(SNode lLU) {
		LLU = lLU;
	}
	
	public SNode () {
		data = 0;
	}

	public int getFreedom() {
		return freedom;
	}

	public void incrementFreedom() {
		freedom++;
	}
	public boolean decrFreedom() {
		freedom--;
//		return freedom;
		return (freedom+data==0) ? true : false;
	}

	public SNode getUp() {
		return up;
	}

	public void setUp(SNode up) {
		this.up = up;
	}

	public SNode getDown() {
		return down;
	}

	public void setDown(SNode down) {
		this.down = down;
	}

	public SNode getRight() {
		return right;
	}

	public void setRight(SNode right) {
		this.right = right;
	}

	public SNode getLeft() {
		return left;
	}

	public void setLeft(SNode left) {
		this.left = left;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}
}
