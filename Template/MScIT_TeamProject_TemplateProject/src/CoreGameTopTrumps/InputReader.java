package CoreGameTopTrumps;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//making a general class with static methods that handles all user input

public class InputReader {
	
	private static Scanner sc = new Scanner(System.in);
	
	//Receives any key input from user as a means of acknowledgement
	public void pressEnter() {
		System.out.println("Press enter to continue");
		String input = sc.nextLine();
	}
	
	/**
	*This method will ensure that the user enters a correct number
	**/
	
	public int parseInt() {
		int inInt = 0;
		boolean correctInput = false;
		
		while(!correctInput) {
			String input = sc.nextLine();
			
			try {
				Scanner t = new Scanner(input);
				inInt = t.nextInt(); 
				t.close();
				correctInput = true;
			}catch(InputMismatchException e) {
				System.out.println("Please input a whole number\n");
				correctInput = false;
			}catch(NoSuchElementException b) {
				System.out.println("Please input a whole number\n");
				correctInput = false;
			}
			
		}
		
		return inInt;
	}

}
