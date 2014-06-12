package trivia;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class Door  implements Serializable{
	
   Random rand = new Random();
	
   private String question;
   private String answer;
   private boolean locked;
   private boolean open; 
   private int mainID;
   private int questionType;
   private String tried;
   private String userInput;
   
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
   }//end setTried
    
   public String getUserInput()
   {
	   return this.userInput;
   }//end getUserInput
   
   public void setUserInput(String input)
   {
	  this.userInput = input;
   }//end getUserInput
   
   //
   //set the TF questions to 1 when question has be asked from the database
   public void setTFquestion()
   {
	   Connection c = null;
	   Statement stmt = null;
	      
	      try
	      {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	         stmt = c.createStatement();
	               String sql = "UPDATE TrueFalseQuestion SET PLAYED = 1 WHERE id = '"+mainID+"'";
	               stmt.executeUpdate(sql);
	              stmt.close();
	              c.close();
	      }
	      catch(Exception e)
	      {
	         System.out.println("error: " + e);
	      }
   }//setTFquestion
   
   //
   //set the MC questions to 1 when question has be asked from database
   public void setMCquestion()
   {
	   Connection c = null;
	   Statement stmt = null;
	      
	      try
	      {
	        
	         System.out.println(questionType);
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	         stmt = c.createStatement();
	         String sql = "UPDATE multipleChoiceQuestion SET PLAYED = 1 WHERE id = '"+mainID+"'";
	         stmt.executeUpdate(sql);
	              stmt.close();
	      }
	      catch(Exception e)
	      {
	         System.out.println("error: " + e);
	      }
	   
   }//end setTFquestion
   
   //
   //get a question from the database when user triggers a door. if question type == 0 then its a T/F question else its a MC question
   //this will also check to see if we run out of T/F questions then it'll grab a MC question. Vice versa
   public String getQuestion()
   {
	   Connection c = null;
	   Statement stmt = null;
	   
	   try
	   {
		   questionType = rand.nextInt(2); 
		   
		   Class.forName("org.sqlite.JDBC");
		   c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
		   int countTF = 0; 
		   
		   if(questionType == 0)
		   {
			   stmt = c.createStatement();
			   String sql1 = "SELECT COUNT(*) FROM TrueFalseQuestion WHERE played = 0";
			   ResultSet res = stmt.executeQuery(sql1);
			   
			   while(res.next())
			   {
				   countTF = res.getInt(1);
			   }

			   if(countTF > 0)
			   {
				  
				   this.questionType = 0;
				   System.out.println("True/False Question");
				   stmt = c.createStatement();
				   res = stmt.executeQuery("SELECT id, Question FROM TrueFalseQuestion WHERE played = 0 ORDER BY RANDOM() LIMIT 1");
				   
				   mainID = res.getInt("id");
				   String question = res.getString("question");
				   
				   
				   this.question = question;
				   res.close();
				   stmt.close();
			   }
			   else 
			   {
				   this.questionType = 1;
				 
				   System.out.println("Multiple Choice Question");
				   res = stmt.executeQuery("SELECT id, QUESTION, a, b, c, d from multipleChoiceQuestion WHERE PLAYED = 0 ORDER BY RANDOM() LIMIT 1");
				   
				   mainID = res.getInt("id");
				   String question = res.getString("question");
				   String a = res.getString("a");
				   String b = res.getString("b"); 
				   String ansC = res.getString("c"); 
				   String d = res.getString("d"); 
				   
				   this.question = question+"\na. "+a+"\t b. "+b+" \nc. "+ansC+"  d. "+d;
						   
				   res.close();
				   stmt.close();
			   }
			   
		   }
		   else 
		   {
			  
			   stmt = c.createStatement();
			   String sql1 = "SELECT COUNT(*) FROM multipleChoiceQuestion WHERE Played = 0";
			   ResultSet res = stmt.executeQuery(sql1);
			   int countMC = 0;
			   
			   while(res.next())
			   {
				   countMC = res.getInt(1);
			   }
			   
			   if(countMC > 0)
			   {
				   
				this.questionType = 1;
			   System.out.println("Multiple Choice Question");
			   res = stmt.executeQuery("SELECT id, QUESTION, a, b, c, d from multipleChoiceQuestion WHERE PLAYED = 0 ORDER BY RANDOM() LIMIT 1");
			   
			   mainID = res.getInt("id");
			   String question = res.getString("question");
			   String a = res.getString("a");
			   String b = res.getString("b"); 
			   String ansC = res.getString("c"); 
			   String d = res.getString("d"); 
			   
		
			   
			  this.question = question+"\na. "+a+"\t b. "+b+" \nc. "+ansC+"  d. "+d;
			   
			   res.close();
			   stmt.close();
			   }
			   else
			   {
				   this.questionType = 0;
				  
				   System.out.println("True/False Question");
				   stmt = c.createStatement();
				   res = stmt.executeQuery("SELECT id, Question FROM TrueFalseQuestion WHERE played = 0 ORDER BY RANDOM() LIMIT 1");
				   
				   mainID = res.getInt("id");
				   String question = res.getString("question");
				  this.question = question;
				   res.close();
				   stmt.close();
			   }
		   }
		   
	   }
	   catch(Exception e)
	   {
		   System.out.println("error: " + e);
	   }
	   return this.question;

   }//end getQuestion
   
   //
   //will get the answer for the question from the database and return the answer the the maze class
   public String getAnswer()
   {
      Connection c = null;
      Statement stmt = null;
    
      try
      {
         if(this.questionType == 0)
         {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
            stmt = c.createStatement();  
            ResultSet res = stmt.executeQuery("SELECT ANSWER from TrueFalseQuestion WHERE ID = '"+mainID+"'");
          
            int ans = res.getInt("answer");
            
            
            if(ans == 1)
               this.answer = "t";
            else 
               this.answer = "f";
            
            
            res.close();
            
         }//end if
         else
         {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
            stmt = c.createStatement();  
            ResultSet res = stmt.executeQuery("SELECT ANSWER from multipleChoiceQuestion WHERE ID = '"+mainID+"'");
           
            String ans = res.getString("answer");
          
           
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
         stmt.close();
         c.close();
        
      }
      catch(Exception e)
      {
         System.out.println("error: " + e);
      }
      return this.answer;
   }//end getAnswer
   
   //
   //return questionType 1 or 0
   public int getType()
   {
      return questionType;
   }
   
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
   
public String getThisQuestion()
{
	return this.question;
}//end getThisQuestion
   
}//end class
	
