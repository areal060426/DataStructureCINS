package Activity3;

import java.util.Scanner;
//Group 2: Jasper James Docado, Japhette Louis Magpulong, Johnpaul Maglana, Shiaorene Lomyier Capuno
//Willheime MarieJune Guillermo, Angelito Baltar, Erich Daler Custodio, Angel John Natividad
public class Group2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] seats [] = {
                {0,0,1,0,0,1},
                {1,1,0,0,0,0},
                {0,0,0,1,1,0},
                {0,1,0,0,0,0},
                {0,0,0,0,1,1},
        };

        displaySeats(seats);
        countSeats(seats);


        System.out.print("Enter row (1-5): ");
        int row = sc.nextInt() - 1;
        System.out.print("Enter seat number (1-6): ");
        int col = sc.nextInt() - 1;

        if(row < 0 || row >= 5 || col < 0 || col >= 6){
            System.out.println("Invalid Seat Location");
        }else if (seats[row][col] == 1){
            System.out.println("Seat already reserved!");
        }else{
            seats[row][col] = 1;
            System.out.println("Seat reserved successfully!");
        }

        System.out.println("Updated Seating Chart:");
        displaySeats(seats);
        countSeats(seats);
    }
    static void displaySeats(int [][] seats){
        System.out.println("         Seats");
        System.out.print("         ");
        for (int col = 1; col <= 6; col++){
            System.out.print(col + "  ");
        }
        System.out.println();

        for (int row = 0; row < 5; row++){
            System.out.print("Row " + (row + 1) + "    ");
            for (int col = 0; col < 6; col++){
                System.out.print(seats[row][col] + "  ");
            }
            System.out.println();
        }
    }

    static void countSeats(int [][] seats){
        int available = 0, reserved = 0;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 6; col++){
                if (seats[row][col] == 0){
                    available++;
                }else{
                    reserved++;
                }
            }
        }
        System.out.println("Available Seats: " + available);
        System.out.println("Reserved Seats: " + reserved);

    }
}
