/**
 * 
 */
package controller;

import java.util.Scanner;

import service.CompanyService;
import ui.CompanyUI;

/**
 * @author ebiz
 *
 */
public class CompanyController {
	
	CompanyUI companyUI;
	private boolean back;
	
	public CompanyController () {
		companyUI = new CompanyUI();
		back = false;
	}
	
	/**
	 * Dispatch towards the good view
	 */
	public void dispatchView () {
		while (!back) {
			Scanner input = new Scanner(System.in);
			String choice; 
			
			System.out.println("****Company Management****");
			System.out.println("0 => Show all companies\n1 => Back to main menu");
			choice = input.next();
			switch (choice) {
			case "0":
				//List all companies
				companyUI.viewCompany((new CompanyService()).findAllCompanies());
				break;
			case "1":
				//Set 'back' to go back to main menu
				back = true;
				break;
			default:
				System.out.println("Please choose a number between 0 and 1");
				break;
			}
		}
	}
}
