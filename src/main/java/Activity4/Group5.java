package Activity4;
// Group members: Apellanes, Balla, Banalo, Pastor, Rasonable, Rosalejos, Saliente, Santos
/*
 * Group 5 — Advanced Array Sorting and Search System

This group creates an interactive Sorting Laboratory.

The user enters numbers and can repeatedly perform operations.

=================================
ARRAY SORTING LABORATORY
=================================
1. Enter Array
2. Display Original Array
3. Bubble Sort
4. Selection Sort
5. Insertion Sort
6. Search Number
7. Find Duplicate Numbers
8. Find Missing Number
9. Reverse Array
10. Display Statistics
11. Exit
=================================

They must preserve the original array.

int[] original;
int[] working;

*/
import java.util.Scanner;

public class Group5 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int[] original = null;
        int[] working = null;

        int choice;

        do {

            System.out.println("\n===============================");
            System.out.println("ARRAY SORTING LABORATORY");
            System.out.println("===============================");
            System.out.println("1. Enter Array");
            System.out.println("2. Display Original Array");
            System.out.println("3. Bubble Sort");
            System.out.println("4. Selection Sort");
            System.out.println("5. Insertion Sort");
            System.out.println("6. Search Number");
            System.out.println("7. Find Duplicates");
            System.out.println("8. Find Missing Number");
            System.out.println("9. Reverse Array");
            System.out.println("10. Statistics");
            System.out.println("11. Exit");

            System.out.print("\nChoice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Array Size: ");
                    int size = scanner.nextInt();

                    original = new int[size];
                    working = new int[size];

                    for (int i = 0; i < size; i++) {

                        System.out.print(
                                "Number " +
                                (i + 1) +
                                ": "
                        );

                        original[i] =
                                scanner.nextInt();

                        working[i] =
                                original[i];

                    }

                    break;

                case 2:

                    if (original == null) {

                        System.out.println(
                                "Please enter an array first."
                        );

                    } else {

                        for (int number : original) {
                            System.out.print(
                                    number + " "
                            );
                        }

                    }

                    break;

                case 3:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // BUBBLE SORT
‎                    boolean isAlreadySorted = false;
‎                    int largerNumber = 0;
‎                    int smallerNumber = 0;
‎
‎                    while (isAlreadySorted == false) {
‎                        isAlreadySorted = true;
‎
‎                        for (int i = 0; i < working.length - 1; i++) {
‎                            if (working[i] > working[i + 1]) {
‎                                largerNumber = working[i];
‎                                smallerNumber = working[i + 1];
‎
‎                                working[i + 1] = largerNumber;
‎                                working[i] = smallerNumber;
‎
‎                                isAlreadySorted = false;
‎                            }
‎                        }
‎                    }
‎
‎                    for (int number : working) {
‎                        System.out.print(number + " ");
‎                    }
‎
‎                    break;
‎
‎                case 4:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // SELECTION SORT
‎                    for (int i = 0; i < working.length - 1; i++) {
‎                        int minIndex = i;
‎                        for (int j = i + 1; j < working.length; j++) {
‎                            if (working[j] < working[minIndex]) {
‎                                minIndex = j;
‎                            }
‎                        }
‎
‎                        // swap working[i] and working[minIndex]
‎                        int temp = working[minIndex];
‎                        working[minIndex] = working[i];
‎                        working[i] = temp;
‎                    }
‎
‎                    for (int number : working) {
‎                        System.out.print(number + " ");
‎                    }
‎
‎                    break;
‎
‎                case 5:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // INSERTION SORT
‎                    for (int i = 1; i < working.length; i++) {
‎                        int key = working[i];
‎                        int j = i - 1;
‎
‎                        // Shift elements that are greater than key
‎                        // one position to the right
‎                        while (j >= 0 && working[j] > key) {
‎                            working[j + 1] = working[j];
‎                            j--;
‎                        }
‎
‎                        working[j + 1] = key;
‎                    }
‎
‎                    for (int number : working) {
‎                        System.out.print(number + " ");
‎                    }
‎
‎                    break;
‎
‎                case 6:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    System.out.print("Enter number to search: ");
‎                    int target = scanner.nextInt();
‎
‎                    // LINEAR SEARCH FIRST
‎                    int linearIndex = -1;
‎                    for (int i = 0; i < working.length; i++) {
‎                        if (working[i] == target) {
‎                            linearIndex = i;
‎                            break;
‎                        }
‎                    }
‎
‎                    if (linearIndex != -1) {
‎                        System.out.println(
‎                                "Linear Search: Found at index " + linearIndex
‎                        );
‎                    } else {
‎                        System.out.println("Linear Search: Not found");
‎                    }
‎
‎                    // THEN BINARY SEARCH
‎                    // Binary search requires a sorted array, so sort
‎                    // a temporary copy first without disturbing "working"
‎                    int[] sortedCopy = Arrays.copyOf(working, working.length);
‎                    Arrays.sort(sortedCopy);
‎
‎                    int low = 0;
‎                    int high = sortedCopy.length - 1;
‎                    int binaryIndex = -1;
‎
‎                    while (low <= high) {
‎                        int mid = (low + high) / 2;
‎
‎                        if (sortedCopy[mid] == target) {
‎                            binaryIndex = mid;
‎                            break;
‎                        } else if (sortedCopy[mid] < target) {
‎                            low = mid + 1;
‎                        } else {
‎                            high = mid - 1;
‎                        }
‎                    }
‎
‎                    if (binaryIndex != -1) {
‎                        System.out.println(
‎                                "Binary Search: Found at sorted index " + binaryIndex
‎                        );
‎                    } else {
‎                        System.out.println("Binary Search: Not found");
‎                    }
‎
‎                    break;
‎
‎                case 7:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // FIND ALL DUPLICATES
‎                    boolean foundDuplicate = false;
‎
‎                    for (int i = 0; i < working.length; i++) {
‎                        boolean alreadyReported = false;
‎
‎                     
‎                        for (int k = 0; k < i; k++) {
‎                            if (working[k] == working[i]) {
‎                                alreadyReported = true;
‎                                break;
‎                            }
‎                        }
‎
‎                        if (alreadyReported) {
‎                            continue;
‎                        }
‎
‎                        int count = 0;
‎                        for (int j = 0; j < working.length; j++) {
‎                            if (working[j] == working[i]) {
‎                                count++;
‎                            }
‎                        }
‎
‎                        if (count > 1) {
‎                            System.out.println(
‎                                    working[i] + " appears " + count + " times"
‎                            );
‎                            foundDuplicate = true;
‎                        }
‎                    }
‎
‎                    if (!foundDuplicate) {
‎                        System.out.println("No duplicates found.");
‎                    }
‎
‎                    break;
‎
‎                case 8:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                  
‎
‎                    int[] sortedForMissing = Arrays.copyOf(working, working.length);
‎                    Arrays.sort(sortedForMissing);
‎
‎                    int missingNumber = -1;
‎
‎                    for (int i = 1; i < sortedForMissing.length; i++) {
‎                        int expected = sortedForMissing[i - 1] + 1;
‎                        if (sortedForMissing[i] != expected) {
‎                            missingNumber = expected;
‎                            break;
‎                        }
‎                    }
‎
‎                    if (missingNumber != -1) {
‎                        System.out.println("Missing Number: " + missingNumber);
‎                    } else {
‎                        System.out.println("No missing number found in the sequence.");
‎                    }
‎
‎                    break;
‎
‎                case 9:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // REVERSE ARRAY WITHOUT
‎                   
‎                    int start = 0;
‎                    int end = working.length - 1;
‎
‎                    while (start < end) {
‎                        int swapTemp = working[start];
‎                        working[start] = working[end];
‎                        working[end] = swapTemp;
‎
‎                        start++;
‎                        end--;
‎                    }
‎
‎                    for (int number : working) {
‎                        System.out.print(number + " ");
‎                    }
‎
‎                    break;
‎
‎                case 10:
‎
‎                    if (working == null) {
‎                        System.out.println("Please enter an array first.");
‎                        break;
‎                    }
‎
‎                    // DISPLAY:
‎                    // MINIMUM
‎                    // MAXIMUM
‎                    // SUM
‎                    // AVERAGE
‎                    // MEDIAN
‎                    // EVEN COUNT
‎                    // ODD COUNT
‎
‎                    int min = working[0];
‎                    int max = working[0];
‎                    long sum = 0;
‎                    int evenCount = 0;
‎                    int oddCount = 0;
‎
‎                    for (int number : working) {
‎                        if (number < min) {
‎                            min = number;
‎                        }
‎                        if (number > max) {
‎                            max = number;
‎                        }
‎
‎                        sum += number;
‎
‎                        if (number % 2 == 0) {
‎                            evenCount++;
‎                        } else {
‎                            oddCount++;
‎                        }
‎                    }
‎
‎                    double average = (double) sum / working.length;
‎
‎                    int[] sortedForMedian = Arrays.copyOf(working, working.length);
‎                    Arrays.sort(sortedForMedian);
‎
‎                    double median;
‎                    int mid = sortedForMedian.length / 2;
‎
‎                    if (sortedForMedian.length % 2 == 0) {
‎                        median = (sortedForMedian[mid - 1] + sortedForMedian[mid]) / 2.0;
‎                    } else {
‎                        median = sortedForMedian[mid];
‎                    }
‎ // DISPLAY:
‎                    System.out.println("Minimum: " + min);
‎ // MINIMUM
‎                    System.out.println("Maximum: " + max);
‎//MAXIMUM
‎                    System.out.println("Sum: " + sum);
‎ // SUM
‎                    System.out.println("Average: " + average);
‎// MEDIAN
‎                    System.out.println("Median: " + median);
‎// EVEN COUNT
‎                    System.out.println("Even Count: " + evenCount);
‎
‎// ODD COUNT
‎                    System.out.println("Odd Count: " + oddCount);
‎
‎                    break;
‎
‎            }
‎
‎        } while (choice != 11);
‎
‎        scanner.close();
‎    }
‎}
‎

            
