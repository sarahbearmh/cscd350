package trivia;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class RoomTestMethods {

	@Test
	public void hasRdoortest() 
	{
		Room room = new Room(false, true);
		boolean result = room.hasRdoor();
		assertTrue("right door fail", result);
	}//end rdoor
	@Test
	public void hasBdoortest() 
	{
		Room room = new Room(true, false);
		boolean result = room.hasBdoor();
		assertTrue("right door fail", result);
	}//end bdoor
	@Test
	public void getBottomTest()
	{
		Room room = new Room(true, true);
		//Door door = room.getBottom();
		assertEquals("Door is locked and should not be", false, room.getBottom().getLocked());
		room.getBottom().setLocked(true);
		assertEquals("Door is unlocked when it shouldn't be", true, room.getBottom().getLocked());
		assertEquals("Door is open when it shouldn't be.", false, room.getBottom().getOpen());
		room.getBottom().setOpen(true);
		assertEquals("Door is closed when is shouldn't be.", true, room.getBottom().getOpen());
	}//end bottom
	@Test
	public void getRightTest()
	{
		Room room = new Room(true, true);
		//Door door = room.getBottom();
		assertEquals("Door is locked and should not be", false, room.getRight().getLocked());
		room.getRight().setLocked(true);
		assertEquals("Door is unlocked when it shouldn't be", true, room.getRight().getLocked());
		assertEquals("Door is open when it shouldn't be.", false, room.getRight().getOpen());
		room.getRight().setOpen(true);
		assertEquals("Door is closed when is shouldn't be.", true, room.getRight().getOpen());
	}//end right
	@Test
	public void getQuestionTest()
	{
		Door door = new Door("hi");
		String value = door.getQuestion();
		assertNotEquals("Question is null", "", value);
	}//end question
	@Test
	public void getAnswerTest()
	{
		Door door = new Door("hi");
		door.getQuestion();
		assertNotEquals("Question is null", "", door.getAnswer());
	}//end answer
	@Test
	public void mazeTraverseTest()
	{
        Maze curMaze = new Maze(2);
        curMaze.fillMaze();
        curMaze.maze[0][0].getBottom().setLocked(true);
        assertEquals("Says there is no valid path/", true, curMaze.traverse(curMaze.getRow(), curMaze.getCol()));
        curMaze.maze[0][0].getRight().setLocked(true);
        assertEquals("Says there is a valid path when there isn't.", false, curMaze.traverse(curMaze.getRow(), curMaze.getCol()));
	}//end traverse
	@Test
	public void colRowSetTest()
	{
        Maze curMaze = new Maze(3);
        curMaze.fillMaze();
        curMaze.setCol(2);
        curMaze.setRow(1);
        assertEquals("Column is incorrect", 2, curMaze.getCol());
        assertEquals("Row is incorrect", 1, curMaze.getRow());
	}//end colrow

}
