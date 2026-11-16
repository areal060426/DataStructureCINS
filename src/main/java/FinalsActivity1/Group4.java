package FinalsActivity1;
import java.util.Arrays;

public class Group4 {

	public static void main(String[] args) {
		 // Group 4 :
		//De Leon, Dimailig, Estores, Fernandez, Lagrada, Linatoc, Pana, Santuele

	
				//1
				int[] num = {45, 12, 89, 33, 67, 21, 90, 54};
				
				System.out.println("Original: " + Arrays.toString(num));
				
				//2
				System.out.print("Copy: ");
				int[] copy = Arrays.copyOf(num,num.length);
				
				System.out.println(Arrays.toString(copy));
				
				//3
				System.out.print("Ascending: ");
				Arrays.sort(num);
				
				//4
				System.out.println(Arrays.toString(num));
				
				//5
				System.out.print("Descending: ");
				for(int i =num.length-1; i>=0; i--) {
					System.out.print(num[i] + ", ");
				}
				
			
				
				
				//6
				int highest = num[0];
				int lowest = num[0];
					for (int i = 0; i < num.length; i++) {
						if (num[i] > highest) {
						    highest = num[i];
			    }

						 if (num[i] < lowest) {
					        lowest = num[i];
			   }
					
						 
				}
					System.out.println();
					System.out.println("Highest value: " + highest);
					System.out.println("Lowest value: " + lowest);

				//7
					
					System.out.println();
					
			        Arrays.sort(num);

			        int target = 67;
			        int index = Arrays.binarySearch(num, target);

			        System.out.println("Sorted: " + Arrays.toString(num));

			        if (index >= 0) {
			            System.out.println(target + " found at index " + index);
			        } else {
			            System.out.println(target + " was not found.");
			     }
			        
			        //8 
			        System.out.println("Arrays are equal: " + Arrays.equals(copy,num));
					
		   }
	}
		
			
					
				