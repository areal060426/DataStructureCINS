package Activity4;
import java.util.Scanner;
/*
 * Group 2 — Number Analyzer and Sorting Laboratory

The user enters 10–20 integers.

The program must:

Display original numbers.
Sort ascending using Bubble Sort.
Sort descending using Selection Sort.
Find largest and smallest.
Find second largest and second smallest.
Count even and odd numbers.
Count duplicate values.
Remove duplicates when displaying unique numbers.
Search for a value entered by the user.
The program must reject an array size lower than 10 or higher than 20.
*/


//Group 2: Jasper James Docado, Japhette Louis Magpulong, Johnpaul Maglana, Shiaorene Lomyier Capuno
//Willheime MarieJune Guillermo, Angelito Baltar, Erich Daler Custodio, Angel John Natividad
public class Group2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int count = 0;
        int numbers[] = new int[20];
        String input = "";

        System.out.println("Enter 10 - 20 numbers: ");

        while (count < 20) {
            System.out.print("Enter " + (count + 1) + " number: ");
            if (sc.hasNextInt()) {
                numbers[count] = sc.nextInt();
                System.out.print("Are you finished(y/n): ");
                input = sc.next();

                if (input.equalsIgnoreCase("y")) {
                    if (count < 9) {
                        System.out.println("Enter at least 10 numbers");
                    } else {
                        break;
                    }
                }
                count++;
            } else {
                System.out.println("Invalid Integer");
                sc.next();
            }
        }

        int size = count + 1;

        //Assign old array to new array
        int newNumbers[] = new int[size];
        for (int i = 0; i < size; i++) {
            newNumbers[i] = numbers[i];
        }

        System.out.println("The original numbers: ");
        for (int number : newNumbers) {
            System.out.print(number + ", ");
        }
        System.out.println(" ");

        //Bubble Sort
        for (int i = 0; i < newNumbers.length - 1; i++) {
            for (int j = 0; j < newNumbers.length - i - 1; j++) {
                if (newNumbers[j] > newNumbers[j + 1]) {
                    int temp = newNumbers[j];
                    newNumbers[j] = newNumbers[j + 1];
                    newNumbers[j + 1] = temp;
                }
            }
        }
        System.out.println("Bubble Sorted Array: ");
        for (int number : newNumbers) {
            System.out.print(number + ", ");
        }
        System.out.println(" ");

        int high = newNumbers[size - 1];
        int low = newNumbers[0];
        int secondLow = newNumbers[1];
        int secondHigh = newNumbers[size - 2];

        //Print
        System.out.println("Highest number in array: " + high);
        System.out.println("Lowest number in array: " + low);
        System.out.println("Second Highest number in array: " + secondHigh);
        System.out.println("Second Lowest number in array: " + secondLow);

        //Selection Sort
        for (int i = 0; i < newNumbers.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < newNumbers.length; j++) {
                if (newNumbers[max] < newNumbers[j]) {
                    max = j;
                }
            }
            int temp = newNumbers[i];
            newNumbers[i] = newNumbers[max];
            newNumbers[max] = temp;
        }

        System.out.println("Selection Sorted Array: ");
        for (int number : newNumbers) {
            System.out.print(number + ", ");
        }
        System.out.println(" ");


        //Counting Odd And Even Numbers
        int evenCount = 0;
        int oddCount = 0;
        for (int i = 0; i < newNumbers.length; i++) {
            if (newNumbers[i] % 2 == 0) {
                evenCount ++;
            } else {
                oddCount++;
            }
        }
        System.out.println("Even numbers in array: " + evenCount);
        System.out.println("Odd numbers in array: " + oddCount);

        for (int i = 0; i < newNumbers.length - 1; i++) {
            for (int j = 0; j < newNumbers.length - i - 1; j++) {
                if (newNumbers[j] > newNumbers[j + 1]) {
                    int temp = newNumbers[j];
                    newNumbers[j] = newNumbers[j + 1];
                    newNumbers[j + 1] = temp;
                }
            }
        }
        //Counting duplicates and displaying unique numbers
        int duplicateCount = 0;
        System.out.println("Unique Numbers in Array: ");
        for (int i = 0; i< newNumbers.length; i++) {
            if (i == 0 || newNumbers[i] != newNumbers[i-1]) {
                System.out.print(newNumbers[i] + ", ");
            }
               else {
                    duplicateCount++;
            }
        }

        System.out.println(" ");
        System.out.println("Number of duplicates in array: " + duplicateCount);




        //Search
        Scanner search = new Scanner(System.in);
        boolean isFound = false;
        System.out.print("Enter a number to search: ");
        int target = search.nextInt();

        for (int i = 0; i < newNumbers.length; i++) {
            if (newNumbers[i] == target) {
                System.out.println(newNumbers[i] + " exists");
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("That doesnt exist");
        }
    }
}


