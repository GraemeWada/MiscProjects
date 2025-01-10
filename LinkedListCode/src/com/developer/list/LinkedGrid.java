package com.developer.list;

import java.util.Random;

public class LinkedGrid {
	private SquareNode first;
	
	public LinkedGrid (int dim) {
		Random r = new Random();
	
		if (dim > 0) {
		
			first = new SquareNode(r.nextInt(6)+1);
			SquareNode colMarker = first;
			SquareNode rowMarker = first;
			
			for (int i = 0; i < dim - 1; i++) {
				colMarker.setRight(new SquareNode(r.nextInt(6)+1));
				colMarker.getRight().setLeft(colMarker);
				colMarker = colMarker.getRight();
			}
			
			for (int i = 0; i < dim - 1; i++) {
				rowMarker.setDown(new SquareNode(r.nextInt(6)+1));
				rowMarker.getDown().setUp(rowMarker);
				rowMarker = rowMarker.getDown();
				
				colMarker = rowMarker;
				
				for (int w = 0; w < dim - 1; w++) {
					colMarker.setRight(new SquareNode(r.nextInt(6)+1));
					colMarker.getRight().setLeft(colMarker);
					colMarker.getRight().setUp(colMarker.getUp().getRight());
					colMarker.getRight().getUp().setDown(colMarker.getRight());
					colMarker = colMarker.getRight();
				}
			}
		}
	}
	
	public LinkedGrid (int dimX, int dimY) {
		Random r = new Random();
	
		if (dimX > 0 && dimY > 0) {
		
			first = new SquareNode(r.nextInt(6)+1);
			SquareNode colMarker = first;
			SquareNode rowMarker = first;
			
			for (int i = 0; i < dimX - 1; i++) {
				colMarker.setRight(new SquareNode(r.nextInt(6)+1));
				colMarker.getRight().setLeft(colMarker);
				colMarker = colMarker.getRight();
			}
			
			for (int i = 0; i < dimY - 1; i++) {
				rowMarker.setDown(new SquareNode(r.nextInt(6)+1));
				rowMarker.getDown().setUp(rowMarker);
				rowMarker = rowMarker.getDown();
				
				colMarker = rowMarker;
				
				for (int w = 0; w < dimX - 1; w++) {
					colMarker.setRight(new SquareNode(r.nextInt(6)+1));
					colMarker.getRight().setLeft(colMarker);
					colMarker.getRight().setUp(colMarker.getUp().getRight());
					colMarker.getRight().getUp().setDown(colMarker.getRight());
					colMarker = colMarker.getRight();
				}
			}
		}
	}
	
	private String set(int n) {
//		StringBuilder s = new StringBuilder(100000);
		
//		String RED = "\u001B[41m  \u001B[0m";
//		String BLUE = "\u001B[44m  \u001B[0m";
//		String GREEN = "\u001B[42m  \u001B[0m";
//		String YELLOW = "\u001B[43m  \u001B[0m";
//		String MAGENTA = "\u001B[45m  \u001B[0m";
		String[] c = {"  ","\u001B[41m  \u001B[0m", "\u001B[44m  \u001B[0m","\u001B[42m  \u001B[0m","\u001B[43m  \u001B[0m","\u001B[45m  \u001B[0m"};
//		String[] w = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 "};
//		String[] p = {"O ", "X ", "+ ", "[ ", "@ ", "# "};
//		String[] z = {":) ", ":[ ", "=D ", "=] ", ":P ", "=/ "};
//		String[] j = {"O ", "X ", "# ", "^ ", ": ", "_ "};
		
				return c[n-1];
				
//			return s.toString();s
	}
	
	public void display() {
		//StringBuilder sb = new StringBuilder(5000000);
		
		SquareNode temp = first;
		SquareNode rowMarker = first;
		
//		String RED = "\u001B[41m";
//		String BLUE = "\u001B[44m";
//		String GREEN = "\u001B[42m";
//		String YELLOW = "\u001B[43m";
//		String MAGENTA = "\u001B[45m";
//		String RESET = " \u001B[0m";
		
		while (rowMarker != null) {
			while (temp != null) {
//				//String p = "";
//				String i = Integer.toString(temp.getData());
//				switch (temp.getData()) {
//				case 1:
//					break;
//				case 2:
//					sb.append(RED);
//					//p = RED+i+" "+RESET;
//					break;
//				case 3:
//					sb.append(BLUE);
//					//p = BLUE+i+" "+RESET;
//					break;
//				case 4:
//					sb.append(GREEN);
//					//p = GREEN+i+" "+RESET;
//					break;
//				case 5:
//					sb.append(YELLOW);
//					//p = YELLOW+i+" "+RESET;
//					break;
//				case 6:
//					sb.append(MAGENTA);
//					//p = MAGENTA+i+" "+RESET;
//				}
//				sb.append(i);
//				sb.append(RESET);
				System.out.print(set(temp.getData()));
				temp = temp.getRight();
			}
			//sb.append("\n");
			System.out.println();
			temp = rowMarker.getDown();
			rowMarker = temp;
		}
		//System.out.print(sb.toString());
		System.out.println("\n1 \u001B[41m2 \u001B[44m3 \u001B[42m4 \u001B[43m5 \u001B[45m6 \u001B[0m");
//		System.out.println("1 -> O     4 -> ^\n2 -> X     5 -> :\n3 -> #     6 -> _");
	}
	
	public boolean checkFlood() {
		SquareNode temp = first;
		SquareNode rowMarker = first;
		int floodNum = first.getData();
		
		while (rowMarker != null) {
			while (temp != null) {
				if(temp.getData() != floodNum) {
					return false;
				}
				temp = temp.getRight();
			}
			temp = rowMarker.getDown();
			rowMarker = temp;
		}
		return true;
	}
	
	public void search(int n) {
		int floodColour = first.getData();
		first.setData(n);
		if(first.getRight()!=null&&first.getRight().getData()==floodColour) {
			search(n, first.getRight(), floodColour);
		}
		if(first.getDown()!=null&&first.getDown().getData()==floodColour) {
			search(n, first.getDown(), floodColour);
		}
	}
	
	public void search(int n, SquareNode s, int fc) {
		s.setData(n);
		if(s.getRight()!=null&&s.getRight().getData()==fc) {
			search(n, s.getRight(), fc);
		}
		if(s.getDown()!=null&&s.getDown().getData()==fc) {
			search(n, s.getDown(), fc);
		}
		if(s.getLeft()!=null && s.getLeft().getData()==fc) {
			search(n, s.getLeft(), fc);
		}
		if(s.getUp()!=null&&s.getUp().getData()==fc) {
			search(n, s.getUp(), fc);
		}
	}
}
