/**
 * 
 */
package fr.ebiz.computer_database.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ebiz.computer_database.Utils.Page;
import fr.ebiz.computer_database.model.Company;

/**
 * @author ebiz
 */
public class CompanyUI {

    public void viewCompany(Company cmp) {
    }

    public void viewCompany() {
        System.out.println("****List of Companies****");
        // To check instance of list elements
        List<String> list = new ArrayList<>();
        boolean finish = false;
        String choice;
        Scanner input = new Scanner(System.in);
        while (!finish) {
            System.out.println("***Choose the number of elements by page***");
            choice = input.next();
            try {
                if (Integer.parseInt(choice) > 0) {// Need at least one element
                                                   // by page
                    // process the paging
                    Page p = new Page(Integer.parseInt(choice), list, false);
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
}
