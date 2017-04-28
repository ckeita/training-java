package fr.ebiz.computer_database.persistence;

import java.sql.Connection;

import fr.ebiz.computer_database.exceptions.DAOException;

/**
 * @author ckeita
 */
public class Transaction {

    private static ThreadLocal<Connection> transContext = new ThreadLocal<>();

    /**
     * @param connection to set
     */
    public static void setTransaction(Connection connection) {
        transContext.set(connection);
    }

    /**
     * @return transaction ID
     */
    public static Connection getTransactionId() {
        return transContext.get();
    }

    /**
     * @throws DAOException .
     */
    public static void removeTransaction() throws DAOException {
        transContext.remove();
    }

}
