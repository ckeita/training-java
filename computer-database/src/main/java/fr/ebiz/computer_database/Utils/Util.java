package fr.ebiz.computer_database.Utils;

import java.time.format.DateTimeFormatter;

/**
 * @author ebiz
 */
public interface Util {

    String DB_CLASS = "com.mysql.jdbc.Driver";
    String DATABASE = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false&zeroDateTimeBehavior=convertToNull";
    String USERNAME = "admincdb";
    String PASSWORD = "qwerty1234";
    String IN_FORMAT = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    String STR_HOUR = " 00:00:00";

    String DASHBOARD_VIEW = "/WEB-INF/views/dashboard.jsp";
    String ADD_COMPUTER_VIEW = "/WEB-INF/views/add_computer.jsp";
    String EDIT_COMPUTER_VIEW = "/WEB-INF/views/edit_computer.jsp";

    String PARAM_ID = "id";
    String PARAM_NAME = "computerName";
    String PARAM_INTRODUCED = "introduced";
    String PARAM_DISCONTINUED = "discontinued";
    String PARAM_COMPANY_ID = "companyId";

    String DASH_REDIRECT = "dashboard";

    int PAGING = 10;
    int OFFSET = 0;
    int ALL_COMPANIES = 43;
}
