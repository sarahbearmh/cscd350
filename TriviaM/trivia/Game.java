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
			  int choice = 0;
			  Admin admin = new Admin();
			  while(choice != 5)
			  {
				  System.out.println("1.) Add question to T/F database. \n 2.) Add question to MC database. \n 3.) Print T/F. \n 4.) Print MC.\n 5.)Exit. \n");
				  choice = kb.nextInt();
				  if(choice == 1)
				  {  
					  kb.nextLine();
					  System.out.println("Enter a True False Question: ");
					  String question = kb.nextLine();
					  
					  System.out.println("Enter the answer(true/false): ");
					  String tfAnswer = kb.nextLine().toLowerCase();
					  admin.addTFQ(question, tfAnswer);
				  }
				  else if(choice == 2)
				  {
					  kb.nextLine();
					  System.out.println("Enter a Multiple Choice Question: ");
					  String question = kb.nextLine();
					  
					  System.out.println("Enter choices a-d seperated by a ', '.(ex. one, two, three, four): ");
					  String[] answer = kb.nextLine().split(", ");
					  System.out.println("Please enter the correct answer(a-d)");
					  String correct = kb.nextLine();
					  admin.addMCQ(question, answer[0], answer[1], answer[2], answer[3], correct);
				  }
				  else if(choice == 3)
				  {
					  admin.printTF();
				  }
				  else if(choice == 4)
				  {
					  //
				  }
				  else if(choice == 5)
				  {
					  System.exit(0);
				  }
				  else
				  {
					  System.out.println("Invalid Input");
				  }
			  }	  
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
	