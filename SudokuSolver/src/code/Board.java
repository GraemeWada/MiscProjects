package code;

public class Board {
	
	public static Cell [][] board  = new Cell[9][9];
	
	public static void setBoxID(){
		//set the boxID's for all cells.
	}
	
	public static void solve(int x, int y, int number)
	{
		/*set the cell's number to be number
			
		set all other cells in the same column so that they
		cannot be that number (set possible[number] to be false)
			
		set all other cells in the same row so that they
			cannot be that number (set possible[number] to be false)
			
		set all other cells in the same box so that they
			cannot be that number (set possible[number] to be false)
		*/
	}
	
	public static void loadPuzzle(String filename)
	{
		//loads the puzzle from the file with the given file name.
	}
	
	public static void display(){
		//show the puzzle
		
	}
		
		
	
	
	public static void main(String[] args){
		
		for(int x = 0; x < 9; x++)
			for(int y = 0; y < 9; y++)
				board[x][y] = new Cell();
		
		System.out.println( board[0][0].getNumber() );
		
		
		
		
		
		
		
		
	}
	

}