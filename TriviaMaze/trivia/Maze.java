package trivia;
public class Maze {
 
   private int row = 0, col = 0, win = 0;	Room[] [] maze;
   
   public Maze(int row, int col)
   {
	   this.row = row;
	   this.col = col;
     this.maze = new  Room[row][col];
   }//end maze
   
   public void printMaze()
   {
      
   }//end printMaze
   
   public void fillMaze()
   {
	   int x = 0;
	   for(; x < this.row - 1; x++)
	   {
		   for(int y = 0; x < this.col; y++)
		   {
			   if(y< this.col -1 )
			   {
				   this.maze[x][y] = new Room(true, true);
			   }
			   else
			   {
				   this.maze[x][y] = new Room(true, false);
			   }
		   }
	   }
	   for(int y = 0; y < this.col -1; y++)
	   {
		   this.maze[x][y] = new Room(false, true);
	   }
   }//end fillMaze
	
   public int getRow()
   {
      return this.row;
   }//end getRow()	
   
   public int getCol()
   {
      return this.col;
   }//end getCol()
   
   public int getWin()
   {
      return this.win;
   }//end getWin()
   
   public void setRow(int row)
   {
      this.row = row;
   }//end setRow()
   
   public void setCol(int col)
   {
      this.col = col;
   }//end setCol
   
   public void setWin(int win)
   {
      this.win = win;
   }//end setWin
   
   public void traverseAndMove()
   {
   //
   }//traverse
   
   
   
}//end class
	
