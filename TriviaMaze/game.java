public class game
{
   private int sizeOfMaze, numOfPlayer; 
   
   public game(int size, int player)
   {
      this.sizeOfMaze = size;
      this.numOfPlayer = player;
   }//end game
	
   public int getMazeSize()
   {
      return this.sizeOfMaze;
   }//end getMazeSize  
  
   public int getNumOfPlayer()
   {
      return this.numOfPlayer;
   }//end getNumOfPlayer
   
   public void setMazeSize(int size)
   {
      this.sizeOfMaze = size;
   }//end setMazeSize
   
   public void setPlayer(int player)
   {
      this.numOfPlayer = player;
   }//end setPlayer
   
}//end class
	
