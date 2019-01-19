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
	
	
	/* my method for parsing money: works by requesting a double 
	 * and then using testMoneyFormat() to check if there are <= 2
	 * Decimal places after the point 
	 */
	public int parseMoney() {
		
		//Made an input reader class so that I can use the private method testMoneyFormat();
		InputReader tester = new InputReader(); 
		
		int penceVal = 0;		
		double moneyValue = 0;
		boolean correctFormat = false;
		
		while (!correctFormat) {
			String thisScan = sc.nextLine();
			
			try {
				Scanner t = new Scanner(thisScan);
				moneyValue = t.nextDouble(); 
				t.close();
				if(tester.testMoneyFormat(moneyValue)) { //returns true if correct format
					penceVal = (int)(moneyValue*100);
					correctFormat = true;
				} else {
					throw new InputMismatchException();
				}
				
			}catch(InputMismatchException e) {
				System.out.println("Please re-enter in the format of pounds and pence with a decimal point\n");
				correctFormat = false;
			}catch(NoSuchElementException b) {
				System.out.println("Please re-enter in the format of pounds and pence with a decimal point\n");
				correctFormat = false;
			}
			
		}
		
		
		return penceVal;
		
	}
		
	
	//Method for reading strings - only an empty string is rejected
	public String parseString() {

		String input = sc.nextLine();
		
		while(input.isEmpty()) {
			System.out.println("Please enter some text");
			input = sc.nextLine();
		}
			
//		System.out.println(input);
				
		return input;
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
	
	
	private boolean testMoneyFormat(double input) {
		
		//This method splits the string at the decimal place (splitting using the period did not seem to work)
		String[] testValue = String.valueOf(input).replace(".", "@").split("@");
		
		//if the number of digits after the decimal point is less <=2 then return true
		if(testValue[1].length() <= 2) {
			return true;
		}		
		
		return false;
	}
	

}
