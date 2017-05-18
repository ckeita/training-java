package fr.ebiz.computer_database.main;

import java.util.Scanner;

import fr.ebiz.computer_database.controller.CompanyController;
import fr.ebiz.computer_database.controller.ComputerController;
import fr.ebiz.computer_database.exception.DAOException;

/**
 * @author ckeita
 */
public class Main {

    /**
     * @param args of the program
     */
    public static void main(String[] args) {

        // The boolean to handle the application exit
        boolean quit = false;
        /**
         * The application main loop
         */
        while (!quit) {
            Scanner input = new Scanner(System.in);
            String choice;

            System.out.println("****Make your choice****");
            System.out.println("0 => Manage companies\n1 => Manage computers\n2 => Quit");
            choice = input.next();
            switch (choice) {
            case "0":
                // Initialize the controller and dispatch
                CompanyController cmpy = new CompanyController();
                try {
                    cmpy.dispatchView();
                } catch (DAOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "1":
                ComputerController cmpr = new ComputerController();
                try {
                    cmpr.dispatchView();
                } catch (DAOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "2":
                // Set 'quit' to exit the application
                quit = true;
                break;
            default:
                System.out.println("Please choose a number between 0 and 2");
                break;
            }
        }
        System.out.println("Exit bye :)");
    }
}
