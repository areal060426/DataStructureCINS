package FinalsProject.Group2;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {
    private static final String filePath = "src/main/java/FinalsProject/Group2/LostItems.txt";
    private static final String seperator = "\t";

    public static void save(ArrayList<Item> items) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Item item : items) {
                String line = item.getItemNumber() + seperator
                + clean(item.getItemName()) + seperator
                + item.getPlace() + seperator
                + item.getTime() + seperator
                + item.getDate() + seperator
                + item.isClaimed() + seperator
                + (item.getClaimedBy() == null ? " " : clean(item.getClaimedBy())) + seperator
                + (item.getDateClaimed() == null ? " " : item.getDateClaimed());
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println("Warning: could not save data (" + e.getMessage() + ")");
        }
    }


    public static ArrayList<Item> load() {
        ArrayList<Item> items = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return items;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] p = line.split(seperator, -1);
                try {
                    boolean claimed = Boolean.parseBoolean(p[5]);
                    items.add(new Item(
                            Integer.parseInt(p[0]),
                            p[1], p[2], p[3],
                            LocalDate.parse(p[4]),
                            claimed,
                            claimed ? p[6] : null,
                            claimed ? LocalDate.parse(p[7]) : null));
                } catch (RuntimeException e) {
                    System.out.println("Skipped a corrupted line in " + filePath);
                }
            }
        } catch (IOException e) {
            System.out.println("Warning: could not load data (" + e.getMessage() + ")");
        }
        return items;
    }

    private static String clean(String string) {
        return string.replace("\t", " ");
    }
}