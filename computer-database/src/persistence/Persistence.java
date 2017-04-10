/**
 * 
 */
package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import Utils.Util;


/**
 * @author ckeita
 *
 */
public final class Persistence {
	
	private Connection connection = null;
	
	private static Persistence persistence = null; 
	private Persistence() {
		
		try {
			//Use MySQL class
			Class.forName(Util.DB_CLASS);
			//Establish connection
			connection = (Connection) DriverManager.getConnection(Util.DATABASE,Util.USERNAME, Util.PASSWORD);			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Persistence getInstance() {
		if(persistence == null) {
			persistence = new Persistence();
		}
		return persistence;
	}
	
	/**
	 * Close the connection
	 */
	public void closeCon () throws SQLException {
		connection.close();
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}
}
