package fr.ebiz.computer_database.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/add_computer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String name;
	private String introduced;
	private String discontinued;
	private String company;
	
	private ComputerService computerService = new ComputerService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    this.getServletContext().getRequestDispatcher(Util.ADD_COMPUTER_VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    name = request.getParameter(Util.PARAM_NAME);
	    introduced = request.getParameter(Util.PARAM_INTRODUCED);
	    discontinued = request.getParameter(Util.PARAM_DISCONTINUED);
	    company = request.getParameter(Util.PARAM_COMPANY_ID);

	    try {
            computerService.createComputer(new Computer.ComputerBuilder(name)
            										.introduced(LocalDate.parse(introduced, Util.TO_FORMATTER))
            										.discontinued(LocalDate.parse(discontinued, Util.TO_FORMATTER))
            										.company_id(Integer.parseInt(company))
            										.build());
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
