package battleship;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

import static java.lang.Math.abs;

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

        if (isShipPositionValid(currentBoard, 5, position1, position2)) {
            System.out.println("position valid");
            currentBoard = addShipToBoard(currentBoard, position1, position2);
        } else {
            System.out.println("Error! Position invalid.");
        }

        drawBoard(currentBoard);

        // Get battleship inputs
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        pos1 = scanner.next();
        pos2 = scanner.next();
        // Convert string positions to integers stored in instances of Position classes
        position1 = convertToPosition(pos1);
        position2 = convertToPosition(pos2);

        if (isShipPositionValid(currentBoard, 4, position1, position2)) {
            System.out.println("position valid");
            currentBoard = addShipToBoard(currentBoard, position1, position2);
        } else {
            System.out.println("Error! Ship position invalid.");
        }

        drawBoard(currentBoard);

    }


    public static char[][] addShipToBoard(char[][] currentBoard, Position position1, Position position2) {
        //todo: make addshiptoboard work with column 10

        // Check if ship is horizontal or vertical
        // Horizontally oriented ship
        if (position1.row == position2.row) {

            // Change symbols to ship
            if (position1.col < position2.col) {
                for (int i = position1.col; i <= position2.col; i++) {
                    currentBoard[position1.row][i] = 'O';
                }
            }
            if (position1.col > position2.col) {
                for (int i = position2.col; i <= position1.col; i++) {
                    currentBoard[position1.row][i] = 'O';
                }
            }
            // Vertically oriented ship
            if (position1.col == position1.col) {
                // Change symbols to ship
                if (position1.row < position2.row) {
                    for (int i = position1.row; i <= position2.row; i++) {
                        currentBoard[position1.col][i] = 'O';
                    }
                }
                if (position1.row > position2.row) {
                    for (int i = position2.row; i <= position1.row; i++) {
                        currentBoard[position1.col][i] = 'O';
                    }
                }
            }
        }
        return currentBoard;
    }

    public static Position convertToPosition(String stringPosition) {
        Position position = new Position();
        position.row = stringPosition.charAt(0) - 65;
        String column = stringPosition.substring(1);
        position.col = Integer.parseInt(column) - 1;
        return position;
    }

    public static boolean isShipPresent(char[][] currentBoard, Position position1, Position position2) {
        // Check if ship is horizontal or vertical
        // Horizontally oriented ship
        if (position1.row == position2.row) {
            if (position1.col < position2.col) {
                for (int i = position1.col; i <= position2.col; i++) {
                    if (currentBoard[position1.row][i] == 'O') {
                        return true;
                    }
                }
            }
            if (position1.col > position2.col) {
                for (int i = position2.col; i <= position1.col; i++) {
                    if (currentBoard[position1.row][i] == 'O') {
                        return true;
                    }
                }
            }
        }
        // Vertically oriented ship
        if (position1.col == position1.col) {
            if (position1.row < position2.row) {
                for (int i = position1.row; i <= position2.row; i++) {
                    if (currentBoard[i][position1.col] == 'O') {
                        return true;
                    }
                }
            }

            if (position1.row > position2.row) {
                for (int i = position2.row; i <= position1.row; i++) {
                    if (currentBoard[i][position1.col] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public static boolean isShipPositionValid(char[][] currentBoard, int shipLength, Position position1, Position position2) {
        // Check if positions are in bounds
        if (!isPositionOnBoard(position1)) {
            System.out.println("Error! Co-ordinates out of bounds.");
            return false;
        }
        if (!isPositionOnBoard(position2)) {
            System.out.println("Error! Co-ordinates out of bounds.");
            return false;
        }


        // Check if diagonal somehow
        if (position1.row != position2.row && position1.col != position2.col) {
            System.out.println("Error! Invalid ship shape.");
            return false;
        }
        // Check if length correct
        System.out.println("position 1 (row, col): " + position1.row + " " + position1.col);
        System.out.println("position 2 (row, col): " + position2.row + " " + position2.col);

        if (position1.row == position2.row) {
            int inputLength = abs(position1.col - position2.col) + 1;
            System.out.println("inputted ship length: " + inputLength);
            if (inputLength != shipLength) {
                System.out.println("Error! Invalid ship length.");
                return false;
            }
        }
        if (position1.col == position2.col) {
            int inputLength = abs(position1.row - position2.row) + 1;
            if (inputLength != shipLength) {
                System.out.println("Error! Invalid ship length.");
                return false;
            }
        }
        // Check if position is occupied by another ship
        if (isShipPresent(currentBoard, position1, position2)) {
            return false;
        }
        // Check if position is adjacent to another ship
        //todo
        return true;
    }

    public static boolean isPositionOnBoard(Position position) {
        if (position.row < 0 || position.row > 9 || position.col < 0 || position.col > 9) {
            System.out.println("Error! Co-ordinates out of bounds.");
            return false;
        }
        return true;
    }
}










