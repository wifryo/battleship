package battleship;
import java.lang.*;
import java.util.Scanner;

import static java.lang.Math.abs;

class Position {
    int row;
    int col;
}

public class Main {

    public static void main(String[] args) {

        Board board1 = new Board();
        board1.draw();

        for (Ship ship : Ship.values()) {
            Position[] inputPositions = board1.getShipPosition(ship);
            board1.addShipToBoard(inputPositions[0], inputPositions[1]);
            board1.draw();
        }

        System.out.println("Press Enter and pass the move to another player");
        Scanner scanner = new Scanner(System.in);
        String pos1 = scanner.next();

        Board board2 = new Board();
        board2.draw();

        for (Ship ship : Ship.values()) {
            Position[] inputPositions = board2.getShipPosition(ship);
            board2.addShipToBoard(inputPositions[0], inputPositions[1]);
            board2.draw();
        }

        System.out.println("Press Enter and pass the move to another player");
        pos1 = scanner.next();

        while (board1.doShipsRemain() && board2.doShipsRemain()) {
            board2.drawFog();
            System.out.println("---------------------");
            board1.draw();
            System.out.println();
            System.out.println("Player 1, it's your turn:");
            board2.takeShot();
            if (!board2.doShipsRemain()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }

            board1.drawFog();
            System.out.println("---------------------");
            board2.draw();
            System.out.println();
            System.out.println("Player 2, it's your turn:");
            board1.takeShot();
            if (!board1.doShipsRemain()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
        }
//

    }
}











