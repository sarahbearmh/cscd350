package trivia;
import java.util.*;

public class Maze 
{
 
   private int row = 0, col = 0, size;	Room[] [] maze;
   private int skip = 3;
   
   public Maze(int size)
   {
      this.size = size;
      this.row = 0;
      this.col = 0;
      this.maze = new  Room[size][size];
   }//end maze
   
   //
 //check if the row and col is not out of bound before moving
   private boolean valid (int row, int column) 
   {
      boolean result = false;
   
       //check if cell is in the bounds of the matrix
      if (row >= 0 && row < this.size &&
          column >= 0 && column < this.size)
      {
         result = true;
      }//end if (row >= 0 && row < this.size && column >= 0 && column < this.size)
   
      return result;
   }//end valid


   
 //
 //looks for a path to the end in the maze
   public boolean traverse (int row, int column)
   {
      boolean done = false;
      
      if (valid (row, column) )
      {
         
         if (row == this.size -1 && column == this.size-1)
            done = true; 
         else
         {
            if(this.maze[row][column].hasBdoor() && this.maze[row][column].getBottom().getLocked()==false && this.maze[row][column].getBottom().getTried()==false)
            {
          
               this.maze[row][column].getBottom().setTried("yes");
               done = traverse (row+1, column);     // down  
               this.maze[row][column].getBottom().setTried("no"); 
            
            }//end if(this.maze[row][column].hasBdoor().....
            if (this.maze[row][column].hasRdoor() && !done && this.maze[row][column].getRight().getLocked()==false && this.maze[row][column].getRight().getTried()==false)
            {
            	
               this.maze[row][column].getRight().setTried("yes");
               done = traverse ( row, column+1);  // right
               this.maze[row][column].getRight().setTried("no");                
            }//end if (this.maze[row][column].hasRdoor()             
            
            if (valid(row-1, column) && this.maze[row-1][column].hasBdoor() && !done && this.maze[row-1][column].getBottom().getLocked()==false && this.maze[row-1][column].getBottom().getTried()==false)
            { 
           
               this.maze[row-1][column].getBottom().setTried("yes");
               done = traverse (row-1, column);  // up
               this.maze[row-1][column].getBottom().setTried("no");
            }//end  if (valid(row-1, column) && this.maze[row-1][column].hasBdoor() 
            if (valid(row, column-1) && this.maze[row][column-1].hasRdoor() && !done && this.maze[row][column-1].getRight().getLocked()==false && this.maze[row][column-1].getRight().getTried()==false)
            {
               this.maze[row][column-1].getRight().setTried("yes");
               done = traverse ( row, column-1);  // left
               this.maze[row][column-1].getRight().setTried("no");
            }//end  if (valid(row, column-1) && this.maze[row][column-1].hasRdoor() &
         }//end big else
      }//end if (valid (row, column) )
      
      return done;
   }//end traverse
   

   //
 //prints the layout of the maze
   public void printMaze() 
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
   
   
   //
 //fills the maze with rooms and doors   
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
   
   
   //
   //returns the current row player is at
   public int getRow()
   {
      return this.row;
   }//end getRow()	
   
   
   //
   //returns the current column player is at
   public int getCol()
   {
      return this.col;
   }//end getCol()
   
   
   //
   //change current row user is at
   public void setRow(int row)
   {
      this.row = row;
   }//end setRow()
   
   
   //
   //change current row user is at
   public void setCol(int col)
   {
      this.col = col;
   }//end setCol


   //
 //checks and make sure user enters t or f or other cheats
   public String checkTrueFalse(String answer)
   {
      String myAnswer=answer;
      Scanner kb  = new Scanner(System.in);
      while(!(myAnswer.equalsIgnoreCase("t") || myAnswer.equalsIgnoreCase("true") || myAnswer.equalsIgnoreCase("f") || myAnswer.equalsIgnoreCase("false")|| myAnswer.equalsIgnoreCase("skip")||myAnswer.equalsIgnoreCase("pass")||myAnswer.equalsIgnoreCase("quit")))
      {
         System.out.println("Please enter a 't', 'true', 'f' or 'false'");
         myAnswer = kb.nextLine();
          // answer = checkAnswer(answer);
      }// end  while(!(myAns
       
      if(answer.equalsIgnoreCase("true"))
      {
         myAnswer = "t";
      }//end  if(answer.equalsIgnoreCase("true"))
      if(answer.equalsIgnoreCase("false"))
      {
         myAnswer = "f";
      }//end  if(answer.equalsIgnoreCase("false"))
      if(myAnswer.equalsIgnoreCase("quit"))
      {
         System.exit(0);
      }//end   if(myAnswer.equalsIgnoreCase("quit"))
    
      return myAnswer;
   }//end checkTrueFalse
   
   
   //
 //checks and makes sure user enters a b c or d
   public String checkMultiple(String answer)
   {
      String myAnswer = answer;
      Scanner kb = new Scanner(System.in);
      while(!(myAnswer.equalsIgnoreCase("a") || myAnswer.equalsIgnoreCase("b") || myAnswer.equalsIgnoreCase("c") || myAnswer.equalsIgnoreCase("d")|| myAnswer.equalsIgnoreCase("skip") ||myAnswer.equalsIgnoreCase("pass")||myAnswer.equalsIgnoreCase("quit")))
      {
         System.out.println("Please enter 'a', 'b', 'c' or 'd' as your answer");
         myAnswer = kb.nextLine();
           
      }//end   while(!
      if(myAnswer.equalsIgnoreCase("quit"))
      {
         System.exit(0);
      }//end  if(myAnswer.equalsIgnoreCase("quit"))
      return myAnswer;
   }//end checkMultiple
   
   
   //
 //check if the answer is right and move
   public String getCheckAnswer(String theDoor, String answer)
   {
	   
      if(theDoor.equalsIgnoreCase("right"))
      {
         if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getRight().getAnswer()) || answer.equalsIgnoreCase("pass"))
         {
            this.maze[this.row][this.col].getRight().setOpen(true);
            this.maze[this.row][this.col].getRight().setLocked(false);
            this.col++;
            return "Your new position is " + this.row + ", " + this.col;
         }
         else
         {
            this.maze[this.row][this.col].getRight().setLocked(true);
            return "Answer is incorrect: your position is " + this.row + ", " + this.col;
         }
         
      }
      else if(theDoor.equalsIgnoreCase("down"))
      {
         if(answer.equalsIgnoreCase(this.maze[this.row][this.col].getBottom().getAnswer())|| answer.equalsIgnoreCase("pass"))
         {
            this.maze[this.row][this.col].getBottom().setOpen(true);
            this.maze[this.row][this.col].getBottom().setLocked(false);
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
         if(answer.equalsIgnoreCase(this.maze[this.row-1][this.col].getBottom().getAnswer())|| answer.equalsIgnoreCase("pass"))
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
         if(answer.equalsIgnoreCase(this.maze[this.row][this.col-1].getRight().getAnswer())|| answer.equalsIgnoreCase("pass"))
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
   
   
   //
   //checks and make sure door is not locked or hit a wall
   public String checkLockedOrWall(Door door, String position) 
   {
      if(position.equalsIgnoreCase("down"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.row++;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }//end if((door.getLocked()==false && door.getOpen()==true))
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }// end else if(door.getLocked()==true)
      }//end  if(position.equalsIgnoreCase("down"))
      else if(position.equalsIgnoreCase("right"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.col++;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }//end if((door.getLocked()==false && door.getOpen()==true))
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }// ennd  else if(door.getLocked()==true)
      }//end  else if(position.equalsIgnoreCase("right"))
      else if(position.equalsIgnoreCase("left"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.col--;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }//end  if((door.getLocked()==false && door.getOpen()==true))
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }//end else if(door.getLocked()==true)
      }//end  else if(position.equalsIgnoreCase("left"))
      else if(position.equalsIgnoreCase("up"))
      {
         if((door.getLocked()==false && door.getOpen()==true))
         {
            this.row--;
            return "Your new position for open door is " + this.row + ", " + this.col;
         }//end  if((door.getLocked()==false && door.getOpen()==true))
         else if(door.getLocked()==true)
         {
            return "This door is locked, cannot move that way";
         }// end   else if(door.getLocked()==true)
      }//end  else if(position.equalsIgnoreCase("up"))
   
      return "should not be here";
   }//end checkLockedOrWall
   
   
   
   //
 //moving around the maze if it is valid
   public String move(String dir)
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
               if(this.skip > 0)
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
                  }//end if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }//end while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  
               }//end if it is a true/false question
               
               
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
               }//end else multiple choice question
               
             
               return getCheckAnswer("right",answer); //check if the answer is correct or not
               
            }//end if(door.getLocked()==false && door.getOpen()==false)
            
            return checkLockedOrWall(door,"right");//if can't move then check if door is locked or wall
         
         }//end if column is valid for right
         
         return "You cannot move that way";
      } //end if the direction is right
      
      
      else if(dir.equalsIgnoreCase("down"))
      {
         
         if(this.row < size - 1)
         {
            Door door = this.maze[this.row][this.col].getBottom();
            if(door.getLocked()==false && door.getOpen()==false)
            {
               this.maze[this.row][this.col].getBottom().getQuestion();
              System.out.println(door.getThisQuestion());
              if(this.skip > 0)
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
                  }//end if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }//end while(answer.equalsIgnoreCase("skip")&&this.skip<1)
               }// end if it is a true/false question
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }//end if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  } //end  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  
               }//end else multiple choice question
               
             
               return getCheckAnswer("down",answer); //check if the answer is correct or not
               
            }//end if(door.getLocked()==false && door.getOpen()==false)
            
            return checkLockedOrWall(door,"down");//if can't move then check if door is locked or wall
            
         } //end  if(this.row < size - 1)
        
         return "You cannot move that way";
         
      }// end  else if(dir.equalsIgnoreCase("down"))
      
      
      
      else if(dir.equalsIgnoreCase("up"))
      {
         if(this.row > 0)
         {
            Door door = this.maze[this.row-1][this.col].getBottom();
            
            if(door.getLocked()==false && door.getOpen()==false)
            {
            
               this.maze[this.row-1][this.col].getBottom().getQuestion();
               System.out.println(door.getThisQuestion());
               if(this.skip > 0)
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
                  }//end  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }// end  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
               }// end if(questionType == 0)
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }//end  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  }//end while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  
               }    //end else //if multiple choice question          
             
             
               return getCheckAnswer("up",answer); //check if the answer is correct or not
               
            }//end  if(door.getLocked()==false && door.getOpen()==false)
            
            return checkLockedOrWall(door,"up");//if can't move then check if door is locked or wall
         
            
         } // end  if(this.row > 0)
        
         return "You cannot move that way";
      }//end  else if(dir.equalsIgnoreCase("up"))
      
      
      
      else if(dir.equalsIgnoreCase("left"))
      {
         if(this.col > 0)
         {
            Door door = this.maze[this.row][this.col-1].getRight();
            if(door.getLocked()==false && door.getOpen()==false)
            {
            
              this.maze[this.row][this.col-1].getRight().getQuestion();
               System.out.println(door.getThisQuestion());
               if(this.skip > 0)
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
                  }//  end if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkTrueFalse(copyAnswer);
                  }//end  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
               }//end  if(questionType == 0)
               
               
               else //if multiple choice question
               {
            	   door.setMCquestion();
                  answer = checkMultiple(copyAnswer);//check if answer is valid like a b c d
                  if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  {
                	  this.skip--;
                	  return ("Alright, you have "+this.skip+" skip(s) left.");
                  }// end if(answer.equalsIgnoreCase("skip")&&this.skip>0)
                  while(answer.equalsIgnoreCase("skip")&&this.skip<1)
                  {
                	  System.out.println("You can't skip anymore.");
                	  System.out.println(door.getThisQuestion());
                      answer = kb.nextLine();
                      copyAnswer = answer;
                      answer = checkMultiple(copyAnswer);
                  } // while(answer.equalsIgnoreCase("skip")&&this.skip<1)
               }//end if multiple choice question
               
             
             
               return getCheckAnswer("left",answer); //check if the answer is correct or not
            }// end  if(door.getLocked()==false && door.getOpen()==false)
            
            return checkLockedOrWall(door,"left");//if can't move then check if door is locked or wall
            
         }//end else if(dir.equalsIgnoreCase("left"))
        
         return "You cannot move that way";
         
      }//end  else if(dir.equalsIgnoreCase("left"))
      
      return "Invalid input!";
      
   }//end move
   
   
}//end class
	
