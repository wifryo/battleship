package battleship;
import java.lang.*;

import static java.lang.Math.abs;

class Position {
    int row;
    int col;
}

public class Main {

    public static void main(String[] args) {

        Board board = new Board();
        board.draw();

        for (Ship ship : Ship.values()) {
            Position[] inputPositions = board.getShipPosition(ship);
            board.addShipToBoard(inputPositions[0], inputPositions[1]);
            board.draw();
        }
        System.out.println("The game starts!");
        board.drawFog();
        System.out.println();

        while (board.doShipsRemain()) {
            board.takeShot();
            board.draw();
            System.out.println();
        }

        System.out.println("You sank the last ship. You won. Congratulations!");

    }
}











