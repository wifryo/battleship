package battleship;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Board {
    char[][] board = new char[10][10];
    char[][] fogBoard = new char[10][10];

    public Board() {
        for (char[] row : board)
            Arrays.fill(row, '~');
        for (char[] row : fogBoard)
            Arrays.fill(row, '~');
    }

    public boolean doShipsRemain() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (board[row][col] == 'O') {
                    return true;
                }
            }
        }
        return false;
    }

    public Position[] getShipPosition(Ship ship) {
        Position[] positions = new Position[2];
        boolean validShipPosition = false;

        while (!validShipPosition) {
            System.out.println("Enter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):");
            Scanner scanner = new Scanner(System.in);
            String pos1 = scanner.next();
            String pos2 = scanner.next();
            positions[0] = convertToPosition(pos1);
            positions[1] = convertToPosition(pos2);
            validShipPosition = isShipPositionValid(ship.getLength(), positions[0], positions[1]);
            if (!validShipPosition) {
                System.out.println("Error! Invalid ship position, please try again.");
            }
        }
        return positions;
    }

    public void takeShot() {
        System.out.println("Take a shot!");
        boolean validShotPosition = false;
        Position position = new Position();
        position.row = -1;
        position.col = -1;
        while (!isPositionOnBoard(position)) {
            Scanner scanner = new Scanner(System.in);
            String pos1 = scanner.next();
            position = convertToPosition(pos1);
            if (!isPositionOnBoard(position)) {
                System.out.println("Error! You entered invalid coordinates! Try again:");
            }
        }
        if (board[position.row][position.col] == 'O' || board[position.row][position.col] == 'X') {
            board[position.row][position.col] = 'X';
            fogBoard[position.row][position.col] = 'X';
            drawFog();
            System.out.println("You hit a ship!");
        } else {
            board[position.row][position.col] = 'M';
            fogBoard[position.row][position.col] = 'M';
            drawFog();
            System.out.println("You missed!");
        }
    }

    public void addShipToBoard(Position position1, Position position2) {
        // Check if ship is horizontal or vertical
        // Horizontally oriented ship
        if (position1.row == position2.row) {
            // Change symbols to ship
            if (position1.col < position2.col) {
                for (int i = position1.col; i <= position2.col; i++) {
                    board[position1.row][i] = 'O';
                }
            }
            if (position1.col > position2.col) {
                for (int i = position2.col; i <= position1.col; i++) {
                    board[position1.row][i] = 'O';
                }
            }
        }
        // Vertically oriented ship
        if (position1.col == position1.col) {
            // Change symbols to ship
            if (position1.row < position2.row) {
                for (int i = position1.row; i <= position2.row; i++) {
                    board[i][position1.col] = 'O';
                }
            }
            if (position1.row > position2.row) {
                for (int i = position2.row; i <= position1.row; i++) {
                    board[i][position1.col] = 'O';
                }
            }
        }
    }

    private static Position convertToPosition(String stringPosition) {
        Position position = new Position();
        position.row = stringPosition.charAt(0) - 65;
        String column = stringPosition.substring(1);
        position.col = Integer.parseInt(column) - 1;
        return position;
    }

    private boolean isShipPresent(Position position1, Position position2) {
        // Check if ship is horizontal or vertical
        // Horizontally oriented ship
        if (position1.row == position2.row) {
            if (position1.col < position2.col) {
                for (int i = position1.col; i <= position2.col; i++) {
                    if (board[position1.row][i] == 'O') {
                        return true;
                    }
                }
            }
            if (position1.col > position2.col) {
                for (int i = position2.col; i <= position1.col; i++) {
                    if (board[position1.row][i] == 'O') {
                        return true;
                    }
                }
            }
        }
        // Vertically oriented ship
        if (position1.col == position1.col) {
            if (position1.row < position2.row) {
                for (int i = position1.row; i <= position2.row; i++) {
                    if (board[i][position1.col] == 'O') {
                        return true;
                    }
                }
            }

            if (position1.row > position2.row) {
                for (int i = position2.row; i <= position1.row; i++) {
                    if (board[i][position1.col] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isShipAdjacent(Position adjPosition1, Position adjPosition2) {
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
                if (board[position1row][position1col - 1] == 'O' || board[position1row][position2col + 1] == 'O') {
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
                    if (board[adjacentPos.row][adjacentPos.col] == 'O') {
                        return true;
                    }
                }
                adjacentPos.row = adjPosition1.row - 1;
                if (isPositionOnBoard(adjacentPos)) {
                    if (board[adjacentPos.row][adjacentPos.col] == 'O') {
                        return true;
                    }
                }
            }
        }
        // Vertically oriented ship
        if (position1col == position2col) {
            // First check if there is another ship in front or behind ship being placed
            try {
                if (board[position1row - 1][position1col] == 'O' || board[position2row + 1][position1col] == 'O') {
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
                    if (board[adjacentPos.row][adjacentPos.col] == 'O') {
                        return true;
                    }
                }
                adjacentPos.col = adjPosition1.col - 1;
                if (isPositionOnBoard(adjacentPos)) {
                    if (board[adjacentPos.row][adjacentPos.col] == 'O') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void draw() {
        // Print column labels
        char rowLabel = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int row = 0; row < 10; row++) {
            // Print row labels
            System.out.print(rowLabel++ + " ");
            for (int col = 0; col < 10; col++) {
                // Print board contents
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void drawFog() {
        // Print column labels
        char rowLabel = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int row = 0; row < 10; row++) {
            // Print row labels
            System.out.print(rowLabel++ + " ");
            for (int col = 0; col < 10; col++) {
                // Print board contents
                System.out.print(fogBoard[row][col] + " ");
            }
            System.out.println();
        }
    }

    private boolean isShipPositionValid(int shipLength, Position position1, Position position2) {
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
        if (isShipPresent(position1, position2)) {
            //System.out.println("Occupied by another ship");
            return false;
        }


        // Check if position is adjacent to another ship
        if (isShipAdjacent(position1, position2)) {
            //System.out.println("Another ship adjacent");
            return false;
        }

        return true;
    }

    private static boolean isPositionOnBoard(Position position) {
        if (position == null) {
            return false;
        }
        else if (position.row < 0 || position.row > 9 || position.col < 0 || position.col > 9) {
            return false;
        }
        return true;
    }
}
