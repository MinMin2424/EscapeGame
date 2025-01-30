/**
 * @author tranomin@fel.cvut.cz
 */

package cz.cvut.fel.pjv.view;

/**
 * Represents information about message.
 * This class holds data such as title, main message, secondary message, and the name of the image associated with the message.
 */
public class MessageInfo {
    private final String title;
    private final String message, message2;
    private final String imageName;

    /**
     * Constructs a MessageInfo object with the specified title, main message, secondary message, and image name.
     * @param title The title of the message.
     * @param message The main message content.
     * @param message2 The secondary message content.
     * @param imageName The name of the associated image.
     */
    public MessageInfo(String title, String message, String message2, String imageName) {
        this.title = title;
        this.message = message;
        this.message2 = message2;
        this.imageName = imageName;
    }

    /**
     * Retrieves the title of the message.
     * @return The title of the message.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the main message content.
     * @return The main message content.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the secondary message.
     * @return The secondary message content.
     */
    public String getMessage2() {
        return message2;
    }

    /**
     * Retrieves the name of the associated image.
     * @return The name of the associated image.
     */
    public String getImageName() {
        return imageName;
    }
}
