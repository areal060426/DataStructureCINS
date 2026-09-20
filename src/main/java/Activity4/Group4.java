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


//Group 4: Vhenz Santuele, Roel Ernest Fernandez, Justine Lagrada, Daryl Paña 
//Lira Linatoc, Clare Margaret De Leon, Glayza Maxinne Dimailig, Angelica Estores
import java.util.Scanner;
public class Group4 {
	
	static Scanner sc = new Scanner(System.in);	
	static int numOfSubjects;
    static int numOfStudents;    
    static double[][] grades;    
    static String[] studentNames;
    static String[] subjectNames;
    static double[] studentAve;
    	
    public static void main(String[] args){
    	
    	enterNumOfStudentsAndSubjects();  
    	inputSubjectNames();
    	inputNamesAndGrades();	
    	displayStudentGradesAndAverage();
    	displaySubjectDetails();   	
    	sortStudentsByAverage();
    	displayFailedSubjects();
    	
    	sc.close();
   }
              
   private static void enterNumOfStudentsAndSubjects() {
	   while (true) {
		   try {
			   System.out.print("Enter number of students: "  );
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
			   System.out.print("Enter number of subjects: ");
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
       grades = new double[numOfStudents][numOfSubjects];
       studentAve = new double[numOfStudents];
             
   }
           
   private static void inputSubjectNames() {   
	   System.out.println("-----------------");
	   sc.nextLine();
	   for (int subjects = 0; subjects< numOfSubjects; subjects++) {         
    	   System.out.print("Enter name for subject " + (subjects + 1) + ": " );
    	   subjectNames[subjects]= sc.nextLine();
       }
       System.out.println("-----------------");
   }
  
   private static void inputNamesAndGrades() {   
       for (int students = 0; students < numOfStudents; students++) {
    	   System.out.print("Enter Student " + (students + 1) + " Name: ");
    	   studentNames[students] = sc.nextLine();
    	   System.out.println(studentNames[students] + "'s Grades ");           	
    	   for (int subjects = 0; subjects < numOfSubjects; subjects++) {
    		   while (true) {
    			   try {  
    				   System.out.print("Enter grade for " + subjectNames[subjects] + ": ");
    				   grades[students][subjects] = sc.nextDouble();		
    				  
    				   if (grades[students][subjects] < 0 || grades[students][subjects] > 100) {
    					   System.out.println("Invalid grade. Please enter a grade from 0 to 100.");
    					   } else {
    						   grades[students][subjects] = grades[students][subjects]; 
    						   computeAverage(students, subjects);    					
    						   break;
    						   }
    				   } catch (Exception e) {
    					   System.out.println("Invalid input. Please enter a numeric grade.");
    					   sc.nextLine();
    					   }
    			   }
    		   }
       	sc.nextLine();  
       	System.out.println("-----------------");   	
       }  
       System.out.println();
   }    
    
   private static void computeAverage(int students, int subjects) {		   
	   studentAve[students] += (grades[students][subjects])/numOfSubjects;
   }
   
   private static void displayStudentGradesAndAverage() {
	   System.out.print("Names\t\t");
       for (int subjects = 0; subjects < subjectNames.length; subjects++) {
           System.out.print(subjectNames[subjects] + "\t\t");
       }
       
       System.out.println("| Average");
       System.out.println("------------------------------------------------------------------------------------------------");
             
       for (int students = 0; students < grades.length; students++) {      	
      	System.out.print(studentNames[students] + "\t\t");
      	
      	for (int subjects = 0; subjects < grades[students].length; subjects++) {
              System.out.printf("%.2f",grades[students][subjects]);
              System.out.print("\t\t");
          }
      	
      	System.out.printf("| %.2f",(studentAve[students]));
      	System.out.println();
       }
       
       System.out.println("------------------------------------------------------------------------------------------------");
   }
 
   private static void sortStudentsByAverage() {
	     System.out.println();
	     System.out.println("SORTED STUDENTS BY AVERAGE");
	     System.out.println("-----------------");
	     for (int i = 0; i < numOfStudents - 1; i++) { 
	    	 for (int j = 0; j < numOfStudents - i - 1; j++) { 
	    		 if (studentAve[j] < studentAve[j + 1]) { 
	    			 double tempAve = studentAve[j]; 
	    			 studentAve[j] = studentAve[j + 1]; 
	    			 studentAve[j + 1] = tempAve;
	    			 
	    			 String tempName = studentNames[j]; 
	    			 studentNames[j] = studentNames[j + 1]; 
	    			 studentNames[j + 1] = tempName;
	    			 
	    			 double[] tempGrades = grades[j]; 
	    			 grades[j] = grades[j + 1]; 
	    			 grades[j + 1] = tempGrades; 
	    			 } 
	    		 } 
	    	 }
	     
	     displayStudentGradesAndAverage();
	     
	    
	     System.out.println("\nTOP 3 STUDENTS");
	     System.out.println("-----------------");
	     for (int students = 0; students < numOfStudents; students++) {
	    	 if (students < 3) {
	    		 System.out.printf("Top %d | %s | Average: %.2f\n", students + 1, studentNames[students], studentAve[students]);
	    		 }
	    	 }
   }
    
   private static void displaySubjectDetails() {	   
	   double [] subjectAve = new double[numOfSubjects];
	   double [] highestPerSubject = new double[numOfSubjects];
	   double [] lowestPerSubject = new double[numOfSubjects]; 
	   
	   
	   for (int subjects = 0; subjects < numOfSubjects; subjects++) {  
		   double max = grades[0][subjects];
		   double min = grades[0][subjects];
		   
			for (int students = 0; students < numOfStudents; students++) {
				subjectAve[subjects] += (grades[students][subjects])/numOfSubjects;
				
				if (grades[students][subjects] > max) {
					max = grades[students][subjects];
				}
				
				if (grades[students][subjects] < min) {
					min = grades[students][subjects];
				}
			}
			highestPerSubject[subjects] = max;
			lowestPerSubject[subjects] = min;	
	   }
	   
	   
       System.out.println("\nSUBJECT AVERAGE");
       System.out.println("-----------------");
       for (int subjects = 0; subjects < numOfSubjects; subjects++) {
	         System.out.printf("%s Average: %.2f\n",
	         subjectNames[subjects], subjectAve[subjects]);
       }

	     System.out.println("\nSUBJECT'S HIGHEST AND LOWEST");
	     System.out.println("-----------------");
	     for (int subjects = 0; subjects < numOfSubjects; subjects++) {
	    	 System.out.printf(subjectNames[subjects] + "'s Highest: %.2f | Lowest: %.2f\n",
	    	 highestPerSubject[subjects], lowestPerSubject[subjects]);    
	     }
   
   }
   
   private static void displayFailedSubjects() {	   
	   System.out.println("\nSTUDENTS WITH FAILED SUBJECTS");
	   System.out.println("-----------------");
       
	   boolean someoneFailed = false;

       for (int students = 0; students < numOfStudents; students++) {
           for (int subject = 0; subject < numOfSubjects; subject++) {

               if (grades[students][subject] < 75.0) {
                   System.out.println(studentNames[students] + " failed in " + subjectNames[subject] + " (" + grades[students][subject] + ")");
                   someoneFailed = true;   
               }
               
           }
       }

       if (!someoneFailed) {
           System.out.println("No students failed in any subject!");
       }
   }
   
   
}
          


            
            
        
        
        

