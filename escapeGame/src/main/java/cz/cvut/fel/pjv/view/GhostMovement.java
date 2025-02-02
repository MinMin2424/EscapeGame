/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition;
import cz.cvut.fel.pjv.view.renders.RenderBackground;
import cz.cvut.fel.pjv.view.renders.RenderObject;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles the movement of a ghost character on the game board.
 */
public class GhostMovement {

    private final GameBoard gameBoard; // The game board where the ghost moves
    private final int currentX; // The current x-coordinate of the ghost
    private int currentY; // The current y-coordinate of the ghost
    private final int startY;
    private final int finalY; // The final y-coordinate where the ghost moves
    private int direction; // The direction of movement (-1 or 1)
    private final GraphicsContext graphicsContext; // The graphics context for rendering
    private final RenderBackground renderBackground; // Renderer for the game background
    private final RenderObject renderObject; // Renderer for game objects
    private Timer timer; // Timer for scheduling ghost movement

    /**
     * Constructs a GhostMovement object with the specified parameters.
     *
     * @param gameBoard        The game board where the ghost moves.
     * @param currentX         The initial x-coordinate of the ghost.
     * @param currentY         The initial y-coordinate of the ghost.
     * @param startY           The start y-coordinate where the ghost moves.
     * @param finalY           The final y-coordinate where the ghost moves.
     * @param graphicsContext  The graphics context for rendering.
     * @param renderBackground The renderer for the game background.
     * @param renderObject     The renderer for game objects.
     */
    public GhostMovement(GameBoard gameBoard, int currentX, int currentY, int startY, int finalY, GraphicsContext graphicsContext, RenderBackground renderBackground, RenderObject renderObject) {
        this.gameBoard = gameBoard;
        this.currentX = currentX;
        this.currentY = currentY;
        this.startY = startY;
        this.finalY = finalY;
        this.direction = -1; // Default movement direction
        this.graphicsContext = graphicsContext;
        this.renderBackground = renderBackground;
        this.renderObject = renderObject;
    }

    /**
     * Starts the movement of the ghost by scheduling periodic updates.
     */
    public void startMovement(GhostPosition ghost) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                moveGhost(ghost);
                renderGame();
            }
        }, 1000, 1000); // Update ghost movement every second
    }

    /**
     * Moves the ghost character on the game board.
     * Clear current position.
     * Change direction when reaching boundaries or final position.
     * Move ghost and then set new position.
     */
    private void moveGhost(GhostPosition ghost) {
        gameBoard.getBoard()[currentX][currentY] = 0;

        if (currentY == finalY || currentY == startY || gameBoard.getBoard()[currentX][currentY+1] != 0 || gameBoard.getBoard()[currentX][currentY-1] != 0) {
            direction *= -1;
        }

        currentY += direction;
        ghost.setPositionY(currentY);

        gameBoard.getBoard()[currentX][currentY] = GameObjects.GHOST.getCode();
    }

    /**
     * Renders the updated game state after ghost movement.
     */
    private void renderGame() {
        Platform.runLater(() -> {
            renderBackground.render(graphicsContext); // Render background
            renderObject.renderObject(graphicsContext, gameBoard.getTileDim()); // Render game objects
        });
    }

    /**
     * Stops the movement of the ghost by cancelling the timer.
     */
    public void stopMovement() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
