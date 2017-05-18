package fr.ebiz.computer_database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author ckeita
 */
@Repository
public class CompanyDAO {

    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
//    private ConnectionManager conMng = ConnectionManager.getInstance();

	@Autowired
    private DataSource dataSource;
    /**
     * @param id of the Company
     * @return the ResultSet of the query
     * @exception DAOException .
     */
    public Company findById(int id) throws DAOException {
        Company comp = new Company();
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
           // Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find Computer by id
            String query = "SELECT * FROM company WHERE id=?";
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter id through the preparedStatement
            pstm.setInt(1, id);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                comp = daoMApper(result);
            }
            return comp;
        } catch (SQLException e) {
            throw new DAOException("[findById] Impossible to fetch data from database");
        } finally {
            /*try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
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
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);

            // Create the sql query to find companies
            String query = "SELECT * FROM company LIMIT ?,?";
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameters of preparedStatement
            pstm.setInt(1, offset);
            pstm.setInt(2, max);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                list.add(daoMApper(result));
            }
            logger.info("Selected: " + result.getFetchSize() + " elements from database");
            return list;
        } catch (SQLException e) {
            throw new DAOException("[findByLimit] Impossible to fetch data from database");
        } finally {
            /*try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
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
