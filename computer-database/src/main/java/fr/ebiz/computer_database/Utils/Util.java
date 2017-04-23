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
    public final DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    public final String STR_HOUR = " 00:00:00";

    public final String DASHBOARD_VIEW = "/WEB-INF/views/dashboard.jsp";
    public final String ADD_COMPUTER_VIEW = "/WEB-INF/views/add_computer.jsp";
    public final String EDIT_COMPUTER_VIEW = "/WEB-INF/views/edit_computer.jsp";
    
    public final String PARAM_ID = "id";
    public final String PARAM_NAME = "computerName";
    public final String PARAM_INTRODUCED = "introduced";
    public final String PARAM_DISCONTINUED = "discontinued";
    public final String PARAM_COMPANY_ID = "companyId";
    
    public final String DASH_REDIRECT = "dashboard";
    
    public final int PAGING = 10;
    public final int OFFSET = 0;
    public final int ALL_COMPANIES = 43;
}
