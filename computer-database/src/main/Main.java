package main;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

import Utils.Util;
import controller.CompanyController;
import controller.ComputerController;
import mapper.CompanyDAO;
import mapper.ComputerDAO;
import model.Company;
import model.Computer;
import service.ComputerService;

/**
 * @author ckeita
 *
 */
public class Main {	
	
	public static void main(String[] args) {
		//CompanyDAO cmp = new CompanyDAO();
		//ComputerService cmt = new ComputerService();

		//System.out.println(cmt.findComputerById(1));
//		Computer c = new Computer();
//		System.out.println(cmt.findAllComputers().toString());
//		c.setName("mac book pro 13\" pouces");
//		cmt.createComputer(c);
		//c.setId(578);
		//c.setCompany_id(1);
		//cmt.updateComputer(c);
		//c.setIntroduced(LocalDateTime.parse(new String("1986-01-16 00:00:00"),Util.FORMATTER));
		//c.setDiscontinued(LocalDateTime.parse(new String("1990-10-15 00:00:00"),Util.FORMATTER));
		//cmt.create(c);
		
		//The boolean to handle the application exit
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
				//Initialize the controller and dispatch
				CompanyController cmpy = new CompanyController();
				cmpy.dispatchView();
				break;
			case "1":
				ComputerController cmpr = new ComputerController();
				cmpr.dispatchView();
				break;
			case "2":
				//Set 'quit' to exit the application
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
