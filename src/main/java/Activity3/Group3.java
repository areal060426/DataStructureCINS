package Activity3;
import java.util.Scanner;
/*

*/
public class Group3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String winCondition = "";
        // 1. Create the board
        char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        char player = 'X';
        // Game has 9 turns maximum
        for (int turn = 0; turn < 9; turn++) {
            // 2. Display board using nested loops
            System.out.println("\n  1   2   3");
            for (int row = 0; row < 3; row++) {
                System.out.print((row + 1) + " ");
                for (int col = 0; col < 3; col++) {
                    System.out.print(board[row][col]);
                    if (col < 2) {
                        System.out.print(" | ");
                    }
                }
                System.out.println();
                if (row < 2) {
                    System.out.println("  ---------");
                }
            }
            // 3. Allow Player X and Player O to Select a Row & Column
            System.out.println("\nPlayer " + player + "'s turn");
            System.out.print("Enter coordinates (x, y): ");
            String coordinates=input.nextLine();
            String[] parts=coordinates.split(",");
            int row=Integer.parseInt(parts[0].trim())-1;
            int col=Integer.parseInt(parts[1].trim())-1;
            // Check if position is valid
            if (row < 0 || row > 2 || col < 0 || col > 2) {
                System.out.println("Invalid position!");
                turn--;
                continue;
            }
            // 5. Check if position is occupied
            if (board[row][col] != ' ') {
                System.out.println("Position is already occupied!");
                turn--;
                continue;
            }
            // 4. Put player's symbol on board
            board[row][col] = player;
            // Check horizontal
            boolean win = false;
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) {
                    winCondition = "Horizontal Win!";
                    win = true;
                }
            }
            // Check vertical
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == player &&
                    board[1][i] == player &&
                    board[2][i] == player) {
                    winCondition = "Vertical Win!";
                    win = true;
                }
            }
            // Check diagonals
            if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
                winCondition = "Diagonal Win!";
                win = true;
            }
            if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
                winCondition = "Diagonal Win!";
                win = true;
            }
            // Display winner
            if (win) {
                System.out.println("\n  1   2   3");
                for (row = 0; row < 3; row++) {
                    System.out.print((row + 1) + " ");
                    for (col = 0; col < 3; col++) {
                        System.out.print(board[row][col]);
                        if (col < 2) {
                            System.out.print(" | ");
                        }
                    }
                System.out.println();
                if (row < 2) {
                    System.out.println("  ---------");
                }
            }
                System.out.println("\nPlayer " + player + " wins!\n" + winCondition);
                break;
            }
            // Switch player
            if (player == 'X') {
                player = 'O';
            } else {
                player = 'X';
            }
            // If last turn
            if (turn == 8) {
                System.out.println("\nIt's a draw!");
            }
        }
        input.close();
        System.out.println("");
    }
}
