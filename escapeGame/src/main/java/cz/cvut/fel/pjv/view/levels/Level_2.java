/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.view.GhostMovement;
import cz.cvut.fel.pjv.view.placers.ObjectPlacer_Level2;
import javafx.scene.canvas.GraphicsContext;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.*;

/**
 * Represents the second level of the game.
 * Extends the LevelBase class.
 */
public class Level_2 extends LevelBase {

    /**
     * Constructs a Level_2 object.
     * Initializes the ObjectPlacer_Level2 with a new GameBoard.
     */
    public Level_2() {
        super(new ObjectPlacer_Level2(new GameBoard()));
    }

    /**
     * Sets up the ghost movements for the second level.
     * @param graphicsContext The graphics context for rendering.
     */
    @Override
    protected void setupGhostMovements(GraphicsContext graphicsContext) {
        ghostMovement1 = new GhostMovement(gameBoard, GHOST3.getPositionX(), GHOST3.getPositionY(), GHOST3.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement1.startMovement(GHOST3);

        ghostMovement2 = new GhostMovement(gameBoard, GHOST4.getPositionX(), GHOST4.getPositionY(), GHOST4.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement2.startMovement(GHOST4);
    }
}


