package FinalsActivity1;

import java.util.Arrays;

public class Group1 {
	public static void main(String[] args) {
		System.out.println("Prepared By: Arnonobal, Caguioa, Constantino, Maon, Mercado, Nacino, Paa, Reyes");
		
		int [] numbers = {45, 12, 89, 33, 67, 21, 90, 54};
		System.out.println("\nORIGINAL: " + Arrays.toString(numbers));
		
		int[] x = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(x);
		System.out.println("\nASCENDING: " + Arrays.toString(x));
		
		System.out.print("\nDESCENDING: ");
		for (int i = x.length - 1; i >= 0; i--) {
			System.out.print(x[i] + " ");
		}
		
		System.out.println("\n\nLOWEST: " + x[0]);
		System.out.println("\nHIGHEST: " + x[7]);
		
		int target = 67;
		int index = Arrays.binarySearch(x, target);
		System.out.println("\n" + target + " found at index " + index);
		
		System.out.println("\nARRAYS ARE EQUALS: " + Arrays.equals(numbers, x));
	}

}
