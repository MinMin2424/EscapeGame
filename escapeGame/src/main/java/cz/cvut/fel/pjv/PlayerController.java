package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.gameObjects_Items.CraftingItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameNextLevel;
import cz.cvut.fel.pjv.gameObjects_Items.GameObjects;
import cz.cvut.fel.pjv.direction.Direction;

public class PlayerController {

    private final Player player;
    private final GameBoard gameBoard;

    public PlayerController(Player player, GameBoard gameBoard) {
        this.player = player;
        this.gameBoard = gameBoard;
    }

    //Metoda pro pohyb hráče
    public void move(Direction direction) {

        // Aktuální pozice hráče
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        // Zjistí, zda hráč má život na další move
        if (checkPlayerHealth()) {
            return;
        }

        // Nová pozice hráče
        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case UP:
                newX--;
                break;
            case DOWN:
                newX++;
                break;
            case LEFT:
                newY--;
                break;
            case RIGHT:
                newY++;
                break;
            default:
                System.out.println("Neznámý směr pohybu.");
                return;
        }

        //Zjistí, zda nová pozice vychází mimo herní pole
        if (newX < 0 || newX >= gameBoard.getBoard().length || newY < 0 || newY >= gameBoard.getBoard()[0].length) {
            return;
        }

        int objectCode = gameBoard.getBoard()[newX][newY];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Nečinnost, hráč nemůže projít zdí
        }

        if (objectCode == GameNextLevel.NEXT_LEVEL.getCode()) {
            if (!handleNextLevel()) {
                return;
            }
            // Přechod na další level ...
            GamePosition2 gamePosition2 = new GamePosition2();
            gamePosition2.startGame();
            return;
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {

            if (object == GameObjects.FIRE && hasItem(GameItems.WATER_ITEM.name())) {
                player.useItem(GameItems.WATER_ITEM.name());
                System.out.println("Použil jsi WATER_ITEM k zhasnutí ohně.");
                gameBoard.getBoard()[newX][newY] = 0; // Odebrání ohně

            } else if (object == GameObjects.GHOST && hasItem(CraftingItems.SWORD.name())) {
                player.useItem(CraftingItems.SWORD.name());
                System.out.println("Použil jsi SWORD k boji s duchem.");
                gameBoard.getBoard()[newX][newY] = 0;

            } else {
                player.collideWithObstacle(object);
            }

        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[newX][newY] = 0; // Odebrání surovin
            }

            // Přesun hráče na novou pozici
            movePlayer(currentX, currentY, newX, newY);
        }
    }

    // Metoda pro zjištění, zda hráč má dostatek životů na další pohyb
    private boolean checkPlayerHealth() {
        if (player.getHealth() <= 0) {
            System.out.println("Hráč nemá dostatek životů. Konec hry");
            return true;
        }
        return false;
    }

    // Metoda pro zjištění, zda hrá může přejít na další level.
    private boolean handleNextLevel () {
        if (hasItem(GameItems.KEY.name())) {
            System.out.println("Hráč má klíč v inventáři. Přechod na další level ...");
            return true;
        }
        // Hráč nemá klíč v inventáři
        System.out.println("Hráč nemá klíč v inventáři. Nelze přejít na další level.");
        return false;
    }

    // Metoda pro zjištění zda daný předmět je v inventáři
    private boolean hasItem (String itemName){
        Inventory inventory = player.getInventory();
        for (Item item : inventory.getItems()) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    // Metoda pro přesun hráče na novou pozici
    private void movePlayer ( int currentX, int currentY, int newX, int newY){
        gameBoard.getBoard()[currentX][currentY] = 0; // Odebrání hráče z aktuální pozice
        player.setPlayerX(newX); // Nastavení nové X-ové souřadnice hráče
        player.setPlayerY(newY); // Nastavení nové Y-ové souřadnice hráče
        gameBoard.placePlayer(player); // Umístění hráče na novou pozici
    }
}