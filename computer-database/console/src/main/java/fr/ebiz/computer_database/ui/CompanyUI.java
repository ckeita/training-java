package fr.ebiz.computer_database.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ebiz.computer_database.util.Page;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.CompanyDTO;

/**
 * @author ebiz
 */
public class CompanyUI {

    /**
     * @throws DAOException .
     */
    public void viewCompany() throws DAOException {
        System.out.println("****List of Companies****");
        // To check instance of list elements
        List<CompanyDTO> list = new ArrayList<>();
        boolean finish = false;
        String choice;
        Scanner input = new Scanner(System.in);
        while (!finish) {
            System.out.println("***Choose the number of elements by page***");
            choice = input.next();
            try {
                // Need at least one element by page
                if (Integer.parseInt(choice) > 0) {
                    // process the paging
                    new Page().paging(Integer.parseInt(choice), false);
                    finish = true;
                } else {
                    System.out.println("Please choose a valid number");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please choose a valid number");
            }
        }
    }
}
