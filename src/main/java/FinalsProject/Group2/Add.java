package FinalsProject.Group2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Add {
    public static final String[] placeFound = {
            "Admin Bldg.", "Oval", "Academic Bldg.1",
            "Academic Bldg.2", "Academic Bldg.3", "HPSB Building"
    };
    public static final String[] timeFound = {"Morning", "Afternoon", "Evening"};

    public static void addItems(Scanner scanner, ArrayList<Item> lostItems) {
        while (true) {
            System.out.println("--- Adding a Lost Item ---");
            System.out.print("Enter item name: ");
            String name = scanner.nextLine().trim();

            String place = chooseFrom(scanner, "Place found:", placeFound);
            String time = chooseFrom(scanner, "Time found:", timeFound);
            LocalDate date = askDate(scanner);

            Item item = new Item(name, place, time, date);
            lostItems.add(item);
            System.out.println("Added: " + item);
            System.out.print("Add another item? (y/n): ");
            String again = scanner.nextLine().trim();
            if (!again.equalsIgnoreCase("y")) {
                break;
            }
            System.out.println();
        }
    }

    public static String chooseFrom(Scanner scanner, String prompt, String[] options) {
        System.out.println(prompt);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
        while (true) {
            System.out.print("Enter choice (1-" + options.length + "): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public static LocalDate askDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD, or press Enter for today): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return LocalDate.now();
            }
            try {
                LocalDate date = LocalDate.parse(input);
                if (date.isAfter(LocalDate.now())) {
                    System.out.println("The date cannot be in the future.");
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use the format YYYY-MM-DD, e.g. 2026-11-16.");
            }
        }
    }
}