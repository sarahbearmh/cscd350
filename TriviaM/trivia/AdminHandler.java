package trivia;

import java.util.Scanner;

public class AdminHandler implements IAdminHandler
{
	public void AdminHandling(IAdmin admin, Scanner kb)
	{
		int choice = 0;
		while(choice != 5)
		  {
			  System.out.println(" 1.) Add question to T/F database. \n 2.) Add question to MC database. \n 3.) Print T/F. \n 4.) Print MC.\n 5.)Exit. \n");
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
				  admin.printMC();
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
		
	}//end method
		
}//end class

