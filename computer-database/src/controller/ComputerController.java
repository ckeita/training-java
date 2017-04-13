/**
 * 
 */
package controller;

import java.util.Scanner;
import ui.ComputerUI;

/**
 * @author ebiz
 *
 */
public class ComputerController {
	
	ComputerUI computerUI;
	private boolean back;
	
	public ComputerController () {
		computerUI = new ComputerUI();
		back = false;
	}
	
	/**
	 * Dispatch towards the good view
	 */
	public void dispatchView () {
		while (!back) {
			Scanner input = new Scanner(System.in);
			String choice; 
			
			System.out.println("****Computer Management****");
			System.out.println("0 => Show All Computers\n1 => Create Computer\n2 => Update Computer\n3 => Delete Computer\n4 => Back to Main Menu");
			choice = input.next();
			switch (choice) {
			case "0":
				//List all computers
				computerUI.viewComputer();
				break;
			case "1":
				//Create new Computer
				computerUI.createOrUpdateComputer(false);
				break;
			case "2":
				//Update a Computer
				computerUI.createOrUpdateComputer(true);
				break;
			case "3":
				//Delete a Computer
				computerUI.deleteComputer();
				break;
			case "4":
				//Set 'back' to go back to main menu
				back = true;
				break;
			default:
				System.out.println("Please choose a number between 0 and 4");
				break;
			}
		}
	}
}
