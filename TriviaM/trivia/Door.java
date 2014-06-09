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
   }
    
   public String getUserInput()
   {
	   return this.userInput;
   }//end getUserInput
   
   public void setUserInput(String input)
   {
	  this.userInput = input;
   }//end getUserInput
   
   
   public void setTFquestion()
   {
	   Connection c = null;
	   Statement stmt = null;
	      
	      try
	      {
	        
	         System.out.println(questionType);
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
				   System.out.println("QUESTION TYPE IS: "+questionType);
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
				   System.out.println("ran out of TF QUESTION TYPE IS: "+questionType);
				   System.out.println("Multiple Choice Question");
				   res = stmt.executeQuery("SELECT id, QUESTION, a, b, c, d from multipleChoiceQuestion WHERE PLAYED = 0 ORDER BY RANDOM() LIMIT 1");
				   
				   mainID = res.getInt("id");
				   String question = res.getString("question");
				   String a = res.getString("a");
				   String b = res.getString("b"); 
				   String ansC = res.getString("c"); 
				   String d = res.getString("d"); 
				   
				   this.question = "a. "+a+"\t b. "+b+" \nc. "+ansC+"  d. "+d;
						   
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
				   
				   System.out.println("QUESTION TYPE IS: "+questionType);
				   this.questionType = 1;
			   System.out.println("Multiple Choice Question");
			   res = stmt.executeQuery("SELECT id, QUESTION, a, b, c, d from multipleChoiceQuestion WHERE PLAYED = 0 ORDER BY RANDOM() LIMIT 1");
			   
			   mainID = res.getInt("id");
			   String question = res.getString("question");
			   String a = res.getString("a");
			   String b = res.getString("b"); 
			   String ansC = res.getString("c"); 
			   String d = res.getString("d"); 
			   
		
			   
			  this.question = "a. "+a+"\t b. "+b+" \nc. "+ansC+"  d. "+d;
			   
			   res.close();
			   stmt.close();
			   }
			   else
			   {
				   this.questionType = 0;
				   System.out.println("ran outof MC QUESTION TYPE IS: "+questionType);
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
   
   
   
   
   public String getAnswer()
   {
      Connection c = null;
      Statement stmt = null;
      System.out.println("in getAnswer");
     System.out.println("quesionTYPE IN ANSWER is: "+this.questionType);
   
      try
      {
         if(this.questionType == 0)
         {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
            stmt = c.createStatement();  
            ResultSet res = stmt.executeQuery("SELECT ANSWER from TrueFalseQuestion WHERE ID = '"+mainID+"'");
           System.out.println("after selecting answer");
            int ans = res.getInt("answer");
            System.out.println("T/F answer id is: "+this.mainID);
            System.out.println("answer is: "+ans);
            
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
            System.out.println("after selecting answer in MC");
            String ans = res.getString("answer");
            System.out.println("MC answer id is: "+this.mainID);
            System.out.println("answer is: "+ans);
           
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
	
