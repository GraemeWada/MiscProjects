package tour;

public class LinkedGrid {
	private SNode root;
	private int solutions = 0;
	private int size;
	
	public LinkedGrid (int dimX, int dimY) {
		size = dimX*dimY;
		root = new SNode();
		
		SNode colMark = root;
		SNode rowMark = root;
		
		if (dimX > 0 && dimY > 0) {
			
			for (int i = 1; i < dimX; i++) {
				colMark.setRight(new SNode());
				colMark.getRight().setLeft(colMark);
				colMark = colMark.getRight();
			}
			
			for (int i = 1; i < dimY; i++) {
				rowMark.setDown(new SNode());
				rowMark.getDown().setUp(rowMark);
				rowMark = rowMark.getDown();
				colMark = rowMark;
				for (int k = 1; k < dimX; k++) {
					colMark.setRight(new SNode());
					colMark.getRight().setLeft(colMark);
					colMark.getRight().setUp(colMark.getUp().getRight());
					colMark.getUp().getRight().setDown(colMark.getRight());
					colMark = colMark.getRight();
				}
			}
		}
		
		
		//set knight move links
		SNode rm = root;
		while(rm != null) {
			SNode temp = rm;
			while(temp != null) {
				//try catches
				try {
					temp.setUUL(temp.getUp().getUp().getLeft());
					if(temp.getUUL() != null ) {temp.incrementFreedom();}
//					System.out.println("1");
				} catch (NullPointerException e) {}
				try {
					temp.setUUR(temp.getUp().getUp().getRight());
					if(temp.getUUR() != null ) {temp.incrementFreedom();}
//					System.out.println("2");
				} catch (NullPointerException e) {}
				try {
					temp.setRRU(temp.getRight().getRight().getUp());
					if(temp.getRRU() != null ) {temp.incrementFreedom();}
//					System.out.println("3");
				} catch (NullPointerException e) {}
				try {
					temp.setRRD(temp.getRight().getRight().getDown());
					if(temp.getRRD() != null ) {temp.incrementFreedom();}
//					System.out.println("4");
				} catch (NullPointerException e) {}
				try {
					temp.setDDR(temp.getDown().getDown().getRight());
					if(temp.getDDR() != null ) {temp.incrementFreedom();}
//					System.out.println("5");
				} catch (NullPointerException e) {}
				try {
					temp.setDDL(temp.getDown().getDown().getLeft());
					if(temp.getDDL() != null ) {temp.incrementFreedom();}
//					System.out.println("6");
				} catch (NullPointerException e) {}
				try {
					temp.setLLD(temp.getLeft().getLeft().getDown());
					if(temp.getLLD() != null ) {temp.incrementFreedom();}
//					System.out.println("7");
				} catch (NullPointerException e) {}
				try {
					temp.setLLU(temp.getLeft().getLeft().getUp());
					if(temp.getLLU() != null ) {temp.incrementFreedom();}
//					System.out.println("8");
				} catch (NullPointerException e) {}
				
				
				
				temp = temp.getRight();
			}
			rm = rm.getDown();
		}
	}
	
	public void display() {
		SNode rowMark = root;
		while(rowMark != null) {
			SNode temp = rowMark;
			while(temp != null) {
				String w = (temp.getData() > 9) ? " " : "  ";
				System.out.print(temp.getData() + w);
				temp = temp.getRight();
			}
			System.out.println();
			rowMark = rowMark.getDown();
		}
		System.out.println();
		
		//print freedoms
//		rowMark = root;
//		while(rowMark != null) {
//			SNode temp = rowMark;
//			while(temp != null) {
//				System.out.print(temp.getFreedom()+" ");
//				temp = temp.getRight();
//			}
//			System.out.println();
//			rowMark = rowMark.getDown();
//		}System.out.println();
	}
	
	public SNode getRoot() {
		return root;
	}
	
	public void tour(SNode node, int num) {
		boolean skip = false;
		int w = 0;
		SNode zeroFreedom=null;
		node.setData(num);
		
		
		if(num == size) {
			System.out.println("Solution "+(++solutions)+":");
			display();
		}
		
		
		SNode uul = node.getUUL();
		SNode uur = node.getUUR();
		SNode rru = node.getRRU();
		SNode rrd = node.getRRD();
		SNode ddr = node.getDDR();
		SNode ddl = node.getDDL();
		SNode lld = node.getLLD();
		SNode llu = node.getLLU();
		
//		if(uul!= null && uul.decrFreedom() == 0 && uul.getData() == 0) {w++;zeroFreedom=uul;}
//		if(uur!= null && uur.decrFreedom() == 0 && uur.getData() == 0) {w++;zeroFreedom=uur;}
//		if(rru!= null && rru.decrFreedom() == 0 && rru.getData() == 0) {w++;zeroFreedom=rru;}
//		if(rrd!= null && rrd.decrFreedom() == 0 && rrd.getData() == 0) {w++;zeroFreedom=rrd;}
//		if(ddr!= null && ddr.decrFreedom() == 0 && ddr.getData() == 0) {w++;zeroFreedom=ddr;}
//		if(ddl!= null && ddl.decrFreedom() == 0 && ddl.getData() == 0) {w++;zeroFreedom=ddl;}
//		if(lld!= null && lld.decrFreedom() == 0 && lld.getData() == 0) {w++;zeroFreedom=lld;}
//		if(llu!= null && llu.decrFreedom() == 0 && llu.getData() == 0) {w++;zeroFreedom=llu;}
		
		if(uul!= null && uul.decrFreedom()) {w++;zeroFreedom=uul;}
		if(uur!= null && uur.decrFreedom()) {w++;zeroFreedom=uur;}
		if(rru!= null && rru.decrFreedom()) {w++;zeroFreedom=rru;}
		if(rrd!= null && rrd.decrFreedom()) {w++;zeroFreedom=rrd;}
		if(ddr!= null && ddr.decrFreedom()) {w++;zeroFreedom=ddr;}
		if(ddl!= null && ddl.decrFreedom()) {w++;zeroFreedom=ddl;}
		if(lld!= null && lld.decrFreedom()) {w++;zeroFreedom=lld;}
		if(llu!= null && llu.decrFreedom()) {w++;zeroFreedom=llu;}
		
//		display();
		
		if(w>1) {
			skip=true;
		}
		
		if(!skip) {
			if(w==0) {
				if(uul != null && uul.getData() == 0) {tour(uul, num+1);}
				if(uur != null && uur.getData() == 0) {tour(uur, num+1);}
				if(rru != null && rru.getData() == 0) {tour(rru, num+1);}
				if(rrd != null && rrd.getData() == 0) {tour(rrd, num+1);}
				if(ddr != null && ddr.getData() == 0) {tour(ddr, num+1);}
				if(ddl != null && ddl.getData() == 0) {tour(ddl, num+1);}
				if(lld != null && lld.getData() == 0) {tour(lld, num+1);}
				if(llu != null && llu.getData() == 0) {tour(llu, num+1);}
			} else {
				tour(zeroFreedom, num+1);
			}
		}
		
		
		
		//backtrack
		node.setData(0);
//		System.out.println(num);
		
		try{ uul.incrementFreedom(); } catch (NullPointerException e) {}
		try{ uur.incrementFreedom(); } catch (NullPointerException e) {}
		try{ rru.incrementFreedom(); } catch (NullPointerException e) {}
		try{ rrd.incrementFreedom(); } catch (NullPointerException e) {}
		try{ ddr.incrementFreedom(); } catch (NullPointerException e) {}
		try{ ddl.incrementFreedom(); } catch (NullPointerException e) {}
		try{ lld.incrementFreedom(); } catch (NullPointerException e) {}
		try{ llu.incrementFreedom(); } catch (NullPointerException e) {}
	}
	
	public int getNumSlns() {
		return solutions;
	}
}
