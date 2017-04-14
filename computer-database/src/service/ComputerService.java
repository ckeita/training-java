/**
 * 
 */
package service;

import java.util.List;

import mapper.ComputerMapper;
import model.Computer;
import persistence.ComputerDAO;

/**
 * @author ckeita
 *
 */
public class ComputerService {
	
	private ComputerDAO computerDAO;
	private ComputerMapper computerMapper;
	
	public ComputerService () {
		computerDAO = new ComputerDAO();
		computerMapper = new ComputerMapper();
	}
	
	/**
	 * @param the id of the computer
	 * @return the string of the object
	 */
	public String  findComputerById (int id) {
		return computerMapper.getById(id);
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return the list of computers
	 */
	public List<String>  findComputersByLimit (int offset, int max) {
		return computerMapper.getByPage(offset, max);
	}
	
	/**
	 * @param comp: the computer to put on database
	 */
	public void createComputer (Computer comp) {
		computerDAO.create(comp);
	}
	
	/**
	 * @param comp: the computer to delete from database
	 */
	public void deleteComputer (Computer comp) {
		computerDAO.delete(comp);
	}
	
	/**
	 * @param comp: the computer to update 
	 */
	public void updateComputer(Computer comp) {
		computerDAO.update(comp);
	}
}
