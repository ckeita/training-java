package fr.ebiz.computer_database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;

/**
 * @author ckeita
 */
public final class ConnectionManager {

    private String driverClass;
    private String database;
    private String username;
    private String password;

    public static ConnectionManager instance = new ConnectionManager();
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);
    private HikariDataSource dataSource = null;

    private ThreadLocal<Connection> connections = new ThreadLocal<>();

    /**
     * Constructor for connection to Mysql database.
     */
    private ConnectionManager() {
        super();
        try {
            Class.forName(Util.DB_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Impossible to load the driver");
        }
        HikariConfig config = new HikariConfig(Util.CONFIG_HIKARI);
        dataSource = new HikariDataSource(config);
    }

    /**
     * @return The instance of connection
     */
    public static ConnectionManager getInstance() {
        if (ConnectionManager.instance == null) {
            ConnectionManager.instance = new ConnectionManager();
        }
        return ConnectionManager.instance;
    }

    /**
     * @return One connection from pool
     */
    public Connection getConnection() {
        Connection connection = connections.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                connections.set(connection);
            } catch (SQLException e) {
                LOGGER.error("Error Unable to Connect to Database");
                throw new IllegalStateException(e);
            }
        }
        return connection;
    }

    /**
     * start the transaction
     */
    public void startTransaction() {
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Error while starting transaction");
            throw new IllegalStateException(e);
        }
    }

    /**
     * @return the state of the actual transaction
     */
    public boolean isTransactional() {
        try {
            return !getConnection().getAutoCommit();
        } catch (SQLException e) {
            LOGGER.error("Error while getting transaction state");
            throw new IllegalStateException(e);
        }
    }

    /**
     * Rolling back the transaction
     */
    public void rollback() {
        Connection connection = connections.get();
        if (connection == null) {
            LOGGER.error("Cannot rollback non-existent transaction");
            throw new IllegalStateException("Cannot rollback non-existent transaction");
        }
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Error while rolling back transaction");
            throw new IllegalStateException(e);
        }
    }

    /**
     * Committing the transaction
     */
    public void commit() {
        Connection connection = connections.get();
        if (connection == null) {
            LOGGER.error("Cannot commit non-existent transaction");
            throw new IllegalStateException("Cannot commit non-existent transaction");
        }
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Error while committing transaction");
            throw new IllegalStateException(e);
        }
    }

    /**
     * close the actual connection
     */
    public void closeConnection() {
        Connection connection = connections.get();
        if (connection == null) {
            LOGGER.error("Cannot close non-existent transaction");
            throw new IllegalStateException("Cannot close non-existent transaction");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Error while closing connection");
            throw new IllegalStateException(e);
        } finally {
            connections.remove();
        }
    }

    /**
     * @param pstm to close
     * @throws DAOException .
     */
    public void closeObjects(PreparedStatement pstm) throws DAOException {
        closeObjects(pstm, null);
    }

    /**
     * @param pstm to close
     * @param result to close
     * @throws DAOException .
     */
    public void closeObjects(PreparedStatement pstm, ResultSet result) throws DAOException {
        if (pstm != null) {
            try {
            	pstm.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
        if (result != null) {
            try {
            	result.close();
            } catch (SQLException e) {
                throw new DAOException(e.getMessage());
            }
        }
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
