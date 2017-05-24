package fr.ebiz.computer_database.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.computer_database.mapper.ComputerDaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author ckeita
 */
@Repository
public class ComputerDAO {
    private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public Computer findById(int id) throws DAOException, DateException {
        Computer computer = null;
        try {
            computer = this.jdbcTemplate.queryForObject(
                Util.COMPUTER_BY_ID,
                new Object[]{id},
                new ComputerDaoMapper());
            logger.info("[findById] Found element from database");
            return computer;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findAll() throws DAOException, DateException {
        List<Computer> computers = null;
        try {
            computers = this.jdbcTemplate.query(
                    Util.ALL_COMPUTERS,
                    new ComputerDaoMapper());
            logger.info("[findAll] Found " + computers.size() + " elements from database");
            return computers;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @return number of computers in database
     * @throws DAOException .
     */
    public int getNumberOfComputers() throws DAOException {
        int number = 0;
        try {
            number = this.jdbcTemplate.queryForObject(Util.GET_NUMBER_OF_COMPUTERS, Integer.class);
            logger.info("[getNumberOfComputers] get number of computers");
            return number;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param name to search
     * @return number of searched computers in database
     * @throws DAOException .
     */
    public int getNumberOfSearchedComputers(String name) throws DAOException {
        int number = 0;
        try {
            number = this.jdbcTemplate.queryForObject(Util.GET_NUMBER_OF_SEARCHED_COMPUTERS, new Object[]{"%" + name + "%"}, Integer.class);
            logger.info("[getNumberOfComputers] get number of searched computers");
            return number;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param current of the first row
     * @param limit number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> findByLimit(int current, int limit) throws DAOException, DateException {
        List<Computer> computers = null;
        try {
            computers = this.jdbcTemplate.query(
                    Util.COMPUTERS_BY_LIMIT,
                    new Object[]{current, limit},
                    new ComputerDaoMapper());
            logger.info("[findByLimit] Found " + computers.size() + " elements from database");
            return computers;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
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
        List<Computer> computers = null;
        try {
            computers = this.jdbcTemplate.query(
                Util.COMPUTERS_BY_ORDER+ orderBy + " " + order,
                new ComputerDaoMapper());
            logger.info("[findByLimit] Found " + computers.size() + " elements from database");
            return computers;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param name of computer to search
     * @param current of the first row
     * @param limit number of rows
     * @return resultSet
     * @throws DateException .
     * @throws DAOException .
     */
    public List<Computer> searchByLimit(String name, int current, int limit) throws DAOException, DateException {
        List<Computer> computers = null;
        try {
        computers = this.jdbcTemplate.query(
            Util.SEARCH_COMPUTERS,
            new Object[]{"%"+ name + "%", current, limit},
            new ComputerDaoMapper());
            logger.info("[searchByLimit] Found " + computers.size() + " elements from database");
            return computers;
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void create(Computer comp) throws DAOException {
        String company = null;
        if (comp.getCompanyId() != 0) {
            company = String.valueOf(comp.getCompanyId());
        }
        try{
            this.jdbcTemplate.update(Util.CREATE_COMPUTER, comp.getName(),
                    comp.getIntroduced(), comp.getDiscontinued(),
                    company);
            logger.info("[create] Inserted successfully");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param compId the ID of computer to delete from database
     * @throws SQLException
     * @exception DAOException .
     */
    public void delete(int compId) throws DAOException {
        try{
            this.jdbcTemplate.update(Util.DELETE_COMPUTER, compId);
            logger.info("[delete] Deleted successfully");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * @param comp the computer to update
     * @throws SQLException
     * @exception DAOException .
     */
    public void update(Computer comp) throws DAOException {
        String company = null;
        if (comp.getCompanyId() != 0) {
            company = String.valueOf(comp.getCompanyId());
        }
        logger.info("[update] computer " + comp);
        try{
            this.jdbcTemplate.update(Util.UPDATE_COMPUTER, comp.getName(),
                    comp.getIntroduced(), comp.getDiscontinued(),
                    company, comp.getId());
            logger.info("[update] Updated successfully");
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }
}
