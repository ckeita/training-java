package fr.ebiz.computer_database.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import fr.ebiz.computer_database.Utils.Util;

/**
 * @author ckeita
 */
public final class Persistence {

    private static Persistence persistence = new Persistence();
    private HikariConfig hikariConfig = new HikariConfig();
    private HikariDataSource hikariDataSource;

    /**
     * create an persistence object.
     */
    private Persistence() {
        // Use MySQL class
        try {
            Class.forName(Util.DB_CLASS);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        hikariConfig.setJdbcUrl(Util.DATABASE);
        hikariConfig.setUsername(Util.USERNAME);
        hikariConfig.setPassword(Util.PASSWORD);
        hikariConfig.setMaximumPoolSize(Util.MAXPOOLSIZE);
        hikariConfig.setAutoCommit(false);
        hikariConfig.addDataSourceProperty(Util.CACHE_PREP_STMTS, "true");
        hikariConfig.addDataSourceProperty(Util.PREP_STMT_CACHE_SIZE, "250");
        hikariConfig.addDataSourceProperty(Util.PREP_STMT_CACHE_SQL_LIMIT, "2048");
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * @return the single instance
     */
    public static Persistence getInstance() {
        return persistence;
    }

    /**
     * @return one connection from the pool
     */
    public Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
