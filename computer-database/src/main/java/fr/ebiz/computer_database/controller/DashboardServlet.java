package fr.ebiz.computer_database.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private ComputerService computerService = new ComputerService();
    private int nbComputers;
    private int nbPages;
    private int nbLinks;
    private int limit = 10;
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ComputerDTO> computers = null;
        int offset = 1;
        int curPage = 1;
        if (request.getParameter("current") != null) {
        	offset = Integer.parseInt(request.getParameter("current"));
        	curPage = offset;
        }
        if (request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }
        try {
            nbComputers = computerService.getNumberOfComputers();
            computers = computerService.findComputersByLimit((offset-1)*limit, limit);
            nbPages = nbComputers/limit;
            nbLinks = nbPages/limit;
            
            request.setAttribute("computers", computers);
            request.setAttribute("nbComputers", nbComputers);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("nbLinks", nbLinks);
            request.setAttribute("curPage", curPage);
            System.out.println(computers);
            this.getServletContext().getRequestDispatcher(Util.DASHBOARD_VIEW).forward(request, response);
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
