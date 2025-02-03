/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view.levels;

import cz.cvut.fel.pjv.model.GameBoard;
import cz.cvut.fel.pjv.model.placers.ObjectPlacer_Level1;

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

    @Override
    public int getLevel() {
        return 1;
    }
}
