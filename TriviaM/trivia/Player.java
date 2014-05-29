package trivia;

import java.io.Serializable;

public class Player  implements Serializable{
   private String name; 	
	
   public Player(String name)
   {
      this.name = name;
   }//end player
   
   public String getName()
   {
      return this.name;
   }//end getOpen   
  
   public void setName(String name)
   {
      this.name = name;
   }//end setOpen
   
}//end class
	
