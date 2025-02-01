/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.view.GhostMovement;
import cz.cvut.fel.pjv.view.placers.ObjectPlacer_Level1;
import javafx.scene.canvas.GraphicsContext;

import static cz.cvut.fel.pjv.model.gameObjects_Items.GhostPosition.*;

/**
 * Represents the first level of the game.
 * Extends the LevelBase class.
 */
public class Level_1 extends LevelBase {

    /**
     * Constructs a Level_1 object.
     * Initializes the ObjectPlacer_Level1 with a new GameBoard.
     */
    public Level_1() {
        super(new ObjectPlacer_Level1(new GameBoard()));
    }

    /**
     * Sets up the ghost movements for the first level.
     * @param graphicsContext The graphics context for rendering.
     */
    @Override
    protected void setupGhostMovements(GraphicsContext graphicsContext) {
        ghostMovement1 = new GhostMovement(gameBoard, GHOST1.getPositionX(), GHOST1.getPositionY(), GHOST1.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement1.startMovement(GHOST1);

        ghostMovement2 = new GhostMovement(gameBoard, GHOST2.getPositionX(), GHOST2.getPositionY(), GHOST2.getFinalPositionY(), graphicsContext, renderBackground, renderObject);
        ghostMovement2.startMovement(GHOST2);
    }


}
