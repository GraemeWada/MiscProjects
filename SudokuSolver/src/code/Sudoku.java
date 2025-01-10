package code;

import java.io.File;
import java.util.Scanner;

public class Sudoku {
	
	public static Cell [][] board  = new Cell[9][9];
	
	public static void setBoxID(){
		//set the boxID's for all cells.
	}
	
	public static void solve(int x, int y, int number)
	{
		board[x][y].setNumber(number);
		/*set the cell's number to be number
		 * 
		 set all the possibles for that cell to be false
		 except for number
			
		set all other cells in the same column so that they
		cannot be that number (set possible[number] to be false)
			
		set all other cells in the same row so that they
			cannot be that number (set possible[number] to be false)
			
		set all other cells in the same box so that they
			cannot be that number (set possible[number] to be false)
		*/
	}
	
	public static void loadPuzzle(String filename) throws Exception
	{
		Scanner input = new Scanner( new File(filename) );
		
		for(int x = 0; x < 9; x++)
			for(int y = 0; y< 9; y++)
				solve(x,y,input.nextInt() );
		
		//loads the puzzle from the file with the given file name.
	}
	
	public static void display(){
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++)
				System.out.print(board[x][y]+" ");
			System.out.println();
		}
	}
		
	public static void main(String[] args)throws Exception{
			
		for(int x = 0; x < 9; x++)
			for(int y = 0; y< 9; y++)
				board[x][y] = new Cell();
		
		loadPuzzle("src/code/easy.txt");	
		
		System.out.println( "FIRST CELL: " + board[0][0].getNumber() );
			
	}
	

}