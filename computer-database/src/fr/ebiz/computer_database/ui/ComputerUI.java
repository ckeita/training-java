/**
 * 
 */
package fr.ebiz.computer_database.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ebiz.computer_database.Utils.Page;
import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.ComputerService;

/**
 * @author ebiz
 */
public class ComputerUI {

    ComputerService computerService = new ComputerService();
    private int id;
    private String name;
    private LocalDateTime introduced;
    private LocalDateTime discontinued;
    private int company_id;
    /**
     * @param list: the list of all computers
     */
    public void viewComputer() {
        List<ComputerDTO> list = new ArrayList<>();
        // To check instance of list elements
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
                    Page p = new Page(Integer.parseInt(choice), list, true);
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
     * Create or Update a new computer
     */
    public void createOrUpdateComputer(boolean update) {
        //Computer comp = new Computer();
        Scanner input = new Scanner(System.in);
        boolean checkDates = false, intrValid = false, discValid = false;
        String name, intrDate = null, discDate = null, cmpId, computId = null;

        // Choose if we create or update
        if (update) {
            System.out.println("***Update Computer***");
            do {
                System.out.println("Choose The id of the computer to update");
                computId = input.nextLine();
            } while (computId.length() == 0);
            this.id = Integer.parseInt(computId);
        } else {
            System.out.println("***Create new Computer***");
        }

        // Make sure that the name is set because it's mandatory
        do {
            System.out.println("Set name");
            name = input.nextLine();
        } while (!update && name.length() == 0);
        this.name = name;

        // Handle the dates properly
        while (!checkDates) {

            /**
             * Check if the introduced date is well set and formated
             */
            while (!intrValid) {
                System.out.println("Set introduced date");
                intrDate = input.nextLine();
                if (intrDate.length() != 0) {// introduced date is set
                    if (isValidDate(intrDate)) {// introduced date is valid
                        this.introduced = LocalDateTime.parse(intrDate, Util.IN_FORMATTER);
                        intrValid = true;
                    } else {// introduced date is not valid
                        intrValid = false;
                    }
                } else {// introduced date is not set
                    intrValid = true;
                }
            }
            /**
             * Check if the discontinued date is well set and formated
             */
            while (!discValid) {
                System.out.println("Set discontinued date");
                discDate = input.nextLine();
                if (discDate.length() != 0) {// discontinued date is set
                    if (isValidDate(discDate)) {// discontinued date is valid
                        this.discontinued = LocalDateTime.parse(discDate, Util.IN_FORMATTER);
                        discValid = true;
                    } else {// discontinued date is not valid
                        discValid = false;
                    }
                } else {// discontinued date is not set
                    discValid = true;
                }
            }

            if (intrDate.length() == 0 || discDate.length() == 0) {// Accept the
                                                                   // dates are
                                                                   // not set
                checkDates = true;
            } else if (intrDate.length() != 0 && discDate.length() != 0) {// Check
                                                                          // if
                                                                          // the
                                                                          // dates
                                                                          // valid
                                                                          // if
                                                                          // they
                                                                          // are
                                                                          // set
                checkDates = checkDates(introduced, discontinued);
                if (!checkDates) {// Do not Accept the dates if they are not
                                  // valid
                    intrValid = false;
                    discValid = false;
                }
            }
        }
        System.out.println("Set company ID");
        cmpId = input.nextLine();
        if (cmpId.length() != 0) {
            company_id = Integer.parseInt(cmpId);
        }

        // Process Update or Delete
        if (update) {
            computerService.updateComputer(new Computer.ComputerBuilder(name)
            											.introduced(introduced)
            											.discontinued(discontinued)
            											.company_id(company_id)
            											.id(id)
            											.build());
        } else {
            computerService.createComputer(new Computer.ComputerBuilder(name)
							.introduced(introduced)
							.discontinued(discontinued)
							.company_id(company_id)
							.build());
        }
    }

    /**
     * Delete a computer
     */
    public void deleteComputer() {

        Scanner input = new Scanner(System.in);
        String computId;

        System.out.println("***Delete Computer***");

        do {
            System.out.println("Choose The id of the computer to delete");
            computId = input.nextLine();
        } while (computId.length() == 0);
        this.id = Integer.parseInt(computId);

        // Process delete
        computerService.deleteComputer(new Computer.ComputerBuilder()
													.id(id)
													.build());
    }

    public void showComputerDetails() {
        String computId;
        Scanner input = new Scanner(System.in);

        System.out.println("***Show Computer Details***");

        do {
            System.out.println("Choose The id of the computer to show");
            computId = input.nextLine();
        } while (computId.length() == 0);

        System.out.println(computerService.findComputerById(Integer.parseInt(computId)));
    }

    /**
     * @param intrDate: The introduced date
     * @param discDate: the discontinued date
     * @return 'true' if discontinued date is greater than The introduced date
     *         and 'false' if not
     */
    private boolean checkDates(LocalDateTime intrDate, LocalDateTime discDate) {

        if (intrDate != null && discDate != null) {
            if (!intrDate.isBefore(discDate)) {
                System.out.println("The discontinued date must be greater than the introduced date");
            }
            return intrDate.isBefore(discDate);
        }
        return false;
    }

    /**
     * @param input: the string of the date to check the format
     * @return 'true' if it's well formatted and 'false' if not
     */
    private boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat(Util.IN_FORMAT);
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            System.out.println("Set a valid date: the format is " + Util.IN_FORMAT);
            return false;
        }
    }
}
