package fr.ebiz.computer_database.controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String name;
	private String introduced;
	private String discontinued;
	private String company;
	
	private ComputerService computerService = new ComputerService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		// TODO Auto-generated method stub
	    Computer comp = new Computer();
	    name = request.getParameter(Util.NAME);
	    introduced = request.getParameter(Util.INTRODUCED);
	    discontinued = request.getParameter(Util.DISCONTINUED);
	    company = request.getParameter(Util.COMPANY_ID);
	    System.out.println(introduced);
	    comp.setName(name);
	    comp.setIntroduced(LocalDateTime.parse(introduced+Util.STR_HOUR, Util.IN_FORMATTER));
	    comp.setDiscontinued(LocalDateTime.parse(discontinued+Util.STR_HOUR, Util.IN_FORMATTER));
	    comp.setCompany_id(Integer.parseInt(company));
	    computerService.createComputer(comp);
		response.getWriter().print("Not Added bitch");
	}

}
