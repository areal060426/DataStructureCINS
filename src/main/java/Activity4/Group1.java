package Activity4;
/*
Group 1 — Student Ranking System

Difficulty: Hard

Create a system that asks for the number of students, their names, and grades. Sort the students from highest to lowest grade, while keeping the correct name connected to each grade.

Additional requirements:

Display original records.
Display sorted ranking.
Display highest and lowest grade.
Compute average grade.
Display students above average.
Handle students with equal grades.


*/

import java.util.*;
//EX. OF DATA STRUCTURE - a process of organizing data so it can be stored, accessed and modified efficiently
public class Group1 {
	public static void main(String[]args) {
		
		System.out.println("Prepared by: Arnonobal, Caguioa, Constantino, Maon, Mercado, Nacino, Paa, Reyes");
			
		Scanner sc = new Scanner(System.in); // Initialize Scanner
		System.out.println("Enter Number of Students: "); //Printed Names
		int n = sc.nextInt(); // Whatever value the user input, in to variable "n"
		sc.nextLine(); // since nextInt kasi nababasa yung input + space, pang tanggal lang ng leftover newline para mabasa nang maayos
		
		//Uses ARRAY to stores elements in an ordered sequence of the same type
		// dalawang 1D array - a single rows of boxes
		//parallel array structure - connected sila by their indexes [i]
		// [n] since its depends to the user's input
		// if n=5 then there will be 5 values stored
		String[] names = new String[n]; //stores names
		double[] grades = new double[n]; //stores grade
		
		//for loop to get each students information
		/* 1 if n = 2(number of students)
		 * 2 starting i(index) = 0
		 * 3 if i < n -> 2<2 loop ends
		 */
		for(int i = 0; i < n; i++) {
			System.out.println("Enter Name of Student " + (i + 1) + ": "); //displays no. of student
			names[i] = sc.nextLine();
			
			System.out.println("Enter Grade: ");
			grades[i] = sc.nextDouble(); //reads the inputted value, and stores it
			sc.nextLine();		
		}
			System.out.println("\n   ORIGINAL RECORDS   ");
			
			for (int i = 0; i < n; i++ ) {
				System.out.println(names[i] + "-" + grades[i]);
			} //Output: name-95.0
			
			//Calculates total and average
			//For loop that will add it over and over
			//Ex. 1st is 90 then 80 since loop, 90-170
			double total = 0;
			
			for (int i = 0; i < n; i++) {
				total += grades[i];
			}
			double average = total / n;
			
			System.out.println("\n   STUDENT AVERAGES   ");
			//Output: name-98.0
			for (int i = 0; i < n; i++) {
				double studentAve = grades[i] / 1; //unnecessary ang /1
				System.out.println(names[i] + "-" + studentAve); 
			}
			
			/*SORTING ALGORITHM: BUBBLE SORT
			 * sorting = arranging data in particular order
			 * descending order
			 * It repeatedly compares two adjacent elements and swaps them when they are in the wrong order. 
			 * During each pass, larger values gradually move toward the end of the array, 
			 * similar to bubbles rising to the surface.
			 */
			
			//nested loop
			for (int i = 0; i < n - 1; i++ ) { //controls how many full passes through the array happen.
				for (int j = 0; j < n - 1 - i ; j++) { //compare and switches values

					if(grades[j] < grades[j + 1]) { //if changed to ">" , it is ascending order
						double tempGrade = grades[j]; //temp- to act as a middle man to not lose the original value
						grades[j] = grades[j + 1];
						grades[j + 1] = tempGrade;

						String tempName = names[j];
						names[j] = names[j + 1];
						names[j + 1] = tempName;
					}
				}
			}

			//loop to print the sorted data in descending order, with tied grades sharing the same rank
			System.out.println("\n   STUDENT RANKING   ");

			//loop to print the sorted data in descending order
			for (int i = 0; i < n; i++) {
				System.out.println((i + 1) + ". " + names[i] + "-" + grades[i]);
			}//Output:1.meow-100.0 2.arf-99.0 
			
			System.out.println("\nHighest Grade: " + grades[0]);//since sorted, the highet will be on the 0 index
			System.out.println("Lowest Grade: " + grades[n - 1]); //while the last element will be the lowest
			
			System.out.println("\n   STUDENT ABOVE AVERAGE   ");
			//For each one, checks: is this student's grade higher than the class average (calculated earlier as total / n)?
			
			boolean above = false; //set as no student found above average

			for (int i = 0; i < n; i++) {
				if (grades[i] > average) {
					System.out.println(names[i] + "-" + grades[i]);
					above = true;
				}
			}
			 if (!above) {
				 System.out.println("No above average student found.");
			 }
			 
	}
}
