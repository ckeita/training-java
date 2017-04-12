/**
 * 
 */
package controller;

import java.util.Scanner;
import service.ComputerService;
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
			System.out.println("0 => Show all companies\n1 => Back to main menu");
			choice = input.next();
			switch (choice) {
			case "0":
				//List all computers
				computerUI.viewComputer((new ComputerService()).findAllComputers());
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
