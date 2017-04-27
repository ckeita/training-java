package fr.ebiz.computer_database.service;

import java.util.List;

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

    private static ComputerDAO computerDAO = new ComputerDAO();
    private static ComputerMapper computerMapper = new ComputerMapper();

    /**
     * @param id of the computer
     * @return the string of the object
     * @throws DateException .
     * @throws DAOException .
     */
    public ComputerDTO findComputerById(int id) throws DateException, DAOException {
        return computerMapper.getById(computerDAO.findById(id));
    }

    /**
     * @return the number of computers
     * @throws DAOException .
     */
    public int getNumberOfComputers() throws DAOException {
        return computerDAO.getNumberOfComputers();
    }

    /**
     * @param name to search
     * @return the number of searched computers
     * @throws DAOException .
     */
    public int getNumberOfSearchedComputers(String name) throws DAOException {
        return computerDAO.getNumberOfSearchedComputers(name);
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) throws DateException, DAOException {
        return computerMapper.getByPage(computerDAO.findByLimit(offset, max));
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
        return computerMapper.getByPage(computerDAO.searchByLimit(name, offset, max));
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void createComputer(Computer comp) throws DAOException {
        computerDAO.create(comp);
    }

    /**
     * @param compId the ID of computer to delete from database
     * @exception DAOException .
     */
    public void deleteComputer(int compId) throws DAOException {
        computerDAO.delete(compId);
    }

    /**
     * @param comp the computer to update
     * @exception DAOException .
     */
    public void updateComputer(Computer comp) throws DAOException {
        computerDAO.update(comp);
    }

    /**
     * @param name to search
     * @return the list of computers DTO
     * @throws DAOException .
     * @throws DateException .
     */
    public List<ComputerDTO> searchComputer(String name) throws DateException, DAOException {
        return computerMapper.getByPage(computerDAO.search(name));
    }
}
