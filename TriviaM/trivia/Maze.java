package trivia;
import java.util.*;
public class Maze {
 
   private int row = 0, col = 0, win = 0, size;	Room[] [] maze;
   
   public Maze(int size)
   {
	   this.size = size;
	   this.row = 0;
	   this.col = 0;
     this.maze = new  Room[size][size];
   }//end maze
   
   public void printMaze()
   {
      
   }//end printMaze
   
   public void fillMaze()
   {
	   int x = 0;
	   for(; x < this.size - 1; x++)
	   {
		   for(int y = 0; y < this.size; y++)
		   {
			   if(y< this.size -1 )
			   {
				   this.maze[x][y] = new Room(true, true);
			   }
			   else if(y == this.size-1)
			   {
				   this.maze[x][y] = new Room(true, false);
			   }
		   }
	   }
	   int y = 0;
	   for(; y < this.size -1; y++)
	   {
		   this.maze[x][y] = new Room(false, true);
	   }
	   this.maze[x][y] = new Room(false, false);
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
   
   public String move(String dir)
   {
	   Scanner kb = new Scanner(System.in);
	   String answer = "";
	   if(dir.equalsIgnoreCase("right"))
	   {
		   if(this.col< size - 1)
		   {
			   System.out.println(this.maze[this.row][this.col].getRight().getQuestion());
			   answer = kb.nextLine();
			   if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getRight().getAnswer()))
			   {
				   this.maze[this.row][this.col].getRight().setOpen(true);
				   this.col++;
				   kb.close();
				   return "Your new position is " + this.row + ", " + this.col;
			   }
			   else
			   {
				   this.maze[this.row][this.col].getRight().setLocked(true);
				   kb.close();
				   return "Answer is incorrect: your position is " + this.row + ", " + this.col;
			   }

		   }
		   kb.close();
		   return "You cannot move that way";
	   }
	   else if(dir.equalsIgnoreCase("down"))
	   {
		   if(this.row < size - 1)
		   {
			   System.out.println(this.maze[this.row][this.col].getBottom().getQuestion());
			   answer = kb.nextLine();
			   if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getBottom().getAnswer()))
			   {
				   this.maze[this.row][this.col].getBottom().setOpen(true);
				   this.row++;
				   kb.close();
				   return "Your new position is " + this.row + ", " + this.col;
			   }
			   else
			   {
				   this.maze[this.row][this.col].getBottom().setLocked(true);
				   kb.close();
				   return "Answer is incorrect: your position is " + this.row + ", " + this.col;
			   }
		   }
		   kb.close();
		   return "You cannot move that way";
	   }
	   else if(dir.equalsIgnoreCase("up"))
	   {
		   if(this.row > 0)
		   {
			   System.out.println(this.maze[this.row-1][this.col].getBottom().getQuestion());
			   answer = kb.nextLine();
			   if(answer.equalsIgnoreCase(this.maze[this.row-1][this.col].getBottom().getAnswer()))
			   {
				   this.maze[this.row-1][this.col].getBottom().setOpen(true);
				   this.row--;
				   kb.close();
				   return "Your new position is " + this.row + ", " + this.col;
			   }
			   else
			   {
				   this.maze[this.row-1][this.col].getBottom().setLocked(true);
				   kb.close();
				   return "Answer is incorrect: your position is " + this.row + ", " + this.col;
			   }
			   
		   }
		   kb.close();
		   return "You cannot move that way";
	   }
	   else if(dir.equalsIgnoreCase("left"))
	   {
		   if(this.col > 0)
		   {
			   System.out.println(this.maze[this.row][this.col-1].getRight().getQuestion());
			   answer = kb.nextLine();
			   if(answer.equalsIgnoreCase(this.maze[this.row][this.col-1].getRight().getAnswer()))
			   {
				   this.maze[this.row][this.col-1].getRight().setOpen(true);
				   this.col--;
				   kb.close();
				   return "Your new position is " + this.row + ", " + this.col;
			   }
			   else
			   {
				   this.maze[this.row][this.col-1].getRight().setLocked(true);
				   kb.close();
				   return "Answer is incorrect: your position is " + this.row + ", " + this.col;
			   }
			   
		   }
		   kb.close();
		   return "You cannot move that way";
	   }
	   kb.close();
	   return "Should not hit this";
	   
   }//traverse
   
   
   
}//end class
	
