public class maze {
 
   private int row = 0, col = 0, win = 0;	int [] [] maze;
   
   public maze(int row, int col)
   {
     this.maze = new int [row][col];
   }//end maze
   
   public void printMaze()
   {
      
   }//end printMaze
   
   public void fillMaze()
   {
   //
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
	
