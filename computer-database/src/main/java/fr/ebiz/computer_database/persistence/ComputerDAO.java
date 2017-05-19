package fr.ebiz.computer_database.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author ckeita
 */
@Repository
public class ComputerDAO {
    private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    private int id;
    private String name;
    private String introduced;
    private String discontinued;
    private String companyName;
    private int companyId;

    @Autowired
    private DataSource dataSource;
	@Autowired
    private PlatformTransactionManager txManager;

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public Computer findById(int id) throws DAOException, DateException {
        Computer comp = null;
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
        	//connection = conMng.getConnection();
            //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            logger.info(connection.toString());
            // Create the sql query to find Computer by id
            String query = Util.COMPUTER_BY_ID;

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter id through the preparedStatement
            pstm.setInt(1, id);
            // Execute the query
            result = pstm.executeQuery();

            while (result.next()) {
                comp = daoMapper(result);
            }
            logger.info("[findById] Found element from database");
            return comp;
        } catch (SQLException e) {
            throw new DAOException("[findById] Impossible to communicate with database findById");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    /**
     * @return number of computers in database
     * @throws DAOException .
     */
    public int getNumberOfComputers() throws DAOException {
        int number = 0;
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find Computer by id
            String query = Util.GET_NUMBER_OF_COMPUTERS;

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                number = result.getInt(1);
            }
            logger.info("[getNumberOfComputers] Found element from database");
            return number;
        } catch (SQLException e) {
            throw new DAOException("[getNumberOfComputers] Impossible to communicate with database");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
        }
    }

    /**
     * @param name to search
     * @return number of searched computers in database
     * @throws DAOException .
     */
    public int getNumberOfSearchedComputers(String name) throws DAOException {
        int number = 0;
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find Computer by id
            String query = Util.GET_NUMBER_OF_SEARCHED_COMPUTERS;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name
            pstm.setString(1, "%" + name + "%");
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                number = result.getInt(1);
            }
            logger.info("[getNumberOfSearchedComputers] Found elements from database");
            return number;
        } catch (SQLException e) {
            throw new DAOException("[getNumberOfSearchedComputers] Impossible to communicate with database");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
        }
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findByLimit(int offset, int max) throws DAOException, DateException {
        // create a list of computer
        List<Computer> list = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find computers
            String query = Util.COMPUTERS_BY_LIMIT;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameters of preparedStatement
            pstm.setInt(1, offset);
            pstm.setInt(2, max);
            // Execute the query
            result = pstm.executeQuery();
            logger.info("[findByLimit] fetch elements from database");
            while (result.next()) {
                list.add(daoMapper(result));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database findByLimit");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
        }
    }

    /**
     * @param order for ordering
     * @param orderBy for ordering
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findByOrder(String orderBy, String order) throws DAOException, DateException {
        // create a list of computer
        List<Computer> list = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find computers
            String query = Util.COMPUTERS_BY_ORDER + orderBy + " " + order;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            result = pstm.executeQuery();
            logger.info("[findByLimit] fetch elements from database");
            while (result.next()) {
                list.add(daoMapper(result));
            }

            return list;
        } catch (SQLException e) {
            throw new DAOException("[findByLimit] Impossible to communicate with database");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
        }
    }

    /**
     * @param name of computer to search
     * @param offset of the first row
     * @param max number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> searchByLimit(String name, int offset, int max) throws DAOException, DateException {
        // create a list of computer
        List<Computer> list = new ArrayList<>();
        ResultSet result = null;
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            // Create the sql query to find computers
            String query = Util.SEARCH_COMPUTERS;

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameters of preparedStatement
            pstm.setString(1, "%" + name + "%");
            pstm.setInt(2, offset);
            pstm.setInt(3, max);
            System.out.println(pstm);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                list.add(daoMapper(result));
            }
            logger.info("[searchByLimit] fetch elements from database");
            return list;
        } catch (SQLException e) {
            throw new DAOException("[searchByLimit] Impossible to communicate with database");
        } finally {
            DataSourceUtils.releaseConnection(connection, dataSource);
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm, result);*/
        }
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void create(Computer comp) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            String query = Util.CREATE_COMPUTER;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name through the preparedStatement
            pstm.setString(1, comp.getName());
            if (comp.getIntroduced() != null) {
                pstm.setString(2, comp.getIntroduced().toString());
            } else {
                pstm.setNull(2, Types.TIMESTAMP);
            }
            if (comp.getDiscontinued() != null) {
                pstm.setString(3, comp.getDiscontinued().toString());
            } else {
                pstm.setNull(3, Types.TIMESTAMP);
            }
            if (comp.getCompanyId() == 0) {
                pstm.setNull(4, Types.BIGINT);
            } else {
                pstm.setInt(4, comp.getCompanyId());
            }
            // Execute the query
            if (pstm.executeUpdate() == 0) {
                logger.info("[create] Impossible to insert");
            } else {
                logger.info("[create] Inserted successfully");
            }
        } catch (MySQLIntegrityConstraintViolationException ex) {
            logger.info("Impossible to insert: The company is not found in database");
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database create");
        } finally {
           /* try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm);*/
        }
    }

    /**
     * @param compId the ID of computer to delete from database
     * @throws SQLException
     * @exception DAOException .
     */
    public void delete(int compId) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            String query = Util.DELETE_COMPUTER;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name through the preparedStatement
            pstm.setInt(1, compId);
            // Execute the query
            if (pstm.executeUpdate() == 0) {
                logger.info("[delete] The computer is not found");
            } else {
                logger.info("[delete] Deleted successfully");
            }
        } catch (SQLException e) {
            throw new DAOException("[delete] Impossible to delete computer delete");
        } finally {
            /*try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm);*/
        }
    }

    /**
     * @param comp the computer to update
     * @throws SQLException
     * @exception DAOException .
     */
    public void update(Computer comp) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = null;
        try {
        	// Get one connection
	        //connection = dataSource.getConnection();
	        connection = DataSourceUtils.getConnection(dataSource);
            String query = Util.UPDATE_COMPUTER;

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name through the preparedStatement
            pstm.setString(1, comp.getName());
            if (comp.getIntroduced() != null) {
                pstm.setString(2, comp.getIntroduced().toString());
            } else {
                pstm.setNull(2, Types.TIMESTAMP);
            }
            if (comp.getDiscontinued() != null) {
                pstm.setString(3, comp.getDiscontinued().toString());
            } else {
                pstm.setNull(3, Types.TIMESTAMP);
            }
            if (comp.getCompanyId() != 0) {
                pstm.setInt(4, comp.getCompanyId());
            } else {
                pstm.setNull(4, Types.BIGINT);
            }
            pstm.setInt(5, comp.getId());
            // Execute the query
            if (pstm.executeUpdate() == 0) {
                logger.info("Impossible to update: The computer is not found");
            } else {
                logger.info("Updated successfully");
            }
        } catch (MySQLIntegrityConstraintViolationException ex) {
            logger.info("Impossible to update: The company is not found in the database");
            throw new DAOException("Impossible to update: The company is not found in the database");
        } catch (SQLException e) {
            throw new DAOException("Impossible to update computer");
        } finally {
            /*try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        	/*if (!conMng.isTransactional()) {
        		conMng.closeConnection();
        	}
        	conMng.closeObjects(pstm);*/
        }
    }

    /**
     * @param resultSet the data from database
     * @return the computer from database
     * @throws DateException .
     * @exception DAOException .
     */
    private Computer daoMapper(ResultSet resultSet) throws DAOException, DateException {
        try {
            id = resultSet.getInt(1);
            name = resultSet.getString(2);
            introduced = resultSet.getString(3);
            discontinued = resultSet.getString(4);
            companyName = resultSet.getString(5);
            companyId = resultSet.getInt(6);
            return new Computer.ComputerBuilder(name).id(id).introduced(introduced)
                    .discontinued(discontinued).companyName(companyName)
                    .companyId(companyId).build();
        } catch (SQLException e) {
            throw new DAOException("Impossible to fetch data from database");
        }
    }
}
