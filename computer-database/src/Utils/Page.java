/**
 * 
 */
package Utils;

import java.util.List;
import java.util.Scanner;

import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import service.CompanyService;
import service.ComputerService;

/**
 * @author ckeita
 * @param <T>
 *
 */
public class Page {
	
	private int numberOfRow;
	private int offset;
	private boolean finish;
	private boolean instanceOfComputer;
	private List<?> list;
	
	/**
	 * @param <T>
	 * @param numberOfRow: the number of elements by one page
	 * @param list: the list of element page
	 */
	public Page(int numberOfRow, List<String> list, boolean instanceOfComputer) {
		this.numberOfRow = numberOfRow;
		this.list = list;
		finish = false;
		this.instanceOfComputer = instanceOfComputer;
	}
	
	/**
	 * Process the paging
	 */
	public void paging () {
		
		Scanner input = new Scanner(System.in);
		CompanyMapper companyMapper = new CompanyMapper();
		ComputerMapper computerMapper = new ComputerMapper();
		String choice; 
		boolean fromNext = false;
		while (!finish) {
			System.out.println("0 => Next>> "+numberOfRow+" Elements\n1 => Back<<\n2 => Return to previous menu");
			choice = input.next();
			switch (choice) {
			case "0":
				/**List next numberOfRow Computers*/
				//check instance of elements in list
				if (instanceOfComputer) {
					list = computerMapper.getByPage(offset, numberOfRow);
				} else {
					list = companyMapper.getByPage(offset, numberOfRow);
				}
				//Set offset to next page
				offset += numberOfRow;
				
				if (list.size() != 0) {
					//show the current page
					printElements();
					fromNext = true;
				} else { //the end is reached
					System.out.println("*You reach the end*\n");
				}
				break;
			case "1":
				/**List previous numberOfRow Computers*/
				//Set offset to previous page
				if(offset > 0) {
					if (offset - numberOfRow >= 0) {
						//offset -= numberOfRow;
						if (fromNext) {
							offset -= 2*numberOfRow;
							fromNext = false;
						} else {
							offset -= numberOfRow;
						}
					} else {
						offset = 0;
					}
					if (instanceOfComputer) {
						list = computerMapper.getByPage(offset, numberOfRow);
					} else {
						list = companyMapper.getByPage(offset, numberOfRow);
					}
					//show the current page
					printElements();
				} else { //the end is reached
					System.out.println("*You reach the top*\n");
				}
				break;
			case "2":
				//Set 'finish' to go back to main menu
				finish = true;
				break;
			default:
				System.out.println("Please choose a number between 0 and 2\n");
				break;
			}
		}
	}
	
	/**
	 * Print the current page
	 */
	private void printElements () {
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
		System.out.println();
	}
	
	
}
