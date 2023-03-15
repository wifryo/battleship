package battleship;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

class Position {
    int row;
    int col;
}

public class Main {

    public static void main(String[] args) {
        // Initialise board array
        char[][] currentBoard = new char[10][10];
        for (char[] row : currentBoard)
            Arrays.fill(row, '~');

        // Draw board
        drawBoard(currentBoard);

        // Get aircraft carrier inputs
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        Scanner scanner = new Scanner(System.in);
        String pos1 = scanner.next();
        String pos2 = scanner.next();
        // Convert string positions to integers stored in instances of Position classes
        Position position1 = convertToPosition(pos1);
        Position position2 = convertToPosition(pos2);

    }
    public static Position convertToPosition(String stringPosition) {
        Position position = new Position();
        position.row = stringPosition.charAt(0) - 65;
        position.col = (int)stringPosition.charAt(1) - 48;
        return position;
    }
    public static boolean checkIfShipPresent(char[][] currentBoard, Position position) {
        // todo
        return true;
    }
    public static void drawBoard(char[][] currentBoard) {
        // Print column labels
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int row = 0; row < 10; row++) {
            // Print row labels
            char rowLabel = (char) (65 + row);
            System.out.print(rowLabel + " ");
            for (int col = 0; col < 10; col++) {
                // Print board contents
                System.out.print(currentBoard[row][col] + " ");
            }
            System.out.println();
        }
    }
}

