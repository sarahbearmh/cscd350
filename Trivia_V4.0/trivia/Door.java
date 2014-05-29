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
	private String tried;
   
   public Door()
   {
	  this.question = null;
	  this.answer = null;
	  this.locked = true;
	  this.open = false;
      open = true;
      this.tried = "no";
     
   }//end door	
   
   public Door(String value)
   {
	   	   this.question = "";
	   	   this.answer = "";
	   	   this.locked = false;
	   	   this.open = false;
	   	 this.tried = "no";
   }//EVC
  
   public boolean getTried()
   {
	  if(this.tried.compareTo("no")==0)
		  return false;
	  return true;
   }
   public void setTried(String tried)
   {
	   this.tried = tried;
   }
   
	
   public String getQuestion()
   {
	   Connection c = null;
	   Statement stmt = null;
	   
	   try
	   {
		   int count = 0;//# of records in the table
		   questionType = rand.nextInt(2); 
		   System.out.println(questionType);
		   // in database 0 = false, 1 = true
		   //if randId == 0, then trigger a t/f question
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
		   
		   if(questionType == 0)
		   {
			   stmt = c.createStatement();
			   String sql1 = "SELECT COUNT(*) FROM TrueFalseQuestion";
			   ResultSet res = stmt.executeQuery(sql1);
			   
			   while(res.next())
			   {
				   count = res.getInt(1);
			   }
			   
			   System.out.println(count);
			   
			   System.out.println("True/False Question");
			   stmt = c.createStatement();
			   res = stmt.executeQuery("SELECT id, QUESTION from TrueFalseQuestion WHERE PLAYED = 0");
			   
			   mainID = res.getInt("id");
			   //System.out.println("MainID: "+mainID);
			   String question = res.getString("question");
			   System.out.println(question);

			   String sql = "UPDATE TrueFalseQuestion SET PLAYED = 1 WHERE id = '"+mainID+"'";
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
			   stmt = c.createStatement();
			   String sql1 = "SELECT COUNT(*) FROM multipleChoiceQuestion";
			   ResultSet res = stmt.executeQuery(sql1);
			   
			   while(res.next())
			   {
				   count = res.getInt(1);
			   }
			   
			   System.out.println("Multiple Choice Question");
			   res = stmt.executeQuery("SELECT id, QUESTION, a, b, c, d from multipleChoiceQuestion WHERE PLAYED = 0");
			   
			   mainID = res.getInt("id");
			   //System.out.println(mainID);
			   String question = res.getString("question");
			   String a = res.getString("a");
			   String b = res.getString("b"); 
			   String ansC = res.getString("c"); 
			   String d = res.getString("d"); 
			   
			   System.out.println(question);
			   System.out.println("a. "+a+"\t b. "+b);
			   System.out.println("c. "+ansC+"\t d. "+d);
			   
			   String sql = "UPDATE multipleChoiceQuestion SET PLAYED = 1 WHERE id = '"+mainID+"'";
			   stmt.executeUpdate(sql);
			   
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
			  
			   String ans = res.getString("answer");
			   //System.out.println(ans);
			  
			 //need to change these to letters for multiple choice questions answer
			   if(ans.equalsIgnoreCase("a"))
				   this.answer = "a";
			   else if(ans.equalsIgnoreCase("b"))
				   this.answer = "b";
			   else if(ans.equalsIgnoreCase("c"))
				   this.answer = "c";
			   else if(ans.equalsIgnoreCase("d"))
				   this.answer = "d";
			   
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
	
