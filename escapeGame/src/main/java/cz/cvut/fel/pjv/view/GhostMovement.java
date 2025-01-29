package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;

import java.util.Timer;
import java.util.TimerTask;

public class GhostMovement {

    private final GameBoard gameBoard;
    private final int currentX;
    private int currentY;
    private final int finalY;
    private int direction;

    public GhostMovement(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.currentX = 4;
        this.currentY = 2;
        this.finalY = 7;
        this.direction = 1;
    }

    public void startMovement() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveGhost();
            }
        }, 1000, 1000);
    }

    private void moveGhost() {
        gameBoard.getBoard()[currentX][currentY] = 0;

        if (currentY == finalY || currentY == 2) {
            direction *= -1;
        }

        currentY += direction;
        gameBoard.getBoard()[currentX][currentY] = GameObjects.GHOST.getCode();
    }
}
