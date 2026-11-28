package FinalsProject.Group2;
import java.util.ArrayList;
import java.util.Scanner;
//Group 2: Jasper James Docado, Japhette Louis Magpulong, Johnpaul Maglana, Shiaorene Lomyier Capuno
//Willheime MarieJune Guillermo, Angelito Baltar, Erich Daler Custodio
public class MainMenu {

    public static void main(String[] args) {
        ArrayList<Item> lostItems = Storage.load();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Lost and Found =====");
            System.out.println("  1. Add lost item");
            System.out.println("  2. Search lost items");
            System.out.println("  3. Claim lost item");
            System.out.println("  4. View all lost items");
            System.out.println("  5. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    Add.addItems(scanner, lostItems);
                    Storage.save(lostItems);
                    break;
                case "2":
                    Search.searchItems(scanner, lostItems);
                    break;
                case "3":
                    Claim.claimItem(scanner, lostItems);
                    Storage.save(lostItems);
                    break;
                case "4":
                    viewAllItems(scanner, lostItems);
                    break;
                case "5":
                    Storage.save(lostItems);
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Shows every item
    private static void viewAllItems(Scanner scanner, ArrayList<Item> lostItems) {
        if (lostItems.isEmpty()) {
            System.out.println("No lost items yet.");
            return;
        }

        System.out.println("\n--- View All Lost Items ---");
        System.out.println("  0. No Sorting (Order added by users)");
        System.out.println("  1. Item name (A-Z)");
        System.out.println("  2. Date found (oldest to latest)");
        System.out.println("  3. Time found (morning to evening)");

        ArrayList<Item> view = new ArrayList<>(lostItems);
        while (true) {
            System.out.print("Sort by: ");
            String choice = scanner.nextLine().trim();
            if (choice.equals("1")) {
                sortByName(view);
            } else if (choice.equals("2")) {
                sortByDate(view);
            } else if (choice.equals("3")) {
                sortByTime(view);
            } else if (!choice.equals("0")) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            break;
        }

        System.out.println("\n--- All Lost Items ---");
        for (Item item : view) {
            System.out.println(item);
        }
    }


    private static void sortByName(ArrayList<Item> list) {
        if (list.size() < 2) return;

        int mid = list.size() / 2;
        ArrayList<Item> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Item> right = new ArrayList<>(list.subList(mid, list.size()));

        sortByName(left);
        sortByName(right);

        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getItemName().compareToIgnoreCase(right.get(j).getItemName()) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) list.set(k++, left.get(i++));
        while (j < right.size()) list.set(k++, right.get(j++));
    }

    private static void sortByDate(ArrayList<Item> list) {
        for (int i = 1; i < list.size(); i++) {
            Item key = list.get(i);
            int j = i - 1;
            while (j >= 0 && list.get(j).getDate().isAfter(key.getDate())) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    private static void sortByTime(ArrayList<Item> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < list.size() - 1 - i; j++) {
                if (timeRank(list.get(j)) > timeRank(list.get(j + 1))) {
                    Item temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    private static int timeRank(Item item) {
        for (int i = 0; i < Add.timeFound.length; i++) {
            if (Add.timeFound[i].equals(item.getTime())) {
                return i;
            }
        }
        return -1;
    }
}