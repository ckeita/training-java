package fr.ebiz.computer_database.exception;

/**
 * @author ebiz
 */
public class DAOException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * @param message to print
     */
    public DAOException(String message) {
        super(message);
    }
}
