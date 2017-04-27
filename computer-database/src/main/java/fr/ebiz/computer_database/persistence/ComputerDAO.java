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

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.exceptions.DateException;
import fr.ebiz.computer_database.model.Computer;

/**
 * @author ckeita
 */
public class ComputerDAO {
    private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
    private int id;
    private String name;
    private String introduced;
    private String discontinued;
    private int companyId;

    // private String query;
    //
    // /**
    // * the default constructor.
    // */
    // public ComputerDAO() {
    // // TODO Auto-generated constructor stub
    // }
    //
    // /**
    // * @param query to execute
    // */
    // public ComputerDAO(String query) {
    // this.query = query;
    // }

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
        Connection connection = Persistence.getInstance().getConnection();
        try {
            // Create the sql query to find Computer by id
            String query = "SELECT * FROM computer WHERE id=?";

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter id through the preparedStatement
            pstm.setInt(1, id);
            // Execute the query
            result = pstm.executeQuery();

            while (result.next()) {
                comp = daoMapper(result);
            }
            logger.info("Found element from database");
            return comp;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
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
        Connection connection = Persistence.getInstance().getConnection();
        try {
            // Create the sql query to find Computer by id
            String query = "SELECT count(*) FROM computer";

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                number = result.getInt(1);
            }
            logger.info("Found element from database");
            return number;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
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
        Connection connection = Persistence.getInstance().getConnection();
        try {
            // Create the sql query to find Computer by id
            String query = Util.QUERY_NB_COMPUTER_SEARCH;
            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name
            pstm.setString(1, "%" + name + "%");
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                number = result.getInt(1);
            }
            logger.info("Found elements from database");
            return number;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
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
        Connection connection = Persistence.getInstance().getConnection();
        try {
            // Create the sql query to find computers
            String query = "SELECT * FROM computer LIMIT ?,?";

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameters of preparedStatement
            pstm.setInt(1, offset);
            pstm.setInt(2, max);
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                list.add(daoMapper(result));
            }
            logger.info("Selected: " + result.getFetchSize() + " elements from database");
            return list;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
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
        Connection connection = Persistence.getInstance().getConnection();
        try {
            // Create the sql query to find computers
            String query = Util.QUERY_COMPUTER_SEARCH;

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
            logger.info("Selected: " + result.getFetchSize() + " elements from database");
            return list;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
        }
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void create(Computer comp) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = Persistence.getInstance().getConnection();
        try {
            String query = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES(?,?,?,?)";
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
            if (comp.getcompanyId() == 0) {
                pstm.setNull(4, Types.BIGINT);
            } else {
                pstm.setInt(4, comp.getcompanyId());
            }
            // Execute the query
            if (pstm.executeUpdate() == 0) {
                logger.info("Computer Insert: Impossible to insert");
            } else {
                logger.info("Computer Insert: Inserted successfully");
            }
        } catch (MySQLIntegrityConstraintViolationException ex) {
            logger.info("Impossible to insert: The company is not found in database");
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
        }
    }

    /**
     * @param compId the ID of computer to delete from database
     * @throws SQLException
     * @exception DAOException .
     */
    public void delete(int compId) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = Persistence.getInstance().getConnection();
        try {
            String query = "DELETE FROM computer WHERE id=?";

            // Initialize a preparedStatement
            pstm = connection.prepareStatement(query);
            // Set the parameter name through the preparedStatement
            pstm.setInt(1, compId);
            // Execute the query
            if (pstm.executeUpdate() == 0) {
                logger.info("Impossible to delete: The computer is not found");
            } else {
                logger.info("Deleted succeDAOExceptionssfully");
            }
        } catch (SQLException e) {
            throw new DAOException("Impossible to delete computer");
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
        }
    }

    /**
     * @param comp the computer to update
     * @throws SQLException
     * @exception DAOException .
     */
    public void update(Computer comp) throws DAOException {
        PreparedStatement pstm = null;
        Connection connection = Persistence.getInstance().getConnection();
        try {
            String query = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";

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
            if (comp.getcompanyId() != 0) {
                pstm.setInt(4, comp.getcompanyId());
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
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
        }
    }

    /**
     * @param name to search
     * @exception DAOException .
     * @exception DateException .
     * @return the list of found computers
     */
    public List<Computer> search(String name) throws DAOException, DateException {
        // create a list of computer
        List<Computer> list = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet result = null;
        Connection connection = Persistence.getInstance().getConnection();

        // Initialize a preparedStatement
        try {
            String query = "SELECT * FROM computer WHERE name LIKE ?";
            pstm = connection.prepareStatement(query);
            // Set the parameter name
            pstm.setString(1, "%" + name + "%");
            // Execute the query
            result = pstm.executeQuery();
            while (result.next()) {
                list.add(daoMapper(result));
            }
            return list;
        } catch (SQLException e) {
            throw new DAOException("Impossible to communicate with database");
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                throw new DAOException("Impossible to close connection");
            }
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
            companyId = resultSet.getInt(5);
            return new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued)
                    .companyId(companyId).build();
        } catch (SQLException e) {
            throw new DAOException("Impossible to fetch data from database");
        }
    }

}
