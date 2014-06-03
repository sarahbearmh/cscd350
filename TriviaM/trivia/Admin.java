package trivia;

import java.sql.*;

public class Admin 
{
	public Admin()
	{
	}

	public void addTFQ(String question, String answer)
	{
		Connection c = null;
	    Statement stmt = null;
	    int answerVal;
	    if(answer.equalsIgnoreCase("true"))
	    {
	    	answerVal = 1;
	    }//end if
	    else
	    {
	    	answerVal = 0;
	    }//end else
	    try
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	   
	    	stmt = c.createStatement();
	    	String sql1 = "INSERT INTO TrueFalseQuestion(QUESTION, ANSWER, PLAYED) "
	    			+ " VALUES(\"" + question + "\", " + answerVal + ", 0)";
	    	stmt.executeUpdate(sql1);
	    
	   } //end try
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }//end catch
	}//end addTF
	public void addMCQ(String question, String a1, String a2, String a3, String a4, String correct)
	{
		Connection c = null;
	    Statement stmt = null;

	    try
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	   
	    	stmt = c.createStatement();
	    	String sql1 = "INSERT INTO multipleChoiceQuestion(Question, A, B, C, D, Answer, Played) " +
    		  		"VALUES ('" + question + "', '" + a1 + "', '" + a2 +"', '" + a3 + "', '" + a4 + "', '" + correct + "', 0)";
	    	stmt.executeUpdate(sql1);
	    
	   } //end try
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }//end catch
	}//end addMCQ
	
	
	public void printTF()
	{
		Connection c = null;
	    Statement stmt = null;
		try
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	   
	    	stmt = c.createStatement();
	    	String sql1 = "SELECT * FROM \'TrueFalseQuestion\'";
			ResultSet res = stmt.executeQuery(sql1);
			while(res.next())
			{
				int id = res.getInt("id");
				String question = res.getString("question");
				String answer = "";
				int answerVal = res.getInt("answer");
				if(answerVal == 1)
				{
					answer = "true";
				}//end if
				else
				{
					answer = "false";
				}
				System.out.println(id + ".) " + question + ", " + answer);
			}
	    
	   } //end try
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }//end catch
	}//end printTF
	
	public void printMC()
	{
		Connection c = null;
	    Statement stmt = null;
		try
	    {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	   
	    	stmt = c.createStatement();
	    	String sql1 = "SELECT * FROM \'multipleChoiceQuestion\'";
			ResultSet res = stmt.executeQuery(sql1);
			while(res.next())
			{
				int id = res.getInt("id");
				String question = res.getString("QUESTION");
				String[] answers = new String[5];
				answers[0] = res.getString("A");
				answers[1] = res.getString("B");
				answers[2] = res.getString("C");
				answers[3] = res.getString("D");
				answers[4] = res.getString("ANSWER");
				System.out.println(id + ".) " + question + ", " + answers[0] + ", " + answers[1] + ", " + answers[2] + ", " + answers[3] + ", ANSWER: " + answers[4]);
			}
	    
	   } //end try
	    catch ( Exception e ) 
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }//end catch
	}//end printMC
}//end class
