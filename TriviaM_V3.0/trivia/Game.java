package trivia;
import java.util.*;
public class Game
{
   public static void main(String[] args)
   {
	   boolean win = false;
	   int sizeOfMaze = 0;
	   String name = "";
	   String sizeString = "";
	   Scanner kb = new Scanner(System.in);
	   System.out.println("Please Enter Your Name: ");
	   name = kb.nextLine();
	   System.out.println("Please Select A Size: S, M, L ");
	   sizeString = kb.nextLine();
	   //Player person = new Player(name);
	   if(sizeString.equalsIgnoreCase("S"))
	   {
		   sizeOfMaze = 5;
	   }
	   else if(sizeString.equalsIgnoreCase("M"))
	   {
		   sizeOfMaze = 8;
	   }
	   else if(sizeString.equalsIgnoreCase("L"))
	   {
		   sizeOfMaze = 10;
	   }
	   
	   Maze curMaze = new Maze(sizeOfMaze);
	   curMaze.fillMaze();
	
	 
	   String move;
    	
	   while((curMaze.getRow() < sizeOfMaze) && (curMaze.getCol() < sizeOfMaze) && !win)
	   {   
      curMaze.printMaze();
      
	   System.out.println("Would you like to move right, left, down, or up?"); 
	   move = kb.nextLine();
	   
	   String value = curMaze.move(move);
	   System.out.println(value);
	   
	   
      if(curMaze.getRow() == sizeOfMaze -1 && curMaze.getCol() == sizeOfMaze - 1)
	   {
		   System.out.println("You Win");
	   win = true;
	   }
	   }
	   
	   kb.close();
	   
	   
   }

}//end class
	