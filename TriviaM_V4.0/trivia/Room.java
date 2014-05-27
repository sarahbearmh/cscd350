package trivia;
public class Room 
{
	private Door bDoor;
	private Door rDoor;
	private Door tDoor;
	private Door lDoor;
   private boolean hasRdoor;
   private boolean hasBdoor;
   
   public Room(boolean bottom, boolean right)
   {
      if(bottom)
      {
    	  this.bDoor = new Door("hi");
        this.tDoor = this.bDoor;
      }      
      if(right)
      {
    	  this.rDoor = new Door("hi");
        this.lDoor = this.rDoor;
      }
      this.hasRdoor = right;
      this.hasBdoor = bottom;     
   }//end room
   
   public boolean hasRdoor()
   {
   return this.hasRdoor;
   }
   
   public boolean hasBdoor()
   {
   return this.hasBdoor;
   }

   
     public Door getBottom()
   {
	   return this.bDoor;
   }
   public Door getRight()
   {
	   return this.rDoor;
   }
   public Door getTop()
   {
	  return this.tDoor;
   }
   public Door getLeft()
   {
	   return this.lDoor;
   }
}//end class

	
