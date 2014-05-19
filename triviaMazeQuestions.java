package triviaQuestionDB;

import java.sql.*;

public class triviaMazeQuestions {

	public static void main(String[] args) 
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE  IF NOT EXISTS TrueFalseQuestion " +
	                   " (ID INT AUTO_INCREMENT, " +
	                   " QUESTION       TEXT    	NOT NULL, " + 
	                   " ANSWER         BOOLEAN     NOT NULL, " +
	                   " PLAYED			BOOLEAN		NOT NULL, " +
	                   " PRIMARY KEY(ID))"; 
	      stmt.executeUpdate(sql);
	      
	      stmt = c.createStatement();
	      String sql2 = "CREATE TABLE  IF NOT EXISTS multipleChoiceQuestion " +
	                   " (ID INT AUTO_INCREMENT, " +
	                   " QUESTION       TEXT    	NOT NULL, " + 
	                   " A         TEXT     NOT NULL, " +
	                   " B         TEXT     NOT NULL, " +
	                   " C         TEXT     NOT NULL, " +
	                   " D         TEXT     NOT NULL, " +
	                   " PLAYED			BOOLEAN		NOT NULL, " +
	                   " PRIMARY KEY(ID))"; 
	      stmt.executeUpdate(sql2);
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	    
	    closedDatabase(c);
			
	}//END MAIN
	
	//
	//if database is closed we need to reset the "play" field back to false so the questions can be asked again
	//
	//right now if the connection is closed then reset "play" field
	private static void closedDatabase(Connection c)
	{
		try
		{
			if(c.isClosed())
			{
				
				System.out.println("Connection Closed: Played field is reset to False");
			}
		}//end try
		catch(Exception e)
		{
			System.err.println("SQLException: "
			        +e.getMessage());
		}
	}
	

}
