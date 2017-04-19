package fr.ebiz.computer_database.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.ComputerService;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/edit_computer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ComputerService computerService = new ComputerService();   
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int id_compt = Integer.parseInt(request.getParameter(Util.PARAM_ID));
        System.out.println("id: "+id_compt);
        try {
            ComputerDTO computer = computerService.findComputerById(id_compt);
            System.out.println(computer);
            request.setAttribute("computer", computer);            
            this.getServletContext().getRequestDispatcher(Util.EDIT_COMPUTER_VIEW).forward(request, response);
            
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
