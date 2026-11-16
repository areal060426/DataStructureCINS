package FinalsActivity1;

import java.util.Arrays;

public class Group5 {

	public static void main(String[] args) {
		int[] numbers = {45, 12, 89, 33, 67, 21, 90, 54};
		
		//1
		System.out.println("ORIGINAL: " + Arrays.toString(numbers));
		
		//2
		int[] numbersCopy = Arrays.copyOf(numbers, numbers.length);
		
		//3
		Arrays.sort(numbersCopy);
		
		//4
		System.out.print("ASCENDING: " + Arrays.toString(numbersCopy));
		
		//5
		System.out.println();
		System.out.print("DESCENDING: [");
		for(int i = numbersCopy.length-1; i> -1; i-- ) {
			if(i == 0) {
				System.out.print(numbersCopy[i]);
			}
			else{
				System.out.print(numbersCopy[i] + ", ");
			}
		}
		System.out.print("]");
		
		//6
		int highest = numbersCopy[numbers.length-1];
		int lowest = numbersCopy[0];
		
		System.out.println("\nLOWEST: " + lowest);
		System.out.println("HIGHEST: " + highest);
		
		//7
		int target = 67;
		int index = Arrays.binarySearch(numbersCopy, target);;
		if(index == -1) {
			System.out.println("Number " + target + " not found");
		}
		else {
			System.out.println(target + " found at index " + index);
		}
		
		//8
		System.out.println("Arrays are equal: " + Arrays.equals(numbers, numbersCopy));
		
		System.out.println("\nGroup - 5 | II-CINS \nMembers:");
		System.out.println("Rosalejos, Jomel\nPastor, Dhylan Jaymari\nSantos, Rhine Miguel\nRasonable, John Carl");
		System.out.println("Banalo, Lance\nBalla, Pauline\nSaliente, Roseann\nApellanes, Sandro");
	}

}
