package trivia;

import java.io.Serializable;

public class Room  implements Serializable
{
	private Door bDoor;
	private Door rDoor;
   private boolean hasRdoor;
   private boolean hasBdoor;
   
   public Room(boolean bottom, boolean right)
   {
      if(bottom)
      {
    	  this.bDoor = new Door("hi");
      }      
      if(right)
      {
    	  this.rDoor = new Door("hi");
      }
      this.hasRdoor = right;
      this.hasBdoor = bottom;     
   }//end room
   
   public boolean hasRdoor()
   {
   return this.hasRdoor;
   }//has right
   
   public boolean hasBdoor()
   {
   return this.hasBdoor;
   }//has bottom

     public Door getBottom()
   {
	   return this.bDoor;
   }//get bottom
   public Door getRight()
   {
	   return this.rDoor;
   }//get right
}//end class

	
