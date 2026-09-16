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

public class Group1 {
	public static void main(String[]args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Number of Students: ");
		int n = sc.nextInt();
		sc.nextLine();
		
		String[] names = new String[n];
		double[] grades = new double[n];
		
		for(int i = 0; i < n; i++) {
			System.out.println("Enter Name of Student: " + (i + 1) + ": ");
			names[i] = sc.nextLine();
			
			System.out.println("Enter Grade: ");
			grades[i] = sc.nextDouble();
			sc.nextLine();		
			
		}
			System.out.println("\n   ORIGINAL RECORDS   ");

			for (int i = 0; i < n; i++ ) {
				System.out.println(names[i] + "-" + grades[i]);
			}
			double total = 0;
			
			for (int i = 0; i < n; i++) {
				total += grades[i];
			}
			double average = total / n;
			
			for (int i = 0; i < n - 1; i++ ) {
				for (int j = 0; j < n - 1 - i ; j++) {
					
					if(grades[j] < grades[j + 1]) {
						double tempGrade = grades[j];
						grades[j] = grades[j + 1];
						grades[j + 1] = tempGrade;
						
						String tempName = names[j];
						names[j] = names[j + 1];
						names[j + 1] = tempName;
					}
				}
			}
			System.out.println("\n   STUDENT RANKING   ");
			
			for (int i = 0; i < n; i++) {
				System.out.println((i + 1) + ". " + names[i] + "-" + grades[i]);
			}
			System.out.println("\nHighest Grade: " + grades[0]);
			System.out.println("Lowest Grade: " + grades[n - 1]);
			
			System.out.println("Average Grade: " + average);
			System.out.println("\n   STUDENT ABOVE AVERAGE   ");
			
			boolean above = false;
			
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
