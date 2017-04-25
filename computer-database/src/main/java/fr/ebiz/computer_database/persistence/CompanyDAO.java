package fr.ebiz.computer_database.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.PreparedStatement;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.model.Company;

/**
 * @author ckeita
 */
public class CompanyDAO {

    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

    /**
     * @param id of the Company
     * @return the ResultSet of the query
     * @exception DAOException .
     */
    public Company findById(int id) throws DAOException {
        Company comp = new Company();
        try {
            // Create the sql query to find Computer by id
            String query = "SELECT * FROM company WHERE id=?";

            // Initialize a preparedStatement
            PreparedStatement pstm = (PreparedStatement) Persistence.getInstance().getConnection()
                    .prepareStatement(query);
            // Set the parameter id through the preparedStatement
            pstm.setInt(1, id);
            // Execute the query
            ResultSet result = pstm.executeQuery();
            while (result.next()) {
                comp = daoMApper(result);
            }
            return comp;
        } catch (SQLException e) {
            throw new DAOException("Impossible to fetch data from database");
        }
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @exception DAOException .
     * @return resultSet
     */
    public List<Company> findByLimit(int offset, int max) throws DAOException {
        List<Company> list = new ArrayList<>();
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
            while (result.next()) {
                list.add(daoMApper(result));
            }
            logger.info("Selected: " + result.getFetchSize() + " elements from database");
            return list;
        } catch (SQLException e) {
            throw new DAOException("Impossible to fetch data from database");
        }
    }

    /**
     * @param resultSet to retrieve data
     * @return the company from database
     * @exception DAOException .
     */
    private Company daoMApper(ResultSet resultSet) throws DAOException {
        Company comp = new Company();
        try {
            comp.setId(resultSet.getInt(1));
            comp.setName(resultSet.getString(2));
            return comp;
        } catch (SQLException e) {
            throw new DAOException("Impossible to fetch data from database");
        }
    }
}
