package FinalsProject.Group2;

import java.time.LocalDate;
import java.util.*;

public class Claim {

    public static void claimItem(Scanner scanner, ArrayList<Item> lostItems) {
        ArrayList<Item> unclaimed = new ArrayList<>();
        for (Item item : lostItems) {
            if (!item.isClaimed()) {
                unclaimed.add(item);
            }
        }

        if (unclaimed.isEmpty()) {
            System.out.println("There are no unclaimed items.");
            return;
        }

        System.out.println("\n--- Unclaimed Items ---");
        for (Item item : unclaimed) {
            System.out.println(item);
        }


        Item selected = null;
        while (selected == null) {
            System.out.print("Enter the item number to claim (0 to cancel): ");
            String input = scanner.nextLine().trim();
            int number;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }
            if (number == 0) {
                System.out.println("Claim cancelled.");
                return;
            }
            for (Item item : unclaimed) {
                if (item.getItemNumber() == number) {
                    selected = item;
                    break;
                }
            }
            if (selected == null) {
                System.out.println("That item number is not in the unclaimed list.");
            }
        }


        String claimer = "";
        while (claimer.isEmpty()) {
            System.out.print("Enter the name of the person claiming the item: ");
            claimer = scanner.nextLine().trim();
            if (claimer.isEmpty()) {
                System.out.println("Name cannot be empty.");
            }
        }


        LocalDate dateClaimed = Add.askDate(scanner);
        while (dateClaimed.isBefore(selected.getDate())) {
            System.out.println("The claim date cannot be before the date found (" + selected.getDate() + ").");
            dateClaimed = Add.askDate(scanner);
        }


        System.out.print("Confirm: " + selected.getItemName() + " claimed by " + claimer
                + " on " + dateClaimed + "? (y/n): ");
        String confirm = scanner.nextLine().trim();
        if (confirm.equalsIgnoreCase("y")) {
            selected.claim(claimer, dateClaimed);
            System.out.println("Item claimed: " + selected);
        } else {
            System.out.println("Claim cancelled.");
        }
    }
}