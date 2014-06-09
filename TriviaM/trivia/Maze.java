package trivia;
import java.util.*;

public class Maze 
{
 
   private int row = 0, col = 0, win = 0, size;	Room[] [] maze;
   private int skip = 3;
   
   public Maze(int size)
   {
      this.size = size;
      this.row = 0;
      this.col = 0;
      this.maze = new  Room[size][size];
   }//end maze
   
   private boolean valid (int row, int column) //check if the row and col is not out of bound before moving
   {
      boolean result = false;
   
       //check if cell is in the bounds of the matrix
      if (row >= 0 && row < this.size &&
          column >= 0 && column < this.size)
      {
         result = true;
      }
   
      return result;
   }//end valid


   
   
   public boolean traverse (int row, int column)//looks for a path to the end in the maze
   {
      boolean done = false;
      
      if (valid (row, column) )
      {
         
         if (row == this.size -1 && column == this.size-1)
            done = true;  // the maze is solved
         else
         {
            if(this.maze[row][column].hasBdoor() && this.maze[row][column].getBottom().getLocked()==false && this.maze[row][column].getBottom().getTried()==false)
            {
            //System.out.println("down");
               this.maze[row][column].getBottom().setTried("yes");
               done = traverse (row+1, column);     // down  
               this.maze[row][column].getBottom().setTried("no"); 
            
            }
            if (this.maze[row][column].hasRdoor() && !done && this.maze[row][column].getRight().getLocked()==false && this.maze[row][column].getRight().getTried()==false)
            {
            	//System.out.println("right");
               this.maze[row][column].getRight().setTried("yes");
               done = traverse ( row, column+1);  // right
               this.maze[row][column].getRight().setTried("no");                
            }
            
            
            if (valid(row-1, column) && this.maze[row-1][column].hasBdoor() && !done && this.maze[row-1][column].getBottom().getLocked()==false && this.maze[row-1][column].getBottom().getTried()==false)
            { 
            //	System.out.println("up");
               this.maze[row-1][column].getBottom().setTried("yes");
               done = traverse (row-1, column);  // up
               this.maze[row-1][column].getBottom().setTried("no");
            }
            if (valid(row, column-1) && this.maze[row][column-1].hasRdoor() && !done && this.maze[row][column-1].getRight().getLocked()==false && this.maze[row][column-1].getRight().getTried()==false)
            {
            //	System.out.println("left");
               this.maze[row][column-1].getRight().setTried("yes");
               done = traverse ( row, column-1);  // left
               this.maze[row][column-1].getRight().setTried("no");
            }
         }
      }
      
      return done;
   }//end traverse
   

   
   public void printMaze() //prints the layout of the maze
   {
   
      int x = 0, y;
      System.out.print("  ");
      for(y = 0; y < this.size; y++)
      {
         System.out.print(" ***");
      }
      System.out.println();
      for(; x < this.size; x++)
      {
         for(y=0; y < this.size; y++)
         {
            if(y == 0)
            {
               System.out.print(" * ");
            }
            if(x == this.row && y == this.col)
            {
               System.out.print(" P ");
            }
            else
            {
               System.out.print("   ");
            }
            if(this.maze[x][y].hasRdoor() ==false)
            {
               System.out.print(" *");
            }
            else if(this.maze[x][y].getRight().getLocked() ==true && this.maze[x][y].getRight().getOpen() ==false) //door is locked
            {
               System.out.print("#");
            }
            else if(this.maze[x][y].getRight().getLocked() ==false && this.maze[x][y].getRight().getOpen() ==false) //answer question door
            {
               System.out.print("|");
            }
            else if(this.maze[x][y].getRight().getOpen() ==true) //door is open
            {
               System.out.print(":");
            }
          
         	
         }//end for y
         System.out.print("\n");
         int m = 0;
         for (; m < this.size; m++)
         {
            if(m==0)
            {
               System.out.print(" *");
            }
            if(this.maze[x][m].hasBdoor() == false)
            {
               System.out.print(" ***");
            }
            else if(this.maze[x][m].getBottom().getLocked() ==true && this.maze[x][m].getBottom().getOpen() ==false) //door is locked
            {
               System.out.print(" ###");
            }
            else if(this.maze[x][m].getBottom().getLocked() ==false && this.maze[x][m].getBottom().getOpen() ==false) //answer question door
            {
               System.out.print(" ---");
            
            }
            else if(this.maze[x][m].getBottom().getOpen() ==true) //door is open
            {
               System.out.print(" ...");
            }           
            if(m==this.size-1)
            {
               System.out.print(" *");
            }
         	         
         }//end for m
         System.out.println();
      }//end for x
   }//end printMaze
   
   
   
   
   public void fillMaze() //fills the maze with rooms and doors
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


   
   public String checkTrueFalse(String answer)//checks and make sure user enters t or f
   {
      String myAnswer=answer;
      Scanner kb  = new Scanner(System.in);
      while(!(myAnswer.equalsIgnoreCase("t") || myAnswer.equalsIgnoreCase("true") || myAnswer.equalsIgnoreCase("f") || myAnswer.equalsIgnoreCase("false")|| myAnswer.equalsIgnoreCase("skip")))
      {
         System.out.println("Please enter a 't', 'true', 'f' or 'false'");
         myAnswer = kb.nextLine();
          // answer = checkAnswer(answer);
      }
       
      if(answer.equalsIgnoreCase("true"))
      {
         myAnswer = "t";
      }
      if(answer.equalsIgnoreCase("false"))
      {
         myAnswer = "f";
      }
      
     //  kb.close();
      return myAnswer;
   }//end checkTrueFalse
   
   
   public String checkMultiple(String answer)//checks and makes sure user enters a b c or d
   {
      String myAnswer = answer;
      Scanner kb = new Scanner(System.in);
      while(!(myAnswer.equalsIgnoreCase("a") || myAnswer.equalsIgnoreCase("b") || myAnswer.equalsIgnoreCase("c") || myAnswer.equalsIgnoreCase("d")|| myAnswer.equalsIgnoreCase("skip")))
      {
         System.out.println("Please enter 'a', 'b', 'c' or 'd' as your answer");
         myAnswer = kb.nextLine();
           
      }
      return myAnswer;
   }//end checkMultiple
   
   
   public String getCheckAnswer(String theDoor, String answer)//check if the answer is right and move
   {
	   
      if(theDoor.equalsIgnoreCase("right"))
      {
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
         
      }
      else if(theDoor.equalsIgnoreCase("down"))
      {
         if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getBottom().getAnswer()))
         {
            this.maze[this.row][this.col].getBottom().setOpen(true);
            this.maze[this.row][this.col].getBottom().setLocked(false);
            this.maze[this.row][this.col].getTop().setOpen(true);
            this.row++;
            return "Your new position is " + this.row + ", " + this.col;
         }
         else
         {
            this.maze[this.row][this.col].getBottom().setLocked(true);
            this.maze[this.row][this.col].getBottom().setLocked(true);
            return "Answer is incorrect: your position is " + this.row + ", " + this.col;
         }
         
      }
      else if(theDoor.equalsIgnoreCase("up"))
      {
         if(answer.equalsIgnoreCase(this.maze[this.row-1][this.col].getTop().getAnswer()))
         {
            this.maze[this.row-1][this.col].getBottom().setOpen(true);
            this.maze[this.row-1][this.col].getBottom().setLocked(false);              
            this.row--;
            return "Your new position is " + this.row + ", " + this.col;
         }
         else
         {
            this.maze[this.row-1][this.col].getBottom().setLocked(true);
            this.maze[this.row-1][this.col].getBottom().setLocked(true);
            return "Answer is incorrect: your position is " + this.row + ", " + this.col;
         }
         
      }
      else if(theDoor.equalsIgnoreCase("left"))
      {
         if(answer.equalsIgnoreCase(this.maze[this.row][this.col-1].getLeft().getAnswer()))
         {
            this.maze[this.row][this.col-1].getRight().setOpen(true);
            this.maze[this.row][this.col-1].getRight().setLocked(false);              
            this.col--;
            return "Your new position is " + this.row + ", " + this.col;
         }
         else
         {
            this.maze[this.row][this.col-1].getRight().setLocked(true);
            this.maze[this.row][this.col-1].getRight().setLocked(true);
            return "Answer is incorrect: your position is " + this.row + ", " + this.col;
         }
         
      }
      return "should not get here";
      
   }//end getCheckAnswer
   
   
   
   public String checkLockedOrWall(Door door, String position) //checks and make sure door is not locked or hit a wall
   {
      if(position.equalsIgnoreCase("down"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.row++;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }
      }
      else if(position.equalsIgnoreCase("right"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.col++;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }
      }
      else if(position.equalsIgnoreCase("left"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.col--;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }
      }
      else if(position.equalsIgnoreCase("up"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.row--;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }
      }
   
      return "should not be here";
   }//end checkLockedOrWall
   
   
   
   
   public String move(String dir)//moving around the maze if it is valid
   {
      Scanner kb = new Scanner(System.in);
           String answer = "";
      int questionType = 0;
      String copyAnswer;
      if(dir.equalsIgnoreCase("right"))
      {
         if(this.col< size - 1)
         {
            Door door = this.maze[this.row][this.col].getRight();
            
            if(door.getLocked()==false && door.getOpen()==false)
            {
               this.maze[this.row][this.col].getRight().getQuestion();
               System.out.println(door.getThisQuestion());
              System.out.println("You may enter skip to skip this question.");
               questionType = door.getType();
               answer = kb.nextLine();
               copyAnswer = answer;     
              
               
               
               if(questionType == 0) //if it is a true/false question
               {
            	   door.setTFquestion();//set question as being used
                  answer = checkTrueFalse(copyAnswer); //check if answer is valid like t or f
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }
               }
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  }
               }
               
             
               return getCheckAnswer("right",answer); //check if the answer is correct or not
               
            }//end if of getLocked() and getOpened()
            
            return checkLockedOrWall(door,"right");//if can't move then check if door is locked or wall
         
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
               this.maze[this.row][this.col].getBottom().getQuestion();
              System.out.println(door.getThisQuestion());
              System.out.println("You may enter skip to skip this question.");
               questionType = door.getType();
               answer = kb.nextLine();
               copyAnswer = answer;
               

               
               if(questionType == 0) //if it is a true/false question
               {
            	   door.setTFquestion();//set question as being used
                  answer = checkTrueFalse(copyAnswer); //check if answer is valid like t or f
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }
               }
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  }
               }
               
             
               return getCheckAnswer("down",answer); //check if the answer is correct or not
            }//end locked =false and open = false
            
            return checkLockedOrWall(door,"down");//if can't move then check if door is locked or wall
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
            
               this.maze[this.row-1][this.col].getBottom().getQuestion();
               System.out.println(door.getThisQuestion());
               System.out.println("You may enter skip to skip this question.");
               //need to get questionType
               questionType = door.getType();
               answer = kb.nextLine();
               copyAnswer = answer;
               

               
               if(questionType == 0) //if it is a true/false question
               {
            	   door.setTFquestion();//set question as being used
                  answer = checkTrueFalse(copyAnswer); //check if answer is valid like t or f
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }
               }
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  }
               }
               
             
             
               return getCheckAnswer("up",answer); //check if the answer is correct or not
            }//end lock is false and open is false
            
            return checkLockedOrWall(door,"up");//if can't move then check if door is locked or wall
         
            
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
            
              this.maze[this.row][this.col-1].getRight().getQuestion();
               System.out.println(door.getThisQuestion());
               System.out.println("You may enter skip to skip this question.");
               questionType = door.getType();
               answer = kb.nextLine();
               copyAnswer = answer;
               

               
               if(questionType == 0) //if it is a true/false question
               {
            	   door.setTFquestion();//set question as being used
                  answer = checkTrueFalse(copyAnswer); //check if answer is valid like t or f
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }
               }
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  }
               }
               
             
             
               return getCheckAnswer("left",answer); //check if the answer is correct or not
            }//end lock is false and open is false
            
            return checkLockedOrWall(door,"left");//if can't move then check if door is locked or wall
            
         }
        
         return "You cannot move that way";
      }
      
      return "Invalid input!";
      
   }//traverse
   
   
   
}//end class
	
