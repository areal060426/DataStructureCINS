package Activity3;
import java.util.Scanner;

//MEMBERS: Arnonobalch, Caguioach, Constantinoch, Maonch, Mercadoch, Nacinoch, Paach, Reyesch
public class Group1 {
    public static void main(String[] args) {
    	
    	System.out.println("Prepared By: Arnonobal, Caguioa, Constantino, Maon, "
    			+ "Mercado, Nacino, Paa, Reyes");

        Scanner scanner = new Scanner(System.in);

        int[][] grades = new int[5][4];

        String[] subjects = {"Java", "Database", "Networking", "Forensics"};

        for (int i = 0; i < grades.length; i++) {
            System.out.println("\nEnter grades for Student " + (i + 1));

            for (int j = 0; j < grades[i].length; j++) {
                System.out.print(subjects[j] + ": ");
                grades[i][j] = scanner.nextInt();
            }
        }

        System.out.println("\n\nStudent Grade Matrix");
        System.out.println();
        System.out.printf("%-12s", "Student");

        for (String subject : subjects) {
            System.out.printf("%-12s", subject);
        }

        System.out.println();

        for (int i = 0; i < grades.length; i++) {
            System.out.printf("%-12s", "Student " + (i + 1));

            for (int j = 0; j < grades[i].length; j++) {
                System.out.printf("%-12d", grades[i][j]);
            }

            System.out.println();
        }

        System.out.println("\nStudent Averages:");

        double highestAverage = 0;
        int highestAverageStudent = 0;

        for (int i = 0; i < grades.length; i++) {
        	int sum = 0;
        	for (int j = 0; j < grades[i].length; j++) {
                sum += grades[i][j];
            }

            double average = (double) sum / grades[i].length;

            System.out.printf("Student %d: %.2f%n", i + 1, average);

            if (average > highestAverage) {
                highestAverage = average;
                highestAverageStudent = i;
            }
        }

        System.out.println("\nStudent with the Highest Average:");
        System.out.printf("Student %d with an average of %.2f%n", highestAverageStudent + 1, highestAverage);

        int highestGrade = grades[0][0];

        for (int i = 0; i < grades.length; i++) {

            for (int j = 0; j < grades[i].length; j++) {

                if (grades[i][j] > highestGrade) {
                    highestGrade = grades[i][j];
                }
            }
        }

        System.out.println("\nHighest Grade in the Entire Array: " + highestGrade);

    }
}