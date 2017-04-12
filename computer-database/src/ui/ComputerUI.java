/**
 * 
 */
package ui;

import java.util.List;

import Utils.Page;
import model.Computer;

/**
 * @author ebiz
 *
 */
public class ComputerUI {
	
	/**
	 * @param list: the list of all computers
	 */
	public void viewComputer (List<Computer> list) {
		Page p = new Page<Computer>(5,list);
		p.paging();
	}
}
