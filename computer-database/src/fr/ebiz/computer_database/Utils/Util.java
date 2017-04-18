/**
 * 
 */
package fr.ebiz.computer_database.Utils;

import java.time.format.DateTimeFormatter;

/**
 * @author ebiz
 */
public interface Util {

    public final String DB_CLASS = "com.mysql.jdbc.Driver";
    public final String DATABASE = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false&zeroDateTimeBehavior=convertToNull";
    public final String USERNAME = "admincdb";
    public final String PASSWORD = "qwerty1234";
    public final String IN_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    public final DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public final DateTimeFormatter OUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    public final String STR_HOUR = " 00:00:00";

    public final String DASHBOARD_VIEW = "/WEB-INF/views/dashboard.jsp";
    public final String ADD_COMPUTER_VIEW = "/WEB-INF/views/addComputer.jsp";
    public final String EDIT_COMPUTER_VIEW = "/WEB-INF/views/editComputer.jsp";
    
    public final String NAME = "computerName";
    public final String INTRODUCED = "introduced";
    public final String DISCONTINUED = "discontinued";
    public final String COMPANY_ID = "companyId";
}
