package trivia;

public class Door {
   private String question;
   private String answer;
   private boolean locked;
   private boolean open; 
   
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
	   	   this.question = "The answer is t";
	   	   this.answer = "t";
	   	   this.locked = false;
	   	   this.open = false;
   }//EVC
	
   public String getQuestion()
   {
      return this.question;
   }//end getQuestion
   
   public String getAnswer()
   {
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
	
