package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exceptions.DAOException;
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
     * @throws DAOException .
     */
    public ComputerDTO findComputerById(int id) throws DAOException {
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
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers DTO
     * @throws DAOException .
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) throws DAOException {
        return computerMapper.getByPage(computerDAO.findByLimit(offset, max));
    }

    /**
     * @param comp the computer to put on database
     * @throws DAOException .
     */
    public void createComputer(Computer comp) throws DAOException {
        computerDAO.create(comp);
    }

    /**
     * @param comp the computer to delete from database
     */
    public void deleteComputer(Computer comp) {
        computerDAO.delete(comp);
    }

    /**
     * @param comp the computer to update
     */
    public void updateComputer(Computer comp) {
        computerDAO.update(comp);
    }
}
