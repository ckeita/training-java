/**
 * 
 */
package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Utils.Page;
import Utils.Util;
import model.Computer;
import service.ComputerService;

/**
 * @author ebiz
 *
 */
public class ComputerUI {
	
	ComputerService computerService = new ComputerService();
	/**
	 * @param list: the list of all computers
	 */
	public void viewComputer () {
		List<Computer> list = new ArrayList<>();
		//To check instance of list elements
		boolean finish = false;
		String choice;
		Scanner input = new Scanner(System.in);
		while (!finish) {
			System.out.println("***Choose the number of elements by page***");
			choice = input.next();
			try {
				if (Integer.parseInt(choice) > 0) {//Need at least one element by page
					//process the paging
					Page p = new Page(Integer.parseInt(choice),list,true);
					p.paging();
					finish = true;
				} else {
					System.out.println("Please choose a valid number");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please choose a valid number");
			}
		}
	}
	
	/**
	 * Create a new computer 
	 */
	public void createComputer () {
		Computer comp = new Computer();
		Scanner input = new Scanner(System.in);
		boolean checkDates = false, parsableDate = false, intrValid = false, discValid = false;
		String name, intrDate=null, discDate=null, cmpId;
		System.out.println("***Create new Computer***");
		
		//Make sure that the name is set because it's mandatory
		do{
			System.out.println("Set name");
			name = input.nextLine();
		} while (name.length() == 0);
		comp.setName(name);
		
		while(!checkDates) {
//			while (!parsableDate) {
//				try {
//					System.out.println("Set introduced date");
//					intrDate = input.nextLine();
//					if (intrDate.length() != 0) {
//						comp.setIntroduced(LocalDateTime.parse(intrDate,Util.FORMATTER));;
//					}		
//					System.out.println("Set discontinued date");
//					discDate = input.nextLine();
//					if (discDate.length() != 0) {
//						comp.setDiscontinued(LocalDateTime.parse(discDate,Util.FORMATTER));
//					}
//					
//					if (intrDate.length() == 0 || discDate.length() == 0) {
//						checkDates = true;
//					} else if (intrDate.length() != 0 && discDate.length() != 0) {
//						checkDates = checkDates(comp.getIntroduced(), comp.getDiscontinued());
//					}
//				} catch (DateTimeParseException ex) {
//					System.out.println("Set a valid date: the format is yyyy-MM-dd HH:mm:ss");
//				}
//			}
			while (!intrValid) {
				System.out.println("Set introduced date");
				intrDate = input.nextLine();
				if (intrDate.length() != 0) {
					if (isValidDate(intrDate)) {
						comp.setIntroduced(LocalDateTime.parse(intrDate,Util.IN_FORMATTER));
						intrValid = true;
					} else {
						intrValid = false;
					}
				} else {
					intrValid = true;
				}
			} 
			
			while (!discValid); {
				System.out.println("Set discontinued date");
				discDate = input.nextLine();
				if (discDate.length() != 0) {
					if (isValidDate(discDate)) {
						comp.setDiscontinued(LocalDateTime.parse(discDate,Util.IN_FORMATTER));
						discValid = true;
					} else {
						discValid = false;
					}	
				} else {
					discValid = true;
				}
			} 
			
			if (intrDate.length() == 0 || discDate.length() == 0) {
				checkDates = true;
			} else if (intrDate.length() != 0 && discDate.length() != 0) {
				checkDates = checkDates(comp.getIntroduced(), comp.getDiscontinued());
			}
		} 
		System.out.println("Set company ID");
		cmpId = input.nextLine();
		if (cmpId.length() != 0) {
			comp.setCompany_id(Integer.parseInt(cmpId));
		}		
		//Computer comp = new Computer(0, name, LocalDateTime.parse(intrDate,Util.FORMATTER), LocalDateTime.parse(discDate,Util.FORMATTER), Integer.parseInt(cmpId));
		//computerService.createComputer(comp);
	}
	
	
	private boolean checkDates (LocalDateTime intrDate, LocalDateTime discDate) {
		if (intrDate != null && discDate != null) {
			if (!intrDate.isBefore(discDate)) {
				System.out.println("The discontinued date must be greater than the introduced date");
			}
			return intrDate.isBefore(discDate);
		}
		return false;
	}

	boolean isValidDate(String input) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     try {
	          format.parse(input);
	          return true;
	     }
	     catch(ParseException e){
	    	 System.out.println("Set a valid date: the format is yyyy-MM-dd HH:mm:ss");
	         return false;
	     }
	}
}
