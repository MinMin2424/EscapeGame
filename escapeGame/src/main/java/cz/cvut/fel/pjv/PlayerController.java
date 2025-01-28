package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.gameObjects_Items.GameItems;
import cz.cvut.fel.pjv.gameObjects_Items.GameObjects;

public class PlayerController {

    private Player player;
    private GameBoard gameBoard;

    public PlayerController(Player player, GameBoard gameBoard) {
        this.player = player;
        this.gameBoard = gameBoard;
    }

    // Metoda pro pohyb hráče nahorů
    public void moveUp() {
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        if (checkPlayerHealth()) {
            return;
        }

        if (currentX <= 0) {
            return; // Hráč se nachází na horním okraji herního pole, nemůže se pohnout výše
        }

        int objectCode = gameBoard.getBoard()[currentX - 1][currentY];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Nečinnost, hráč nemůže projít zdí
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {
            player.collideWithObstacle(object);
        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[currentX - 1][currentY] = 0; // Odebrání surovin
            }
            // Přesun hráče na novou pozici
            gameBoard.getBoard()[currentX][currentY] = 0; // Odebrání hráče z aktuální pozice
            player.setPlayerX(currentX - 1); // Nastavení nové X-ové souřadnice hráče
            gameBoard.placePlayer(player); // Umístění hráče na novou pozici
        }

    }

    // Metoda pro pohyb hráče dolů
    public void moveDown() {
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        if (checkPlayerHealth()) {
            return;
        }

        if (currentX >= gameBoard.getBoard().length - 1) {
            return; // Hráč se nachází na dolním okraji herního pole, nemůže se pohnout níže
        }

        int objectCode = gameBoard.getBoard()[currentX + 1][currentY];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Nečinnost, hráč nemůže projít zdí
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {
            player.collideWithObstacle(object);
        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[currentX + 1][currentY] = 0; // Odebrání surovin
            }
            // Přesun hráče na novou pozici
            gameBoard.getBoard()[currentX][currentY] = 0; // Odebrání hráče z aktuální pozice
            player.setPlayerX(currentX + 1); // Nastavení nové X-ové souřadnice hráče
            gameBoard.placePlayer(player); // Umístění hráče na novou pozici
        }

    }


    // Metoda pro pohyb hráče doleva
    public void moveLeft() {
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        if (checkPlayerHealth()) {
            return;
        }

        if (currentY <= 0) {
            return; // Hráč se nachází na levým okraji herního pole, nemůže se pohnout doleva
        }

        int objectCode = gameBoard.getBoard()[currentX][currentY - 1];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Nečinnost, hráč nemůže projít zdí
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {
            player.collideWithObstacle(object);
        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[currentX][currentY - 1] = 0; // Odebrání surovin
            }
            // Přesun hráče na novou pozici
            gameBoard.getBoard()[currentX][currentY] = 0; // Odebrání hráče z aktuální pozice
            player.setPlayerY(currentY - 1); // Nastavení nové Y-ové souřadnice hráče
            gameBoard.placePlayer(player); // Umístění hráče na novou pozici
        }

    }

    // Metoda pro pohyb hráče doprava
    public void moveRight() {
        int currentX = player.getPlayerX();
        int currentY = player.getPlayerY();

        if (checkPlayerHealth()) {
            return;
        }

        if (currentY > gameBoard.getBoard().length - 1) {
            return; // Hráč se nachází na pravým okraji herního pole, nemůže se pohnout doprava
        }

        int objectCode = gameBoard.getBoard()[currentX][currentY + 1];

        if (objectCode == GameObjects.WALL.getCode()) {
            return; // Nečinnost, hráč nemůže projít zdí
        }

        GameObjects object = GameObjects.getByCode(objectCode);
        if (object != null && object.isDamage()) {
            player.collideWithObstacle(object);
        } else {
            GameItems item = GameItems.getByCode(objectCode);
            if (item != null) {
                player.collideWithItem(item);
                gameBoard.getBoard()[currentX][currentY + 1] = 0; // Odebrání surovin
            }
            // Přesun hráče na novou pozici
            gameBoard.getBoard()[currentX][currentY] = 0; // Odebrání hráče z aktuální pozice
            player.setPlayerY(currentY + 1); // Nastavení nové Y-ové souřadnice hráče
            gameBoard.placePlayer(player); // Umístění hráče na novou pozici
        }

    }

    private boolean checkPlayerHealth() {
        if (player.getHealth() <= 0) {
            System.out.println("Hráč nemá dostatek životů. Konec hry");
            return true;
        }
        return false;
    }


}

