package CoreGameTopTrumps;


import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//making a general class with static methods because I would like using this to feel like applying a formula/function, without making an object every time.

public class InputReader {
		
	/*	I would like all the object calling to be abstracted away for this specific task, so I have chosen static methods
		I feel its a bit more logical because each instance of this class will do exactly the same thing and it does not hold any data
		All the data passes through this and gets stored in another variable/object. 
		This is like a 'pipeline', so should behave exactly the same all the time, and the objects should change based on the data fed into them from this.
		basically this class shouldn't change or store data!
	*/

	
	private static Scanner sc = new Scanner(System.in);
	
	//Receives any key input from user as a means of acknowledgement
	public void pressEnter() {
		System.out.println("Press enter to continue");
		String input = sc.nextLine();
	}
	
	//methods for reading an integer
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
