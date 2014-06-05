package trivia;

import java.util.Scanner;

public interface IAdmin 
{
	void printMC();
	void printTF();
	void addMCQ(String question, String a1, String a2, String a3, String a4, String correct);
	void addTFQ(String question, String answer);
}//end interface
