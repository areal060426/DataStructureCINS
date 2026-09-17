package Activity3;
// Group members: Apellanes, Balla, Banalo, Pastor, Rasonable, Rosalejos, Saliente, Santos

/*Tasks:
1.	Store the grades using a 3D array. 
2.	Display all student grades grouped by course. 
3.	Calculate each student's average. 
4.	Calculate the average grade of each course. 
5.	Find the highest grade. 
6.	Determine which course has the higher overall average. 
Challenge: Find the top-performing student across both courses.**/


public class Group5 {
	public static void main(String[] args) {
		
			int [][][] grades = {
				{ 
					{90, 85, 88}, 
			        {82, 87, 91}, 
			        {89, 92, 86}, 
			        {95, 90, 93}  
			    },
			    {
			        {85, 88, 90},
			        {91, 86, 89},
			        {87, 90, 92},
			        {88, 84, 91}
				}
			};
			
			String[] courses = {"BSIT", "BSOA"};
			
			double[][] average = new double[2][4];//para mag store ng average
			
			System.out.print("ALL STUDENT RECORD");
			for (int course = 0; course < grades.length; course++) {
				System.out.println("\nCourse: " + courses[course]);

			for (int student = 0; student < grades[course].length; student++) {
				System.out.print("Student " + (student + 1) + ": ");

			for (int subject = 0; subject < grades[course][student].length; subject++) {
			    System.out.print(grades[course][student][subject] + " ");
			        }

			        System.out.println();
			    }
			}
			
			 System.out.print("\nEACH STUDENTS' AVERAGE");

		        for (int course = 0; course < grades.length; course++) {
		            System.out.println("\nCourse: " + courses[course]);

		        for (int student = 0; student < grades[course].length; student++) {
		                int total = 0;
		                
		        for (int subject = 0; subject < grades[course][student].length; subject++) {
		                    total += grades[course][student][subject];
		                }
		                average[course][student] =
		                        (double) total / grades[course][student].length;

		                System.out.println("Student " + (student + 1)
		                        + " Average: " + average[course][student]);
		            }
		        }

		        // Finds the highest grade
		        int highestGrade = grades[0][0][0]; 
		        int highestCourse = 0;
		        int highestStudent = 0;

		        for (int course = 0; course < grades.length; course++) {
		        for (int student = 0; student < grades[course].length; student++) {
		        for (int subject = 0; subject < grades[course][student].length; subject++) {

		                    if (grades[course][student][subject] > highestGrade) { 
		                        highestGrade = grades[course][student][subject];
		                        highestCourse = course;
		                        highestStudent = student;
		                    }
		                }
		            }
		        }

		        System.out.println("\nHighest Grade: " + highestGrade);
		        System.out.println("Course: " + courses[highestCourse]);
		        System.out.println("Student No.: " + (highestStudent + 1) + "\n");
		
	        	//Determine which course has the higher overall average.
		        courseWithHighestOverallAve(grades);
		
		        // condition to find the top performing student
		        double highestAverage = average[0][0];
		        int topStudent = 0;
		        int topCourse = 0;

		        for (int course = 0; course < average.length; course++) {
		        for (int student = 0; student < average[course].length; student++) {
		        	
		                if (average[course][student] > highestAverage) { 

		                    highestAverage = average[course][student];
		                    topStudent = student;
		                    topCourse = course;
		                }
		            }
		        }

		        System.out.println("\nTop Performing Student:");
		        System.out.println("Course: " + courses[topCourse]);
		        System.out.println("Student no.: " + (topStudent + 1));
		        System.out.println("Average: " + highestAverage);
		        
		  
		        String[] group5 = {"Apellanes", "Balla", "Banalo", "Pastor", "Rasonable", "Rosalejos", "Saliente", "Santos"};       
		        System.out.println("\nSUBMITTED BY:");
		        for (String name : group5) {
		            System.out.println(name);
		        }
		    }
	
	public static void courseWithHighestOverallAve(int [][][] grades){
		double bsitSum = 0;
        double bsoaSum = 0;
        double bsitTotalNumberOfCourse = 0;
        double bsoaTotalNumberOfCourse = 0;
        
        for(int i = 0; i<grades.length; i++){
            for(int j = 0; j<grades[i].length; j++){
                for(int k = 0; k<grades[i][j].length; k++){
                    
                    if(i == 0){
                        bsitSum += grades[i][j][k];
                        bsitTotalNumberOfCourse += 1;
                    }
                    else{
                        bsoaSum += grades[i][j][k];
                        bsoaTotalNumberOfCourse += 1;
                    }
                }
            }
        }
        
        double bsitAverage = bsitSum/bsitTotalNumberOfCourse;
        double bsoaAverage = bsoaSum/bsoaTotalNumberOfCourse;
        
        if(bsitAverage > bsoaAverage){
            System.out.println("Course with a higher Overall Average: BSIT");
            System.out.println("Total Average: " + bsitAverage);
        }
        else if (bsitAverage < bsoaAverage){
            System.out.println("Course with a higher Overall Average: BSOA");
            System.out.println("Total Average: " + bsoaAverage);
        }
        else{
            System.out.println("Both courses have the same overall average.");
            System.out.println("Total Average: " + bsitAverage);
        }
    }
	
}
