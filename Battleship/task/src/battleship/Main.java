package battleship;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        char[][] currentBoard = new char[10][10];
        for (char[] row : currentBoard)
            Arrays.fill(row, '~');
        drawBoard(currentBoard);
    }

    public static void drawBoard(char[][] currentBoard) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int row = 0; row < 10; row++) {
            char rowLabel = (char) (65 + row);
            System.out.print(rowLabel + " ");
            for (int col = 0; col < 10; col++) {
                System.out.print(currentBoard[row][col] + " ");
            }
            System.out.println();
        }
    }
}

