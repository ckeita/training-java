/**
 * 
 */
package service;

import java.util.List;
import mapper.ComputerDAO;
import model.Computer;

/**
 * @author ckeita
 *
 */
public class ComputerService {
	
	private ComputerDAO computerDAO;
	
	public ComputerService () {
		computerDAO = new ComputerDAO();
	}
	
	/**
	 * @param the id of the computer
	 * @return the string of the object
	 */
	public String  findComputerById (int id) {
		return computerDAO.findById(id);
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return the list of computers
	 */
	public List<Computer>  findComputersByLimit (int offset, int max) {
		return computerDAO.findByLimit(offset, max);
	}
	
	/**
	 * @return the list all computers
	 */
	public List<Computer> findAllComputers () {
		return computerDAO.findAll();
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
