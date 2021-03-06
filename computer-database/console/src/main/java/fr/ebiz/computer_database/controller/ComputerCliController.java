package fr.ebiz.computer_database.controller;

import java.util.Scanner;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.ui.ComputerUI;

/**
 * @author ebiz
 */
public class ComputerCliController {

    ComputerUI computerUI;
    private boolean back;

    /**
     * the default controller.
     */
    public ComputerCliController() {
        computerUI = new ComputerUI();
        back = false;
    }
    /**
     * @throws DAOException .
     */
    public void dispatchView() throws DAOException {
        while (!back) {
            Scanner input = new Scanner(System.in);
            String choice;

            System.out.println("****Computer Management****");
            System.out.println(
                    "0 => Show All Computers\n1 => Show Computer details\n2 => Create Computer\n3 => Update Computer\n4 => Delete Computer\n5 => Back to Main Menu");
            choice = input.next();
            switch (choice) {
            case "0":
                // List all computers
                computerUI.viewComputer();
                break;
            case "1":
                // Show Computer details
                computerUI.showComputerDetails();
                break;
            case "2":
                // Create new Computer
                computerUI.createOrUpdateComputer(false);
                break;
            case "3":
                // Update a Computer
                computerUI.createOrUpdateComputer(true);
                break;
            case "4":
                // Delete a Computer
                computerUI.deleteComputer();
                break;
            case "5":
                // Set 'back' to go back to main menu
                back = true;
                break;
            default:
                System.out.println("Please choose a number between 0 and 4");
                break;
            }
        }
    }
}
