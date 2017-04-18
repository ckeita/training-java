/**
 * 
 */
package fr.ebiz.computer_database.service;

import java.sql.ResultSet;
import java.util.List;

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
     */
    public ComputerDTO findComputerById(int id) {
        ResultSet resultSet = computerDAO.findById(id);
        return computerMapper.getById(resultSet);
    }

    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return the list of computers
     */
    public List<ComputerDTO> findComputersByLimit(int offset, int max) {
        ResultSet resultSet = computerDAO.findByLimit(offset, max);
        return computerMapper.getByPage(resultSet);
    }

    /**
     * @param comp: the computer to put on database
     */
    public void createComputer(Computer comp) {
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
