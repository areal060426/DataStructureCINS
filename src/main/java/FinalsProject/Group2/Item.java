package FinalsProject.Group2;
import java.time.LocalDate;

public class Item {
    private static int nextNumber = 1;

    //Setters
    private final int itemNumber;
    private final String itemName;
    private final String place;
    private final String time;
    private final LocalDate date;

    private boolean claimed = false;
    private String claimedBy = null;
    private LocalDate dateClaimed = null;

    //Getters
    public int getItemNumber() { return itemNumber; }
    public String getItemName() { return itemName; }
    public String getPlace() { return place; }
    public String getTime() { return time; }
    public LocalDate getDate() { return date; }
    public boolean isClaimed() { return claimed; }
    public String getClaimedBy() { return claimedBy; }
    public LocalDate getDateClaimed() { return dateClaimed; }


    // Adding an item
    public Item(String itemName, String place, String time, LocalDate date) {
        this.itemNumber = nextNumber++;
        this.itemName = itemName;
        this.place = place;
        this.time = time;
        this.date = date;
    }

    // Used to load items
    public Item(int itemNumber, String itemName, String place, String time, LocalDate date,
                boolean claimed, String claimedBy, LocalDate dateClaimed) {
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.place = place;
        this.time = time;
        this.date = date;
        this.claimed = claimed;
        this.claimedBy = claimedBy;
        this.dateClaimed = dateClaimed;
        if (itemNumber >= nextNumber) {
            nextNumber = itemNumber + 1;
        }
    }

    //For items that have been claimed
    public void claim(String claimedBy, LocalDate dateClaimed) {
        this.claimed = true;
        this.claimedBy = claimedBy;
        this.dateClaimed = dateClaimed;
    }

    @Override
    public String toString() {
        String status = claimed
                ? "Claimed by " + claimedBy + " on " + dateClaimed
                : "Unclaimed";
        return itemNumber + ". " + itemName
                + " | Place found: " + place
                + " | Time found: " + time
                + " | Date found: " + date
                + " | Status: " + status;
    }
}