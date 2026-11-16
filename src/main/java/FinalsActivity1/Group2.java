package FinalsActivity1;

import java.util.Arrays;

public class Group2 {
    public static void main(String[] args) {
        //Group 2: Jasper James Docado, Japhette Louis Magpulong, Johnpaul Maglana, Shiaorene Lomyier Capuno
        //Willheime MarieJune Guillermo, Angelito Baltar, Erich Daler Custodio, Angel John Natividad

		//Create a Java program named GroupName using:
        int[] numbers = {45, 12, 89, 33, 67, 21, 90, 54};

        //1
        System.out.println("Original : " + Arrays.toString(numbers));   

        //2 3 4
        int numbers2 [] = Arrays.copyOf(numbers, numbers.length) ;
        Arrays.sort(numbers2);
        System.out.println("Ascending : " + Arrays.toString(numbers2));

        //5
        System.out.print("Descending : ");
        for (int i = numbers2.length - 1; i >= 0; i--) {
            System.out.print(numbers2[i] + " ");
        }
        System.out.println();

        //6
        int lowest = numbers2[0];
        int highest = numbers2[numbers.length - 1];

        System.out.println("Lowest: " + lowest );
        System.out.println("Highest: " + highest);


        //7
        int target = 67;
        int index = Arrays.binarySearch(numbers2, target);

        if (index >= 0) {
            System.out.println(target + " found at sorted index " + index);
        } else {
            System.out.println(target + " was not found.");
        }

        //8
        System.out.println("Arrays are equal: " + Arrays.equals(numbers,numbers2));
//			Your program must:
//			1.	Display the original array using Arrays.toString().
//			2.	Create an independent copy using Arrays.copyOf().
//			3.	Sort the copy in ascending order.
//			4.	Display the sorted copy.
//			5.	Display its values in descending order using a loop.
//			6.	Display the lowest and highest values.
//			7.	Search for 67 using Arrays.binarySearch().
//			8.	Print whether the original and sorted arrays are equal.
//			Expected output:
//			Original: [45, 12, 89, 33, 67, 21, 90, 54]
//			Ascending: [12, 21, 33, 45, 54, 67, 89, 90]
//			Descending: 90 89 67 54 45 33 21 12
//			Lowest: 12
//			Highest: 90
//			67 found at sorted index 5
//			Arrays are equal: false

    }

}

