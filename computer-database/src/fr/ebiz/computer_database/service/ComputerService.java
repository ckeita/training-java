/**
 * 
 */
package fr.ebiz.computer_database.service;

import java.sql.ResultSet;
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

    private ComputerDAO computerDAO;
    private ComputerMapper computerMapper;

    public ComputerService() {
        computerDAO = new ComputerDAO();
        computerMapper = new ComputerMapper();
    }

    /**
     * @param the id of the computer
     * @return the string of the object
     * @throws DAOException 
     */
    public ComputerDTO findComputerById(int id) throws DAOException {
        ResultSet resultSet = computerDAO.findById(id);
        ComputerDTO comp = computerMapper.getById(resultSet);
        System.out.println(comp);
        return comp;
    }

    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return the list of computers
     * @throws DAOException 
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) throws DAOException {
        ResultSet resultSet = computerDAO.findByLimit(offset, max);
        return computerMapper.getByPage(resultSet);
    }

    /**
     * @param comp: the computer to put on database
     * @throws DAOException 
     */
    public void createComputer(Computer comp) throws DAOException {
        computerDAO.create(comp);
    }

    /**
     * @param comp: the computer to delete from database
     */
    public void deleteComputer(Computer comp) {
        computerDAO.delete(comp);
    }

    /**
     * @param comp: the computer to update
     */
    public void updateComputer(Computer comp) {
        computerDAO.update(comp);
    }
}
