package fr.ebiz.computer_database.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.exceptions.DateException;
import fr.ebiz.computer_database.mapper.ComputerMapper;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.persistence.ComputerDAO;
import fr.ebiz.computer_database.persistence.Persistence;
import fr.ebiz.computer_database.persistence.Transaction;

/**
 * @author ckeita
 */
public class ComputerService {

    private static ComputerDAO computerDAO = new ComputerDAO();
    private static ComputerMapper computerMapper = new ComputerMapper();

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public ComputerDTO findComputerById(int id) throws DateException, DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        ComputerDTO computerDTO = computerMapper.getById(computerDAO.findById(id));
        try {
            connection.close();
            Transaction.removeTransaction();
            return computerDTO;
        } catch (SQLException e) {
            throw new DAOException("[findComputerById] " + e.getMessage());
        }
    }

    /**
     * @return the number of computers
     * @throws DAOException .
     */
    public int getNumberOfComputers() throws DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        int nbComputers = computerDAO.getNumberOfComputers();
        try {
            connection.close();
            Transaction.removeTransaction();
            return nbComputers;
        } catch (SQLException e) {
            throw new DAOException("[getNumberOfComputers] " + e.getMessage());
        }
    }

    /**
     * @param name to search
     * @return the number of searched computers
     * @throws DAOException .
     */
    public int getNumberOfSearchedComputers(String name) throws DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        int nbFoundComputers = computerDAO.getNumberOfSearchedComputers(name);
        try {
            connection.close();
            Transaction.removeTransaction();
            return nbFoundComputers;
        } catch (SQLException e) {
            throw new DAOException("[getNumberOfSearchedComputers] " + e.getMessage());
        }
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) throws DateException, DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        List<ComputerDTO> computers = computerMapper.getByPage(computerDAO.findByLimit(offset, max));
        try {
            connection.close();
            Transaction.removeTransaction();
            return computers;
        } catch (SQLException e) {
            throw new DAOException("[findComputersByLimit] " + e.getMessage());
        }
    }

    /**
     * @param name of computer to search
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> searchComputers(String name, int offset, int max) throws DateException, DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        List<ComputerDTO> computers = computerMapper.getByPage(computerDAO.searchByLimit(name, offset, max));
        try {
            connection.close();
            Transaction.removeTransaction();
            return computers;
        } catch (SQLException e) {
            throw new DAOException("[findComputersByLimit] " + e.getMessage());
        }
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void createComputer(Computer comp) throws DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        if (connection != null) {
            try {
                computerDAO.create(comp);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException("[createComputer] " + e1.getMessage());
                }
                throw new DAOException("[createComputer] " + e.getMessage());
            } finally {
                try {
                    connection.close();
                    Transaction.removeTransaction();
                } catch (SQLException e) {
                    throw new DAOException("[createComputer] " + e.getMessage());
                }
            }
        }
    }

    /**
     * @param compId the ID of computer to delete from database
     * @exception DAOException .
     */
    public void deleteComputer(int compId) throws DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        if (connection != null) {
            try {
                computerDAO.delete(compId);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException("[deleteComputer] " + e1.getMessage());
                }
                throw new DAOException("[deleteComputer] " + e.getMessage());
            } finally {
                try {
                    connection.close();
                    Transaction.removeTransaction();
                } catch (SQLException e) {
                    throw new DAOException("[deleteComputer] " + e.getMessage());
                }
            }
        }
    }

    /**
     * @param comp the computer to update
     * @exception DAOException .
     */
    public void updateComputer(Computer comp) throws DAOException {
        Connection connection = Persistence.getInstance().getConnection();
        Transaction.setTransaction(connection);
        if (connection != null) {
            try {
                computerDAO.update(comp);
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DAOException("[updateComputer] " + e1.getMessage());
                }
                throw new DAOException("[updateComputer] " + e.getMessage());
            } finally {
                try {
                    connection.close();
                    Transaction.removeTransaction();
                } catch (SQLException e) {
                    throw new DAOException("[updateComputer] " + e.getMessage());
                }
            }
        }
    }
}
