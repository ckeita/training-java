package fr.ebiz.computer_database.persistence;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import fr.ebiz.computer_database.Utils.Util;

/**
 * @author ckeita
 */
public final class Persistence {

    private Connection connection = null;

    private static Persistence persistence = new Persistence();

    /**
     * create an persistence object.
     */
    private Persistence() {

        try {
            // Use MySQL class
            Class.forName(Util.DB_CLASS);
            // Establish connection
            connection = (Connection) DriverManager.getConnection(Util.DATABASE, Util.USERNAME, Util.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the single instance
     */
    public static Persistence getInstance() {
        return persistence;
    }

    /**
     * Close the connection.
     * @throws SQLException .
     */
    public void closeCon() throws SQLException {
        connection.close();
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}
