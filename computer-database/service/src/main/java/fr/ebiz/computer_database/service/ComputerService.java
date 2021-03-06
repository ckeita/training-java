package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.mapper.ComputerMapper;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.persistence.ComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ckeita
 */
@Service
@Transactional
public class ComputerService {
    private static Logger logger = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private ComputerDAO computerDAO;
    @Autowired
    private ComputerMapper computerMapper;

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public ComputerDTO findComputerById(int id) throws ServiceException {
        try {
            return computerMapper.getById(computerDAO.findById(id));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findAll() throws ServiceException {
        try {
            return computerMapper.getAll(computerDAO.findAll());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @return the number of computers
     * @throws DAOException .
     */
    //@Transactional
    public long getNumberOfComputers() throws DAOException {
        return computerDAO.getNumberOfComputers();
    }

    /**
     * @param name to search
     * @return the number of searched computers
     * @throws DAOException .
     */
    //@Transactional
    public long getNumberOfSearchedComputers(String name) throws DAOException {
        return computerDAO.getNumberOfSearchedComputers(name);
    }

    /**
     * @param current for the first row
     * @param limit number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByLimit(int current, int limit) throws ServiceException {
        try {
            return computerMapper.getAll(computerDAO.findByLimit(current, limit));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param orderBy for ordering
     * @param order for ascendant or descendant
     * @param current for the first row
     * @param limit number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByOrder(String orderBy, String order, int current, int limit) throws DateException, DAOException {
        logger.info("orderby " + orderBy + " order " + order);
        return computerMapper.getAll(computerDAO.findByOrder(orderBy, order, current, limit));
    }

    /**
     * @param name of computer to search
     * @param current of the first row
     * @param limit number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> searchComputers(String name, int current, int limit) throws DateException, DAOException {
        return computerMapper.getAll(computerDAO.searchByLimit(name, current, limit));
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void createComputer(Computer comp) throws ServiceException {
        try {
            computerDAO.create(comp);
        } catch (DAOException e) {
            logger.info(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param compId the ID of computer to delete from database
     * @exception DAOException .
     */
    public void deleteComputer(int compId) throws ServiceException {
        try {
            computerDAO.delete(compId);
        } catch (DAOException e) {
            logger.info(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param comp the computer to update
     * @exception DAOException .
     */
    public void updateComputer(Computer comp) {
        try {
            computerDAO.update(comp);
        } catch (DAOException e) {
            logger.info(e.getMessage());
        }
    }
}