package com.developer.list;

public class SquareNode {
	private int data;
	private SquareNode up;
	private SquareNode down;
	private SquareNode left;
	private SquareNode right;
	
	public SquareNode(int data) {
		this.data = data;
	}
	
	public SquareNode() {
		data = -1;
		up = null;
		down = null;
		left = null;
		right = null;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public SquareNode getUp() {
		return up;
	}
	public void setUp(SquareNode up) {
		this.up = up;
	}
	public SquareNode getDown() {
		return down;
	}
	public void setDown(SquareNode down) {
		this.down = down;
	}
	public SquareNode getLeft() {
		return left;
	}
	public void setLeft(SquareNode left) {
		this.left = left;
	}
	public SquareNode getRight() {
		return right;
	}
	public void setRight(SquareNode right) {
		this.right = right;
	}
	
	
	
}
