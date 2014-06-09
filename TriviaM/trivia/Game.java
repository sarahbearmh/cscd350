package trivia;
import java.util.*;


public class Game
{
   public static void main(String[] args)
   {
   
      boolean win = false;
      boolean lose = false;
      int sizeOfMaze = 0;
      String name = "";
      String sizeString = "";
      TriviaMazeQuestion mazeQ = new TriviaMazeQuestion(); 
      mazeQ.closedDatabase();
      Scanner kb = new Scanner(System.in);
      System.out.println("Please Enter Your Name: ");
      name = kb.nextLine();
      if(name.equals("admin"))
      {
         System.out.println("Enter the password:");
         String pass = kb.nextLine();
         
         if(pass.equals("gameAdmin"))
         {
            IAdmin admin = new Admin();
            IAdminHandler adminH = new AdminHandler();
            adminH.AdminHandling(admin, kb);	  
         }
      }
      else
      {
         while(!sizeString.equalsIgnoreCase("S") && !sizeString.equalsIgnoreCase("M") && !sizeString.equalsIgnoreCase("L"))
         {
            System.out.println("Please Select A Size: S, M, L ");
            sizeString = kb.nextLine();
            //Player person = new Player(name);
            if(sizeString.equalsIgnoreCase("S"))
            {
               sizeOfMaze = 4;
            }//end if
            else if(sizeString.equalsIgnoreCase("M"))
            {
               sizeOfMaze = 6;
            }//end else if
            else if(sizeString.equalsIgnoreCase("L"))
            {
               sizeOfMaze = 8;
            }//end else if
         }//end while
         
         Maze curMaze = new Maze(sizeOfMaze);
         curMaze.fillMaze();
         String move = "";
         System.out.println("WELCOME TO TRIVIA MAZE!");
         System.out.println("You may skip questions 3 times by typing 'skip'.  It is not case sensitive.");
         System.out.println("You may also quit the game by typing 'quit'.  It is not case sensitive.");
         while((curMaze.getRow() < sizeOfMaze) && (curMaze.getCol() < sizeOfMaze) && !win && !move.equalsIgnoreCase("quit") && lose==false)
         {   
            boolean loser = true;
            curMaze.printMaze();
            System.out.println("Would you like to move right, left, down, or up?"); 
            move = kb.nextLine();
            
            if(!move.equalsIgnoreCase("quit"))
            {
               String value = curMaze.move(move);
               System.out.println(value);
               loser = curMaze.traverse(curMaze.getRow(), curMaze.getCol());
            
              
            }//end if
            if(move.equalsIgnoreCase("quit"))
            {
               System.out.println("Exiting");
            }//end if
            
            if(loser==false)
            {
               curMaze.printMaze();
               System.out.println("All locked up, you lose.");
               lose = true;
               mazeQ.closedDatabase();
            }
            
            if(curMaze.getRow() == sizeOfMaze -1 && curMaze.getCol() == sizeOfMaze - 1)
            {
               curMaze.printMaze();
               System.out.println("You Win");
               win = true;
               mazeQ.closedDatabase();
            }//end if
         }//end while
      }//end else
      kb.close();
   }//end main

}//end class
