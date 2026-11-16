package FinalsActivity1;

import java.util.Arrays;

public class Group3 {

	public static void main(String[] args) {
		int[] numbers = {45, 12, 89, 33, 67, 21, 90, 54};
		int[] numbers1 = Arrays.copyOf(numbers, numbers.length);
		
		Arrays.sort(numbers1);
		System.out.println("1. Original: " + Arrays.toString(numbers));
		System.out.println("2-4. Ascending: " + Arrays.toString(numbers1));
		
		System.out.print("5. Descending: ");
		
		for (int i = numbers1.length - 1; i >= 0; i--) {
			System.out.print(numbers1[i] + " ");
		}
		
		System.out.println("\n6. Lowest: " + numbers1[7] + "\n   Highest: " + numbers1[0]);
		
		int target = 67;
        int index = Arrays.binarySearch(numbers1, target);

        if (index >= 0) {
            System.out.println("7. " + target + " found at index " + index);
        } else {
            System.out.println(target + " was not found.");
        }
        
        System.out.println("8. Arrays are equal: " + Arrays.equals(numbers, numbers1));
        
        String[] members = {"Velasco", "Cayosa", "Abuedo", "Inoy", "Pagulayan", "Gregorio", "Orille", "Macalla"};
        
        Arrays.sort(members);
        System.out.println("\nGroup 3 Members: " + Arrays.toString(members));
    }

}
