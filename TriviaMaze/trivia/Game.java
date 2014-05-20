package trivia;
import java.util.*;
public class Game
{
   public static void main(String[] args)
   {
	   int sizeOfMaze = 0;
	   String name = "";
	   String sizeString = "";
	   Scanner kb = new Scanner(System.in);
	   System.out.println("Please Enter Your Name: ");
	   name = kb.nextLine();
	   System.out.println("Please Select A Size: S, M, L ");
	   sizeString = kb.nextLine();
	   Player person = new Player(name);
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
	   Maze curMaze = new Maze(sizeOfMaze, sizeOfMaze);
	   curMaze.fillMaze();
	   
	   
   }

}//end class
	
