package fr.ebiz.computer_database.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.exceptions.DateException;
import fr.ebiz.computer_database.mapper.ComputerMapper;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.persistence.ComputerDAO;

/**
 * @author ckeita
 */
public class ComputerService {

//    private static ComputerDAO computerDAO = new ComputerDAO();
//    private static (new computerMapper()) (new computerMapper()) = new (new computerMapper())();
    
    private static Logger LOGGER = LoggerFactory.getLogger(ComputerService.class);

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public ComputerDTO findComputerById(int id) throws DAOException, DateException  {
        return (new ComputerMapper()).getById(new ComputerDAO().findById(id));
    }

    /**
     * @return the number of computers
     * @throws DAOException .
     */
    public int getNumberOfComputers() throws DAOException {
        return new ComputerDAO().getNumberOfComputers();
    }

    /**
     * @param name to search
     * @return the number of searched computers
     * @throws DAOException .
     */
    public int getNumberOfSearchedComputers(String name) throws DAOException {
        return new ComputerDAO().getNumberOfSearchedComputers(name);
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) throws DateException, DAOException {
        return (new ComputerMapper()).getByPage((new ComputerDAO()).findByLimit(offset, max));
    }

    /**
     * @param orderBy for ordering
     * @param order for ascendant or descendant
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByOrder(String orderBy, String order) throws DateException, DAOException {
        LOGGER.info("orderby " + orderBy + " order " + order);
        return (new ComputerMapper()).getByPage(new ComputerDAO().findByOrder(orderBy, order));
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
        return (new ComputerMapper()).getByPage(new ComputerDAO().searchByLimit(name, offset, max));
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void createComputer(Computer comp) throws DAOException {
    	new ComputerDAO().create(comp);
    }

    /**
     * @param compId the ID of computer to delete from database
     * @exception DAOException .
     */
    public void deleteComputer(int compId) throws DAOException {
    	new ComputerDAO().delete(compId);
    }

    /**
     * @param comp the computer to update
     * @exception DAOException .
     */
    public void updateComputer(Computer comp) throws DAOException {
    	new ComputerDAO().update(comp);
    }
}
