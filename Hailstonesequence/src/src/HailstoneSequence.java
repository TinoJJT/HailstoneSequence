package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class HailstoneSequence {
	
	public static void main(String[] args) {
		int input = 0;
		int steps = 0;
		//Values may exceed the int range in the sequence, so long is used instead.
		long currentValue;
		long largestValue;
		//Initialized value so a comparison can be made
		long secondLargestValue = 0;
		String htmlOutput;
		//New file will be created in the root directory with filename output.html
		File htmlFile = new File("output.html");
		
		Scanner scan = new Scanner(System.in);
		
		//This part is used to request the starting value for the sequence
		System.out.println("Please enter a starting value for the Hailstone Sequence");
		//In a while loop until the user enters a valid starting value
		while(input == 0){
			try{
				input = scan.nextInt();
				if(input < 1){
					System.out.println("Please only use whole numbers between 1 and 1000000");
					//Used to ensure to continue the loop even if the user enters a negative number
					input = 0;
				}
			}
			catch (Exception e){
				System.out.println("Please only use whole numbers between 1 and 1000000");
				//Necessary to move the scanner pointer forward, otherwise it will stay in the invalid value.
				scan.next();
			}
		}
		scan.close();
		
		//Calculations will not be done on the input variable, so the original value is preserved
		currentValue = input;
		largestValue = input;
		
		//Loop continues until the sequence reaches its end which is defined as 1.
		while(currentValue != 1){
			//Updates the current value according to the rules of the Hailstone sequence.
			if((currentValue & 1) == 0 ) currentValue = currentValue / 2;
			else currentValue = (currentValue * 3) + 1;
			
			//Keeps track of the largest and second largest value reached in the sequence.
			if(currentValue > largestValue){
				secondLargestValue = largestValue;
				largestValue = currentValue;
			}
			else if(currentValue > secondLargestValue) secondLargestValue = currentValue;
			
			steps++;
		}
		
		/*Holds the String of what will be written in the, containing the starting value,
		 * how many steps it took to reach final value, and the second largest value reached in the sequence.
		 * This is a very simple HTML format and may not work with all browsers. Tested to work with Firefox and Chrome.
		 */
		htmlOutput = ("<p> With input " + input + " the number of steps is " + steps +
				" and the second largest number reached is " + secondLargestValue + "</p>");
		
		//The BufferedWriter can only write inside a try block.
		try{
			//In this program, the BufferedWriter is chosen to be used as the method to write to the html file.
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(htmlFile));
			bufferedWriter.write(htmlOutput);
			bufferedWriter.close();
			//Notifies the user in the console that the file has been created.
			System.out.println("Html file created in root directory");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
