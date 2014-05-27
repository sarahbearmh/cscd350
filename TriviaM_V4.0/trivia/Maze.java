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
      int x = 0, y;
      for(; x < this.size; x++)
      {
         for(y=0; y < this.size; y++)
         {
        	 if(x == this.row && y == this.col)
        	 {
        		 System.out.print("P");
        	 }
            if(this.maze[x][y].hasRdoor() ==false)
            {
               System.out.print("  *");
            }
            else if(this.maze[x][y].getRight().getLocked() ==true && this.maze[x][y].getRight().getOpen() ==false) //door is locked
            {
               System.out.print("  #");
            }
            else if(this.maze[x][y].getRight().getLocked() ==false && this.maze[x][y].getRight().getOpen() ==false) //answer question door
            {
               System.out.print("  |");
            }
            else if(this.maze[x][y].getRight().getOpen() ==true) //door is open
            {
               System.out.print("  :");
            }
         	
         }//end for y
         System.out.print("\n");
         int m = 0;
         for (; m < this.size; m++)
         {
           
            if(this.maze[x][m].hasBdoor() == false)
            {
               System.out.print(" **");
            }
            else if(this.maze[x][m].getBottom().getLocked() ==true && this.maze[x][m].getBottom().getOpen() ==false) //door is locked
            {
               System.out.print("##");
            }
            else if(this.maze[x][m].getBottom().getLocked() ==false && this.maze[x][m].getBottom().getOpen() ==false) //answer question door
            {
               System.out.print(" --");
            
            }
            else if(this.maze[x][m].getBottom().getOpen() ==true) //door is open
            {
               System.out.print(" ..");
            }
         	         
         }//end for m
         System.out.println();
      }//end for x
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
         System.out.println();
      }
      int y = 0;
      for(; y < this.size -1; y++)
      {
         this.maze[x][y] = new Room(false, true);//bottom, right
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
            Door door = this.maze[this.row][this.col].getRight();
            
            if(door.getLocked()==false && door.getOpen()==false)
            {
               System.out.println(this.maze[this.row][this.col].getRight().getQuestion());
               answer = kb.nextLine();
               if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getRight().getAnswer()))
               {
                  this.maze[this.row][this.col].getRight().setOpen(true);
                   this.maze[this.row][this.col].getRight().setLocked(false);
                  this.maze[this.row][this.col].getLeft().setOpen(true);
                  this.col++;
                  return "Your new position is " + this.row + ", " + this.col;
               }
               else
               {
                  this.maze[this.row][this.col].getRight().setLocked(true);
                  this.maze[this.row][this.col].getLeft().setLocked(true);
                  return "Answer is incorrect: your position is " + this.row + ", " + this.col;
               }
            }//end if of getLocked() and getOpened()
            else if((door.getLocked()==false && door.getOpen()==true))
            {
               this.col++;
               return "Your new position for open door is " + this.row + ", " + this.col;
            }
            else if((door.getLocked()==true && door.getOpen()==false))
            {
               return "This door is locked, cannot move that way";
            }
         
         }
         
         return "You cannot move that way";
      }
      else if(dir.equalsIgnoreCase("down"))
      {
         
         if(this.row < size - 1)
         {
            Door door = this.maze[this.row][this.col].getBottom();
            if(door.getLocked()==false && door.getOpen()==false)
            {
               System.out.println(this.maze[this.row][this.col].getBottom().getQuestion());
               answer = kb.nextLine();
               if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getBottom().getAnswer()))
               {
                  this.maze[this.row][this.col].getBottom().setOpen(true);
                  this.maze[this.row][this.col].getTop().setOpen(true);
                  this.row++;
                  return "Your new position is " + this.row + ", " + this.col;
               }
               else
               {
                  this.maze[this.row][this.col].getBottom().setLocked(true);
                  this.maze[this.row][this.col].getTop().setLocked(true);
               
                  return "Answer is incorrect: your position is " + this.row + ", " + this.col;
               }
            }//end locked =false and open = false
            else if((door.getLocked()==false && door.getOpen()==true))
            {
               this.row++;
               return "Your new position for open door is " + this.row + ", " + this.col;
            }
            else if((door.getLocked()==true && door.getOpen()==false))
            {
               return "This door is locked, cannot move that way";
            }
         }
        
         return "You cannot move that way";
      }
      else if(dir.equalsIgnoreCase("up"))
      {
         if(this.row > 0)
         {
            Door door = this.maze[this.row-1][this.col].getBottom();
            
            if(door.getLocked()==false && door.getOpen()==false)
            {
            
               System.out.println(this.maze[this.row-1][this.col].getBottom().getQuestion());
               answer = kb.nextLine();
               if(answer.equalsIgnoreCase(this.maze[this.row-1][this.col].getBottom().getAnswer()))
               {
                  this.maze[this.row-1][this.col].getBottom().setOpen(true);
                  this.row--;
                  return "Your new position is " + this.row + ", " + this.col;
               }
               else
               {
                  this.maze[this.row-1][this.col].getBottom().setLocked(true);
                  return "Answer is incorrect: your position is " + this.row + ", " + this.col;
               }
            }//end lock is false and open is false
            else if((door.getLocked()==false && door.getOpen()==true))
            {
               this.row--;
               return "Your new position for open door is " + this.row + ", " + this.col;
            }
            else if((door.getLocked()==true && door.getOpen()==false))
            {
               return "This door is locked, cannot move that way";
            }
         
            
         }
        
         return "You cannot move that way";
      }
      else if(dir.equalsIgnoreCase("left"))
      {
         if(this.col > 0)
         {
            Door door = this.maze[this.row][this.col-1].getRight();
            if(door.getLocked()==false && door.getOpen()==false)
            {
            
               System.out.println(this.maze[this.row][this.col-1].getRight().getQuestion());
               answer = kb.nextLine();
               if(answer.equalsIgnoreCase(this.maze[this.row][this.col-1].getRight().getAnswer()))
               {
                  this.maze[this.row][this.col-1].getRight().setOpen(true);
                  this.col--;
                  return "Your new position is " + this.row + ", " + this.col;
               }
               else
               {
                  this.maze[this.row][this.col-1].getRight().setLocked(true);
                  return "Answer is incorrect: your position is " + this.row + ", " + this.col;
               }
            }//end lock is false and open is false
            else if((door.getLocked()==false && door.getOpen()==true))
            {
               this.col--;
               return "Your new position for open door is " + this.row + ", " + this.col;
            }
            else if((door.getLocked()==true && door.getOpen()==false))
            {
               return "This door is locked, cannot move that way";
            }
         
            
         }
        
         return "You cannot move that way";
      }
      
      return "Should not hit this";
      
   }//traverse
   
   
   
}//end class
	
