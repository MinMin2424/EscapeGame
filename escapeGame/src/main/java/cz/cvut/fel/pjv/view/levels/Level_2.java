/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.placers.ObjectPlacer_Level2;

/**
 * Represents the second level of the game.
 * Extends the LevelBase class.
 */
public class Level_2 extends LevelBase {

    /**
     * Constructs a Level_2 object
     * Initializes the ObjectPlacer_Level2 with a new GameBoard.
     */
    public Level_2() {
        super(new ObjectPlacer_Level2(new GameBoard()));
    }
}


