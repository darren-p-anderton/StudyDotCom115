//Darren P Anderton
//Study.com CS 115 Final Project
//01/29/2024

import java.util.Scanner;

public class CS115FinalProj {

	public static void main(String[] args) {
		//main class simply prints the project prompt then calls the mainQuizArray, which contains most of the code
		int[] mainQuizArray = new int[10];
		
		printSpecs();
		
		mainQuizArray = collectQuizArray();
		
		System.out.println("EXITING");

	}
	
	public static void printSpecs() {
		// This method will print the project specifications as defined in the CS 115 final assignment
		System.out.println("Create a Java project in IDE and begin the Project Program by developing a Java Method to print the programming specifications for the project.");
		System.out.println("Expand the Project Program and create Java code to input from the console (keyboard) the first quiz grade. Use a loop to allow the user to continue to enter grades as long as they don't enter 999 to quit.");
		System.out.println("Expand the Project Program and create Java code to populate the array of the student's quiz grades. The grade is added to a running total, and the count of grades entered is incremented. The loop ends after 10 grades are entered. When the loop ends, count holds the number of grades entered.");
		System.out.println("Expand the Project Program and create Java code use another loop to print all grades in the array.");
		System.out.println("Expand the Project Program and create Java code to compute the average quiz grade.");
		System.out.println("Expand the Project Program and create Java code to use decision logic to find out the letter grade of the average based on the following grading scale and print to the console.\rA = 90-100\rB = 80-89\rC = 70-79\rD = 60-69\rF = 0-59\r");
	}
	
	public static int[] collectQuizArray() {
		//The bulk of the project code is in this method.
		//This method will receive data from the user and perform required tranformations
		int[] quizArray = new int[10];
		int i, runTotal, count, average;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a quiz grade between 0 to 100. Enter 999 when finished.");
		
		runTotal = 0;
		for(i = 0; i < 10; i++) {
			while(true) {				//run a breakout loop for error handling to prevent an error from incrementing i
				try {					//error checking in case an integer is not entered
					quizArray[i] = input.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Please enter an integer: " + e);
					input.nextLine();
				}
			}
			if(quizArray[i] == 999) {	//breakout condition
				quizArray[i] = 0;
				System.out.println("Terminating grade entry");
				break;
			}
			runTotal += quizArray[i];
			
			if(quizArray[i] > 100 || quizArray[i] < 0) {
				System.out.println("Quiz grade out of bounds. Discarding entry...");		//condition in case int is OOB
				runTotal -= quizArray[i];			//This will reverse out the prior upades to the array
				i--;
			}
		}
		count = i; 
		if(count == 0) return quizArray; //return early if no data is entered
		System.out.println("Total score: " + runTotal);
		System.out.println("Grades entered: " + count);
		
		printArray(quizArray, count);
		average = printAverage(runTotal, count);
		printGrades(quizArray, count);
		
		System.out.print("The average quiz grade is: " + determineLetterGrade(average)+ "\r");
		
		return quizArray;
	}
	
	public static void printArray(int[] arrayToPrint, int quizLen) {
		//Method recieves an array and set length, and prints the array up to that defined length.
		int i;
		System.out.println("Quiz grades are as follows:");
		for(i = 0; i < quizLen; i++) {
			System.out.print(arrayToPrint[i] + " ");
		}
		System.out.print("\r\r");
	}
	
	public static int printAverage(int avgTotal, int avgCount) {
		//method receives total and count, then returns the average.
		int avgGrade = avgTotal/avgCount;
		System.out.println("Average grade: " + avgGrade+"\r");
		return avgGrade;
	}
	
	public static void printGrades(int[] gradeToPrint, int gradeLen) {
		//Method recieves an array and set length, and prints the array up to that defined length.
		int i;
		System.out.println("Quiz letter grades are as follows:");
		for(i = 0; i < gradeLen; i++) {
			System.out.print(determineLetterGrade(gradeToPrint[i]) + " ");
		}
		System.out.print("\r\r");
	}
	
	public static char determineLetterGrade(int tgtGrade) {
		//Assign a letter grade to the passed integer grade
		char letterGrade;
		letterGrade = 'A';
		if(tgtGrade < 90) letterGrade = 'B';
		if(tgtGrade < 80) letterGrade = 'C';
		if(tgtGrade < 70) letterGrade = 'D';
		if(tgtGrade < 60) letterGrade = 'F';
		//This probably isn't the most efficient way to do this, but starting at A and decrementing the grade
		//one by one feels oddly satisfying
		
		return letterGrade;
	}

}
