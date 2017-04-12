/**
 * 
 */
package Utils;

import java.time.format.DateTimeFormatter;

/**
 * @author ebiz
 *
 */
public interface Util {
	
	public final String DB_CLASS = "com.mysql.jdbc.Driver"; 
	public final String DATABASE = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false&zeroDateTimeBehavior=convertToNull";
	public final String USERNAME = "admincdb";
	public final String PASSWORD = "qwerty1234";
	public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
}
