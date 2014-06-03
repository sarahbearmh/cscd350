package trivia;
import java.sql.*;

public class TriviaMazeQuestion {

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
	                   " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
	                   " QUESTION       TEXT    	NOT NULL, " + 
	                   " ANSWER         BOOLEAN     NOT NULL, " +
	                   " PLAYED			BOOLEAN		NOT NULL)"; 
	      stmt.executeUpdate(sql);
	      
	      stmt = c.createStatement();
	      String sql2 = "CREATE TABLE  IF NOT EXISTS multipleChoiceQuestion " +
	                   " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
	                   " QUESTION       TEXT    	NOT NULL, " + 
	                   " A         TEXT     NOT NULL, " +
	                   " B         TEXT     NOT NULL, " +
	                   " C         TEXT     NOT NULL, " +
	                   " D         TEXT     NOT NULL, " +
	                   " ANSWER         TEXT     NOT NULL, " +
	                   " PLAYED			BOOLEAN		NOT NULL)"; 
	      stmt.executeUpdate(sql2);
	      /*
	      stmt = c.createStatement();
	      String sql3 = "INSERT INTO multipleChoiceQuestion " +
	    		  		"VALUES (1,'Track and field star Carl Lewis won how many gold medals at the 1984 Olympic games?', 'two', 'three', 'four', 'eight', 'c', 0)";
	    		  		
	      stmt.executeUpdate(sql3);*/
	      
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");

			
	}//END MAIN

	public void closedDatabase()
	{
		Connection c = null;
	    Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:triviaQuestionDB.db");

			stmt = c.createStatement();
				    
			String sql = "UPDATE TrueFalseQuestion SET PLAYED = 0 WHERE PLAYED = 1";
			stmt.executeUpdate(sql);
			
			String sql1 = "UPDATE multipleChoiceQuestion SET PLAYED = 0 WHERE PLAYED = 1";
			stmt.executeUpdate(sql1);
			
		}//end try
		catch(Exception e)
		{
			System.err.println("SQLException: "
			        +e.getMessage());
		}
	}
	

}
