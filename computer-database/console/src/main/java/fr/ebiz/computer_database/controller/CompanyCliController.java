package fr.ebiz.computer_database.controller;

import java.util.Scanner;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.ui.CompanyUI;

/**
 * @author ebiz
 */
public class CompanyCliController {

    CompanyUI companyUI;
    private boolean back;

    /**
     * the default controller.
     */
    public CompanyCliController() {
        companyUI = new CompanyUI();
        back = false;
    }
    /**
     * @throws DAOException .
     */
    public void dispatchView() throws DAOException {
        while (!back) {
            Scanner input = new Scanner(System.in);
            String choice;

            System.out.println("****Company Management****");
            System.out.println("0 => Show all companies\n1 => Back to main menu");
            choice = input.next();
            switch (choice) {
            case "0":
                // List all companies
                companyUI.viewCompany();
                break;
            case "1":
                // Set 'back' to go back to main menu
                back = true;
                break;
            default:
                System.out.println("Please choose a number between 0 and 1");
                break;
            }
        }
    }
}
