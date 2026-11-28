package FinalsProject.Group2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Search {

    public static void searchItems(Scanner scanner, ArrayList<Item> lostItems) {
        if (lostItems.isEmpty()) {
            System.out.println("There are no lost items to search yet.");
            return;
        }

        while (true) {
            System.out.println("\n--- Search Lost Items ---");
            System.out.println("  1. By item name");
            System.out.println("  2. By place found");
            System.out.println("  3. By time found");
            System.out.println("  4. By date found");
            System.out.println("  5. By item number");
            System.out.println("  0. Back");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            ArrayList<Item> results = new ArrayList<>();

            switch (choice) {
                case "1": {
                    System.out.print("Enter item name (or part of it): ");
                    String keyword = scanner.nextLine().trim().toLowerCase();
                    for (Item item : lostItems) {
                        if (item.getItemName().toLowerCase().contains(keyword)) {
                            results.add(item);
                        }
                    }
                    break;
                }
                case "2": {
                    String place = Add.chooseFrom(scanner, "Place found:", Add.placeFound);
                    for (Item item : lostItems) {
                        if (item.getPlace().equals(place)) {
                            results.add(item);
                        }
                    }
                    break;
                }
                case "3": {
                    String time = Add.chooseFrom(scanner, "Time found:", Add.timeFound);
                    for (Item item : lostItems) {
                        if (item.getTime().equals(time)) {
                            results.add(item);
                        }
                    }
                    break;
                }
                case "4": {
                    LocalDate date = Add.askDate(scanner);
                    for (Item item : lostItems) {
                        if (item.getDate().equals(date)) {
                            results.add(item);
                        }
                    }
                    break;
                }
                case "5": {
                    System.out.print("Enter item number: ");
                    try {
                        int number = Integer.parseInt(scanner.nextLine().trim());
                        for (Item item : lostItems) {
                            if (item.getItemNumber() == number) {
                                results.add(item);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        continue;
                    }
                    break;
                }
                case "0":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }

            printResults(results);
        }
    }

    private static void printResults(ArrayList<Item> results) {
        if (results.isEmpty()) {
            System.out.println("No matching items found.");
            return;
        }
        System.out.println("\nFound " + results.size() + " item(s):");
        for (Item item : results) {
            System.out.println(item);
        }
    }
}