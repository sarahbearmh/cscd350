package trivia;
import java.sql.*;
import java.util.*;

public class Door {
	
	Random rand = new Random();
	
	private String question;
	private String answer;
	private boolean locked;
	private boolean open; 
	private int mainID;
	private int questionType = 0;
   
   public Door()
   {
	  this.question = null;
	  this.answer = null;
	  this.locked = true;
	  this.open = false;
      open = true;
   }//end door	
   
   public Door(String value)
   {
	   	   this.question = "";
	   	   this.answer = "";
	   	   this.locked = false;
	   	   this.open = false;
   }//EVC
	
   public String getQuestion()
   {
	   Connection c = null;
	   Statement stmt = null;

	   try
	   {
		   // in database 0 = false, 1 = true
		   //if randId == 0, then trigger a t/f question
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
		   
		   if(questionType == 0)
		   {
			   System.out.println("True/False Question");
			   stmt = c.createStatement();
			   ResultSet res = stmt.executeQuery("SELECT id, QUESTION from TrueFalseQuestion WHERE PLAYED = 0");
			   
			   mainID = res.getInt("id");
			   //System.out.println("MainID: "+mainID);
			   String question = res.getString("question");
			   System.out.println(question);
			  
			   //test
			   //uncomment when ready to set questions to 1 after questions is used.
			   
			   String sql = "UPDATE TrueFalseQuestion SET PLAYED = 0 WHERE id = '"+mainID+"'";
			   stmt.executeUpdate(sql);
			   
			   //test test test
			   
			   /*
			   sql = "SELECT * FROM TrueFalseQuestion"; 
			   res = stmt.executeQuery(sql);
			   
			   while(res.next())
			   {
				   int id = res.getInt("id");
				   String ques = res.getString("question");
				   int an = res.getInt("answer");
				   int played = res.getInt("played");
				   
				   System.out.println("ID: "+id);
				   System.out.println("Question: "+ques);
				   System.out.println("Answer: "+an);
				   System.out.println("Played: "+played);
			   }
			   */
			   res.close();
			   stmt.close();
			   
			   //update played to 1. since question has already been used. 
		   }
		   else 
		   {
			   System.out.println("Multiple Choice Question");
			   stmt = c.createStatement();
			   ResultSet res = stmt.executeQuery("SELECT id, QUESTION from multipleChoiceQuestion WHERE PLAYED = 0");
			   
			   mainID = res.getInt("id");
			   //System.out.println(mainID);
			   String question = res.getString("question");
			   System.out.print(question);
			   
			   res.close();
			   stmt.close();
		   }
		   
	   }
	   catch(Exception e)
	   {
		   
	   }
	   return this.question;
   }//end getQuestion
   
   public String getAnswer()
   {
	   Connection c = null;
	   Statement stmt = null;

	   try
	   {
		   if(questionType == 0)
		   {
			   Class.forName("org.sqlite.JDBC");
			   c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
			   stmt = c.createStatement();  
			   ResultSet res = stmt.executeQuery("SELECT ANSWER from TrueFalseQuestion WHERE ID = '"+mainID+"'");
			  
			   int ans = res.getInt("answer");
			   //System.out.println(ans);
			   
			   if(ans == 1)
				   this.answer = "t";
			   else 
				   this.answer = "f";
			   
			   res.close();
			   stmt.close();
			   
			   
		   }//end if
		   else
		   {
			   Class.forName("org.sqlite.JDBC");
			   c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
			   stmt = c.createStatement();  
			   ResultSet res = stmt.executeQuery("SELECT ANSWER from multipleChoiceQuestion WHERE ID = '"+mainID+"'");
			  
			   int ans = res.getInt("answer");
			   System.out.println(ans);
			  
			 //need to change these to letters for multiple choice questions answer
			   if(ans == 1)
				   this.answer = "t";
			   else 
				   this.answer = "f";
			   
			   
			   res.close();
			   stmt.close(); 
		   }//end else

	   }
	   catch(Exception e)
	   {
		   
	   }
	   return this.answer;
   }//end getAnswer
   
   public boolean getLocked()
   {
      return this.locked;
   }//end getOpen   
  
   public void setLocked(boolean lock)
   {
      this.locked = lock;
   }//end setOpen
   
   public boolean getOpen()
   {
      return this.open;
   }//end getOpen   
  
   public void setOpen(boolean open)
   {
      this.open = open;
   }//end setOpen
   
 
  

   
}//end class
	
