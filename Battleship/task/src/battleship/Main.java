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
        Position[] inputPositions = getShipPosition("Aircraft Carrier", 5, currentBoard);
        currentBoard = addShipToBoard(currentBoard, inputPositions[0], inputPositions[1]);

        drawBoard(currentBoard);

        // Get battleship inputs
        inputPositions = getShipPosition("Battleship", 4, currentBoard);
        currentBoard = addShipToBoard(currentBoard, inputPositions[0], inputPositions[1]);

        drawBoard(currentBoard);

        // Get submarine inputs
        inputPositions = getShipPosition("Submarine", 3, currentBoard);
        currentBoard = addShipToBoard(currentBoard, inputPositions[0], inputPositions[1]);

        drawBoard(currentBoard);

        // Get cruiser inputs
        inputPositions = getShipPosition("Cruiser", 3, currentBoard);
        currentBoard = addShipToBoard(currentBoard, inputPositions[0], inputPositions[1]);

        drawBoard(currentBoard);

        // Get destroyer inputs
        inputPositions = getShipPosition("Destroyer", 2, currentBoard);
        currentBoard = addShipToBoard(currentBoard, inputPositions[0], inputPositions[1]);

        drawBoard(currentBoard);

    }

    public static Position[] getShipPosition(String shipName, int shipLength, char[][] currentBoard) {
        Position[] positions = new Position[2];
        positions[0] = new Position();
        positions[1] = new Position();
        positions[0].row = -1;
        positions[0].col = -1;
        positions[1].row = -1;
        positions[1].col = -1;

        while (!isShipPositionValid(currentBoard, shipLength, positions[0], positions[1])) {
            System.out.println("Enter the coordinates of the " + shipName + " (" + shipLength + " cells):");
            Scanner scanner = new Scanner(System.in);
            String pos1 = scanner.next();
            String pos2 = scanner.next();
            positions[0] = convertToPosition(pos1);
            positions[1] = convertToPosition(pos2);
            if ((!isShipPositionValid(currentBoard, shipLength, positions[0], positions[1]))) {
                System.out.println("Error! Invalid ship position, please try again.");
            }

        }
        return positions;
    }

    public static char[][] addShipToBoard(char[][] currentBoard, Position position1, Position position2) {
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
        }
        // Vertically oriented ship
        if (position1.col == position1.col) {
            // Change symbols to ship
            if (position1.row < position2.row) {
                for (int i = position1.row; i <= position2.row; i++) {
                    currentBoard[i][position1.col] = 'O';
                }
            }
            if (position1.row > position2.row) {
                for (int i = position2.row; i <= position1.row; i++) {
                    currentBoard[i][position1.col] = 'O';
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

    public static boolean isShipAdjacent(char[][] currentBoard, Position adjPosition1, Position adjPosition2) {
        // Todo - fix bug in this method that changes the positions unintentionally
        // Reorder inputs if backwards
        int position1row = adjPosition1.row;
        int position1col = adjPosition1.col;
        int position2row = adjPosition2.row;
        int position2col = adjPosition2.col;

        if (adjPosition1.row > adjPosition2.row) {
            position1row = adjPosition2.row;
            position2row = adjPosition1.row;
        }
        if (adjPosition1.col > adjPosition2.col) {
            position1col = adjPosition2.row;
            position2col = adjPosition1.row;
        }

        // Check if ship is horizontal or vertical
        // Horizontally oriented ship
        if (position1row == position2row) {

                // First check if there is another ship in front or behind ship being placed
            try {
                if (currentBoard[position1row][position1col - 1] == 'O' || currentBoard[position1row][position2col + 1] == 'O') {
                    return true;
                }}
            catch (Exception e) {
                // bleb
            }
                // Check if there is another ship beside ship being placed
                for (int i = position1col; i <= position2col; i++) {
                    Position adjacentPos = new Position();
                    adjacentPos.row = position1row + 1;
                    adjacentPos.col = i;
                    if (isPositionOnBoard(adjacentPos)) {
                        if (currentBoard[adjacentPos.row][adjacentPos.col] == 'O') {
                            return true;
                        }
                    }
                    adjacentPos.row = adjPosition1.row - 1;
                    if (isPositionOnBoard(adjacentPos)) {
                        if (currentBoard[adjacentPos.row][adjacentPos.col] == 'O') {
                            return true;
                        }
                    }
                }
        }
        // Vertically oriented ship
        if (position1col == position2col) {
                // First check if there is another ship in front or behind ship being placed
            try {
                if (currentBoard[position1row - 1][position1col] == 'O' || currentBoard[position2row + 1][position1col] == 'O') {
                    return true;
                }}
            catch (Exception e) {
                // bleb
            }
                // Check if there is another ship beside ship being placed
                for (int i = position1row; i <= position2row; i++) {
                    Position adjacentPos = new Position();
                    adjacentPos.row = i;
                    adjacentPos.col = adjPosition1.col + 1;
                    if (isPositionOnBoard(adjacentPos)) {
                        if (currentBoard[adjacentPos.row][adjacentPos.col] == 'O') {
                            return true;
                        }
                    }
                    adjacentPos.col = adjPosition1.col - 1;
                    if (isPositionOnBoard(adjacentPos)) {
                        if (currentBoard[adjacentPos.row][adjacentPos.col] == 'O') {
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
            //System.out.println("Out of bounds");
            return false;
        }
        if (!isPositionOnBoard(position2)) {
            //System.out.println("Out of bounds");
            return false;
        }

        // Check if diagonal somehow
        if (position1.row != position2.row && position1.col != position2.col) {
            //System.out.println("Diagonal");
            return false;
        }
        // Check if length correct
        if (position1.row == position2.row) {
            int inputLength = abs(position1.col - position2.col) + 1;
            if (inputLength != shipLength) {
                //System.out.println("Wrong length");
                return false;
            }
        }
        if (position1.col == position2.col) {
            int inputLength = abs(position1.row - position2.row) + 1;
            if (inputLength != shipLength) {
                //System.out.println("Wrong length");
                return false;
            }
        }

        // Check if position is occupied by another ship
        if (isShipPresent(currentBoard, position1, position2)) {
            //System.out.println("Occupied by another ship");
            return false;
        }


        // Check if position is adjacent to another ship
        if (isShipAdjacent(currentBoard, position1, position2)) {
            //System.out.println("Another ship adjacent");
            return false;
        }

        return true;
    }

    public static boolean isPositionOnBoard(Position position) {
        if (position.row < 0 || position.row > 9 || position.col < 0 || position.col > 9) {
            return false;
        }
        return true;
    }
}










