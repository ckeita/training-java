/**
 * 
 */
package fr.ebiz.computer_database.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author ckeita
 */
public class CompanyDAO {

    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return
     */
    public ResultSet findByLimit(int offset, int max) {

        try {
            // Create the sql query to find companies
            String query = "SELECT * FROM company LIMIT ?,?";

            // Initialize a preparedStatement
            PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection()
                    .prepareStatement(query);
            // Set the parameters of preparedStatement
            pstm.setInt(1, offset);
            pstm.setInt(2, max);
            // Execute the query
            ResultSet result = pstm.executeQuery();

            logger.info("Selected: " + result.getFetchSize() + " elements from database");
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
