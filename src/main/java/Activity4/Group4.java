package Activity4;
/*
 * Group 4 — 2D Array Grade Management System


Use a 2D array.

Example:

double[][] grades;

Rows = students
Columns = subjects

Example:

              Java   Database   Networking
Student 1      90       85          88
Student 2      75       92          80
Student 3      88       91          94

The program must:

Ask number of students.
Ask number of subjects.
Input student names.
Input grades.
Compute average of every student.
Compute average of every subject.
Sort students based on overall average.
Determine Top 1, Top 2, and Top 3.
Determine highest grade per subject.
Determine lowest grade per subject.
Display students who failed any subject.

*/
import java.util.Scanner;
public class Group4 {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);


            System.out.print("Number of students: "  );
            int numOfStudents = sc.nextInt();

            System.out.print("Number of subjects: "  );
            int numOfSubjects = sc.nextInt();

  
            String[] studentNames = new String[numOfStudents];
            String[] subjectNames = new String[numOfSubjects];
            double[][] arrOfGrades = new double[numOfStudents][numOfSubjects];
            double[] studentAve = new double[numOfStudents];
            
            sc.nextLine();
            for (int i = 0; i< numOfSubjects; i++) {
            
            	System.out.print("Enter name for subject " + (i + 1) + ": \n" );
            	subjectNames[i]= sc.nextLine();
            }
            
            
            for (int i =0; i < numOfStudents; i++) {
            	System.out.print("Enter Student " + (i + 1) + " Name: ");
            	studentNames[i] = sc.nextLine();
            	
            	
            	System.out.print("Enter " + studentNames[i] + "'s Grades \n");
            	for (int j = 0; j < numOfSubjects; j++) {
            		System.out.println("Enter grade for " + subjectNames[j] + ": ");
            		arrOfGrades[i][j] = sc.nextDouble();		
            		studentAve[i] += arrOfGrades[i][j]; 
            	}
            	
            	sc.nextLine();
            	System.out.println(studentNames[i] + "'s Average: " + (studentAve[i]/numOfSubjects));

            	
            }
            
            double [] subjectAverage = new double[numOfSubjects];
            double [] highestPerSubject = new double[numOfSubjects];
            double [] lowestPerSubject = new double[numOfSubjects];
            
            for (int j = 0; j < numOfSubjects; j++) {
            	double sum = 0;
            	double max = arrOfGrades[0][j];
            	double min = arrOfGrades[0][j];
            	
            for (int i = 0; i < numOfStudents; i++) {
            	sum += arrOfGrades [i][j];
            	if (arrOfGrades[i][j] > max) max = arrOfGrades[i][j];
            	if (arrOfGrades[i][j] < min) min = arrOfGrades[i][j];
            }
            
            subjectAverage[j] = sum / numOfStudents;
            highestPerSubject[j] = max;
            lowestPerSubject[j] = min;
            
            }
           
            System.out.println("\nSUBJECT AVERAGE");
            for (int j = 0; j < numOfSubjects; j++) {
           System.out.printf("Subject %s | Average: %.2f\n",
           subjectNames[j], subjectAverage[j]);
           
            }
     for (int i = 0; i < numOfStudents - 1; i++) {
    	 for (int j = 0;  j < numOfStudents - 1 - i; j++) {
    		 if(studentAve[j] > studentAve[j + 1]) {
    			 double tempAvg = studentAve[j];
    			 studentAve[j]
    		 }
    	 }
     
          
          
     }
     
     System.out.println("\nSUBJECT'S HIGHEST AND LOWEST");
     for (int j = 0; j < numOfSubjects; j++) {
     System.out.printf(subjectNames[j] + "'s Highest: %.2f | Lowest: %.2f\n",
    	 highestPerSubject[j], lowestPerSubject[j]);    
     }
     
     System.out.println("\n\t STUDENT RANKING");
     
        
   }
}
          
            
            
            
        
        
        

