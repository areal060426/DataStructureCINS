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


            System.out.println("Number of students: "  );
            int numberOfStudents = sc.nextInt();

            System.out.println("Number of subjects: "  );
            int numberOfSubjects = sc.nextInt();

            System.out.println("Name: "  );
            String name = sc.nextLine();

            System.out.println("Grades: "  );
            int grades = sc.nextInt();

            double[][] arrOfGrades = new double[numberOfStudents][numberOfSubjects];
        }
}
