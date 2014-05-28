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
	   if(name.equals("admin"))
	   {
		   System.out.println("Enter the password:");
		   String pass = kb.nextLine();
		   
		  if(pass.equals("gameAdmin"))
		  {
			  Admin admin = new Admin();
			  admin.addTFQ("Here is a question", "true");
			  admin.addTFQ("Here is a question2", "false");
			  admin.printTF();
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
				   sizeOfMaze = 5;
			   }//end if
			   else if(sizeString.equalsIgnoreCase("M"))
			   {
				   sizeOfMaze = 8;
			   }//end else if
			   else if(sizeString.equalsIgnoreCase("L"))
			   {
				   sizeOfMaze = 10;
			   }//end else if
		   }//end while
		   
		   Maze curMaze = new Maze(sizeOfMaze);
		   curMaze.fillMaze();
		   String move = "";
	    	
		   while((curMaze.getRow() < sizeOfMaze) && (curMaze.getCol() < sizeOfMaze) && !win && !move.equalsIgnoreCase("quit"))
		   {   
		       curMaze.printMaze();
		      
			   System.out.println("Would you like to move right, left, down, or up?"); 
			   move = kb.nextLine();
			   
			   if(!move.equalsIgnoreCase("quit"))
			   {
				   String value = curMaze.move(move);
				   System.out.println(value);
			   }//end if
			   if(move.equalsIgnoreCase("quit"))
			   {
				   System.out.println("Exiting");
			   }//end if
			   
		      if(curMaze.getRow() == sizeOfMaze -1 && curMaze.getCol() == sizeOfMaze - 1)
			   {
				   System.out.println("You Win");
				   win = true;
			   }//end if
		   }//end while
	   }//end else
	   kb.close();
   }//end main

}//end class
	