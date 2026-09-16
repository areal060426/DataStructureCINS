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
            String[] studentNames;
            String[] subjectNames;
            double[][] arrOfGrades;
            double[] studentAve;
            int numOfSubjects;
            int numOfStudents;

            while (true) {
                try {
            System.out.print("Number of students: "  );
            numOfStudents = sc.nextInt();
            if (numOfStudents <= 0) {
                System.out.println("Please enter a number greater than 0.");
                continue;
            }

            break;

		        } catch (Exception e) {
		            System.out.println("Invalid input. Please enter a whole number.");
		            sc.nextLine();
		        }
		    }
            
 
            while (true) {
                try {
                    System.out.print("Number of subjects: ");
                    numOfSubjects = sc.nextInt();

                    if (numOfSubjects <= 0) {
                        System.out.println("Please enter a number greater than 0.");
                        continue;
                    }

                    break;

                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                    sc.nextLine();
                }
            }
  
            studentNames = new String[numOfStudents];
            subjectNames = new String[numOfSubjects];
            arrOfGrades = new double[numOfStudents][numOfSubjects];
            studentAve = new double[numOfStudents];
            
            sc.nextLine();
            for (int i = 0; i< numOfSubjects; i++) {
            
            	System.out.print("Enter name for subject " + (i + 1) + ": " );
            	subjectNames[i]= sc.nextLine();
            }
            
            System.out.println("-----------------");
            
            for (int students =0; students < numOfStudents; students++) {
            	System.out.print("Enter Student " + (students + 1) + " Name: ");
            	studentNames[students] = sc.nextLine();
            	System.out.println(studentNames[students] + "'s Grades ");           	
            	for (int subjects = 0; subjects < numOfSubjects; subjects++) {
            		
            		
            		 while (true) {
                         try {
 	
            		System.out.print("Enter grade for " + subjectNames[subjects] + ": ");
            		arrOfGrades[students][subjects] = sc.nextDouble();		
            		
            		  if (arrOfGrades[students][subjects] < 0 || arrOfGrades[students][subjects] > 100) {
                          System.out.println(
                                  "Invalid grade. Please enter a grade from 0 to 100.");
                      } else {
                          arrOfGrades[students][subjects] = arrOfGrades[students][subjects];
                          studentAve[students] += arrOfGrades[students][subjects];
                          break;
                      }

                  } catch (Exception e) {
                      System.out.println(
                              "Invalid input. Please enter a numeric grade.");
                      sc.nextLine();
                  }
              }
            	
            	
            	}
            	
            	sc.nextLine();  
            	System.out.println();
            }
            
            
            
            
         System.out.println("\n================ GRADE REPORT ================");

         System.out.print("Names\t");

         for (int subjects = 0; subjects < subjectNames.length; subjects++) {
             System.out.print(subjectNames[subjects] + "\t");
         }
        
         System.out.println("Average");
         for (int students = 0; students < arrOfGrades.length; students++) {
        	
        	
        	System.out.print(studentNames[students] + "\t");
   	
        	for (int subjects = 0; subjects < arrOfGrades[students].length; subjects++) {
                System.out.print(arrOfGrades[students][subjects] + " \t");             
            }
        	System.out.print((studentAve[students]/numOfSubjects));
            System.out.println();
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
  
     
	     System.out.println("\nSUBJECT'S HIGHEST AND LOWEST");
	     for (int j = 0; j < numOfSubjects; j++) {
	     System.out.printf(subjectNames[j] + "'s Highest: %.2f | Lowest: %.2f\n",
	    	 highestPerSubject[j], lowestPerSubject[j]);    
	     }
     
	     
	     //SORTED BY AVERAGE
	     System.out.println("\nSTUDENT RANKING");
	     for (int i = 0; i < numOfStudents - 1; i++) { 
	    	 for (int j = 0; j < numOfStudents - i - 1; j++) { 
	    		 if (studentAve[j] < studentAve[j + 1]) { 
	    			 double tempAve = studentAve[j]; 
	    			 studentAve[j] = studentAve[j + 1]; 
	    			 studentAve[j + 1] = tempAve; 
	    			 String tempName = studentNames[j]; 
	    			 studentNames[j] = studentNames[j + 1]; 
	    			 studentNames[j + 1] = tempName; 
	    			 double[] tempGrades = arrOfGrades[j]; 
	    			 arrOfGrades[j] = arrOfGrades[j + 1]; 
	    			 arrOfGrades[j + 1] = tempGrades; 
	    			 } 
	    		 } 
	    	 }
	     
	     for (int i = 0; i < numOfStudents; i++) {
	    	 System.out.printf("Top %d | %s | Average: %.2f\n", i + 1, studentNames[i], (studentAve[i]/numOfSubjects)); 
	    	 }
     
	     
	     System.out.println("\nFAILED STUDENTS");
	        boolean someoneFailed = false;

	        for (int students = 0; students < numOfStudents; students++) {
	            for (int subject = 0; subject < numOfSubjects; subject++) {

	                if (arrOfGrades[students][subject] < 75.0) {
	                    System.out.println(studentNames[students] + " failed in " + subjectNames[subject] + " (" + arrOfGrades[students][subject] + ")");
	                    someoneFailed = true;   
	                }
	                
	            }
	        }

	        if (!someoneFailed) {
	            System.out.println("No students failed!");
	        }
    sc.close();
   }
}
          
            
            
            
        
        
        

