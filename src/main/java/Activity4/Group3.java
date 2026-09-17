package Activity4;
import java.util.*;

// Group Members: Abuedo, Cayosa, Grregorio, Inoy, Macalla, Orille, Pagulayan (ABSENT), Velasco

/* Group 3 — Employee Salary Sorting System

Create parallel arrays:

String[] employeeName;
int[] employeeID;
double[] salary;

Ask the user to enter employee information.

The program must provide this menu:

==============================
EMPLOYEE MANAGEMENT SYSTEM
==============================
1. Display Employees
2. Sort by Salary - Highest to Lowest
3. Sort by Salary - Lowest to Highest
4. Sort by Employee ID
5. Search Employee ID
6. Display Salary Statistics
7. Exit
==============================

Students must keep all employee information connected when sorting.*/

public class Group3 {
	public static void main(String[] args) {
       Scanner input = new Scanner(System.in);

       System.out.println("Enter number of Employees: ");
       int size = input.nextInt();
       input.nextLine();
       
       // parallel array
       String[] employeeName = new String[size];
       int[] employeeID = new int[size];
       double[] salary = new double[size];
       
       for (int i=0; i<size; i++) {
    	   System.out.println("Employee Number #" + (i+1));
    	   
    	System.out.println("Enter Employee Name: ");
    	employeeName[i] = input.nextLine();
    	
    	System.out.println("Enter Employee ID: ");
    	employeeID[i] = input.nextInt();
    	
    	System.out.println("Enter Employee Salary: ");
    	salary[i] = input.nextDouble();
    	input.nextLine();
       }
       
       int choice;
       
       	//the UI Navigation System using SCANNER
       do {
           System.out.println("\n==============================");
           System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
           System.out.println("==============================");
           System.out.println("1. Display Employees");
           System.out.println("2. Sort by Salary - Highest to Lowest");
           System.out.println("3. Sort by Salary - Lowest to Highest");
           System.out.println("4. Sort by Employee ID");
           System.out.println("5. Search Employee ID");
           System.out.println("6. Display Salary Statistics");
           System.out.println("7. Exit");
           System.out.println("==============================");

           System.out.print("Enter choice: ");	
           
           		if (!input.hasNextInt()) {
           			System.out.println("Invalid input. Please enter a number from 1 to 7.");
           			input.nextLine();
           			choice = 0;
           			continue;
           		}

           		choice = input.nextInt();
           		input.nextLine();
           		
           switch (choice) {

               case 1:
                   displayEmployees(employeeName, employeeID, salary);
                   break;

               case 2:
                   sortSalaryHighest(employeeName, employeeID, salary);
                   System.out.println("\nEmployees sorted by salary: Highest to Lowest");
                   displayEmployees(employeeName, employeeID, salary);
                   break;

               case 3:
                   sortSalaryLowest(employeeName, employeeID, salary);
                   System.out.println("\nEmployees sorted by salary: Lowest to Highest");
                   displayEmployees(employeeName, employeeID, salary);
                   break;

               case 4:
                   sortEmployeeID(employeeName, employeeID, salary);
                   System.out.println("\nEmployees sorted by Employee ID");
                   displayEmployees(employeeName, employeeID, salary);
                   break;

               case 5:
                   searchEmployeeID(employeeName, employeeID, salary, input);
                   break;

               case 6:
                   displayStatistics(employeeName, salary);
                   break;

               case 7:
                   System.out.println("\nExiting program...");
                   break;

               default:
                   System.out.println("\nInvalid choice!");

           }

       } while (choice != 7);

       input.close();
	}
	
	public static void displayEmployees(String[] employeeName, int[] employeeID, double[] salary) {
		
		System.out.println("\n==============================================");
        System.out.printf("%-20s %-12s %-12s%n", "NAME", "EMPLOYEE ID", "SALARY");
		
        System.out.println("==============================================");
		for (int i=0; i<employeeName.length; i++) {
		System.out.printf("%-20s %-12d %.2f%n", employeeName[i], employeeID[i], salary[i]);
	}
	
	System.out.println("\n==============================================");
	}
	
	// SORT SALARY HIGH TO LOW
    public static void sortSalaryHighest(String[] employeeName, int[] employeeID, double[] salary) {

        for (int i = 0; i < salary.length - 1; i++) {

            for (int j = 0; j < salary.length - 1 - i; j++) {

                if (salary[j] < salary[j + 1]) {

                    // Swap salary
                    double tempSalary = salary[j];
                    salary[j] = salary[j + 1];
                    salary[j + 1] = tempSalary;

                    // Swap ID
                    int tempID = employeeID[j];
                    employeeID[j] = employeeID[j + 1];
                    employeeID[j + 1] = tempID;

                    // Swap name
                    String tempName = employeeName[j];
                    employeeName[j] = employeeName[j + 1];
                    employeeName[j + 1] = tempName;
                }
            }
        }
    }

    // SORT SALARY LOW TO HIGH
    public static void sortSalaryLowest(
            String[] employeeName,
            int[] employeeID,
            double[] salary) {

        for (int i = 0; i < salary.length - 1; i++) {

            for (int j = 0; j < salary.length - 1 - i; j++) {

                if (salary[j] > salary[j + 1]) {

                    // Swap salary
                    double tempSalary = salary[j];
                    salary[j] = salary[j + 1];
                    salary[j + 1] = tempSalary;

                    // Swap ID
                    int tempID = employeeID[j];
                    employeeID[j] = employeeID[j + 1];
                    employeeID[j + 1] = tempID;

                    // Swap name
                    String tempName = employeeName[j];
                    employeeName[j] = employeeName[j + 1];
                    employeeName[j + 1] = tempName;
                }
            }
        }
    }
	
    // SORT BY EMPLOYEE ID
    public static void sortEmployeeID(
            String[] employeeName,
            int[] employeeID,
            double[] salary) {

        for (int i = 0; i < employeeID.length - 1; i++) {

            for (int j = 0; j < employeeID.length - 1 - i; j++) {

                if (employeeID[j] > employeeID[j + 1]) {

                    // Swap ID
                    int tempID = employeeID[j];
                    employeeID[j] = employeeID[j + 1];
                    employeeID[j + 1] = tempID;

                    // Swap name
                    String tempName = employeeName[j];
                    employeeName[j] = employeeName[j + 1];
                    employeeName[j + 1] = tempName;

                    // Swap salary
                    double tempSalary = salary[j];
                    salary[j] = salary[j + 1];
                    salary[j + 1] = tempSalary;
                }
            }
        }
    }
 
    // SEARCH EMPLOYEE ID
   public static void searchEmployeeID(
           String[] employeeName,
           int[] employeeID,
           double[] salary,
           Scanner input) {

       System.out.print("\nEnter Employee ID to search: ");
       int searchID = input.nextInt();

       boolean found = false;

       for (int i = 0; i < employeeID.length; i++) {

           if (employeeID[i] == searchID) {

               System.out.println("\n=== EMPLOYEE FOUND ===");
               System.out.println("Name: " + employeeName[i]);
               System.out.println("Employee ID: " + employeeID[i]);
               System.out.printf("Salary: ₱%.2f%n", salary[i]);

               found = true;
               break;
           }
       }

       if (!found) {
           System.out.println("\nEmployee ID not found.");
       }
   }
   
   // SALARY STATISTICS
   
   public static void displayStatistics (String[] employeeName, double[] salary) {
	   
	   if (salary.length == 0) {
		   System.out.println("No employee data available.");
		   return;
	   }
	   
	   double highest = salary [0];
	   double lowest = salary [0];
	   double total = 0;
	   
	   int highestIndex = 0;
	   int lowestIndex = 0;
	   
	   for (int i=0; i<salary.length; i++) {
		   total += salary[i];
		   
		   if(salary[i] > highest) {
			   highest = salary[i];
			   highestIndex = i;
		   }
		   
		   if(salary[i] > lowest) {
			   lowest = salary[i];
			   lowestIndex = i;
		   }
	   }
	   
	   double average = total / salary.length;
	   
		System.out.println("\n==============================================");
		System.out.println("SALARY STATISTICS");
		
		System.out.println("==============================");

        System.out.printf("Highest Salary: %.2f (%s)%n",
                highest, employeeName[highestIndex]);

        System.out.printf("Lowest Salary: %.2f (%s)%n",
                lowest, employeeName[lowestIndex]);

        System.out.printf("Average Salary: %.2f%n", average);
        System.out.printf("Total Salary: %.2f%n", total);

        System.out.println("==============================");
    }

}