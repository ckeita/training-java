package fr.ebiz.computer_database.exception;

/**
 * @author ebiz
 */
public class DateException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * @param message to print
     */
    public DateException(String message) {
        throw new IllegalArgumentException(message);
    }
}
