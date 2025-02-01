package cz.cvut.fel.pjv.model;

import cz.cvut.fel.pjv.model.gameObjects_Items.GameObjects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameBoardTest {

    GameBoard gameBoard;

    /**
     * Initializes the test environment before each test case.
     */
    @BeforeEach
    void initData() {
        gameBoard = new GameBoard();
    }

    /**
     * Tests the correct functionality of the placeObject method when valid coordinates are provided.
     */
    @Test
    void testPlaceObject_ValidCoordinates(){
        gameBoard.placeObject(GameObjects.WALL, 0, 0);
        int expected = GameObjects.WALL.getCode();
        int actual = gameBoard.getBoard()[0][0];
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Tests whether the placeObject method throws an IllegalArgumentException
     * when coordinates are provided that are out of bounds of the game board.
     */
    @Test
    void testPlaceObject_OutOfBounds() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameBoard.placeObject(GameObjects.WALL, -1, 5));
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameBoard.placeObject(GameObjects.WALL, 10, 3));
    }

    /**
     * Tests whether the placeObject method throws an IllegalArgumentException
     * when a null GameObject is provided.
     */
    @Test
    void testPlaceObject_NullGameObject() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> gameBoard.placeObject(null, 2, 2));
    }
}
