package fr.ebiz.computer_database.util;

import java.time.format.DateTimeFormatter;

/**
 * @author ebiz
 */
public interface Util {
	String CONFIG_HIKARI = "/db.propreties";
    String DB_CLASS = "com.mysql.jdbc.Driver";
    String DATABASE = "jdbc:mysql://localhost/computer-database-db?useSSL=false&zeroDateTimeBehavior=convertToNull";
    String USERNAME = "admincdb";
    String PASSWORD = "qwerty1234";
    String CACHE_PREP_STMTS = "cachePrepStmts";
    String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    int MAXPOOLSIZE = 25;
    String IN_FORMAT = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    DateTimeFormatter TO_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter FROM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    String STR_HOUR = " 00:00:00";
    String DATE_FORMAT_EXCEPTION = "Date Fomat not valid";
    String GREATER_THAN = "The discontinued date must be greater than the introduced date";
    String NAME_EXCEPTION = "The name must be set";

    String DASHBOARD_VIEW = "/WEB-INF/views/dashboard.jsp";
    String ADD_COMPUTER_VIEW = "/WEB-INF/views/add_computer.jsp";
    String EDIT_COMPUTER_VIEW = "/WEB-INF/views/edit_computer.jsp";

    //PARAM
    String PARAM_ID = "id";
    String PARAM_NAME = "name";
    String PARAM_INTRODUCED = "introduced";
    String PARAM_DISCONTINUED = "discontinued";
    String PARAM_COMPANY_ID = "companyId";
    String PARAM_COMPANY_NAME = "company";
    String PARAM_SEARCH = "search";

    //QUERIES
    String BASE_QUERY = "SELECT cpt.id, cpt.name, cpt.introduced, cpt.discontinued, cpy.name, cpy.id FROM computer cpt LEFT JOIN company cpy ON cpt.company_id = cpy.id ";
    String COMPUTER_BY_ID = BASE_QUERY+"WHERE cpt.id=?";
    String GET_NUMBER_OF_COMPUTERS = "SELECT count(*) FROM computer";
    String GET_NUMBER_OF_SEARCHED_COMPUTERS ="SELECT count(*) FROM computer WHERE name LIKE ?";
    String COMPUTERS_BY_LIMIT = BASE_QUERY+"LIMIT ?,?";
    String COMPUTERS_BY_ORDER = BASE_QUERY+"ORDER BY cpt.";
    String SEARCH_COMPUTERS = BASE_QUERY+"WHERE cpt.name LIKE ? LIMIT ?,?";
    String CREATE_COMPUTER = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
    String DELETE_COMPUTER = "DELETE FROM computer WHERE id=?";
    String UPDATE_COMPUTER = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    String COMPANIES_BY_ID = "SELECT * FROM company WHERE id=?";
    String COMPANIES_BY_LIMIT = "SELECT * FROM company LIMIT ?,?";
    //ERROR PAGE
    int ERROR_500 = 500;
    int ERROR_404 = 404;
    int ERROR_403 = 403;

    String DASH_REDIRECT = "dashboard";

    String ASC = "ASC";
    String DESC = "DESC";
    String COLUMN_SORT = "column";
    String CURRENT_PAGE = "current";
    String PAGE_LIMIT = "limit";
    String ORDER_COLUMN = "column";
    String SORT_ORDER = "order";

    int PAGING = 10;
    int OFFSET = 0;
    int ALL_COMPANIES = 43;
}
