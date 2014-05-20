package trivia;
public class Room 
{
	private Door bDoor;
	private Door rDoor;
   
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
   }//end room
   
   public Door getBottom()
   {
	   return this.bDoor;
   }
   public Door getRight()
   {
	   return this.rDoor;
   }
}//end class
	
