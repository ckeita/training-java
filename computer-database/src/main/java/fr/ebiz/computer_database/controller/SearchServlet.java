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
import fr.ebiz.computer_database.exceptions.DateException;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.ComputerService;

/**
 * Servlet implementation class SearchServlet.
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ComputerService computerService = new ComputerService();

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = null;
        List<ComputerDTO> computers = null;
        if (request.getParameter(Util.PARAM_SEARCH) != null) {
            search = request.getParameter(Util.PARAM_SEARCH);
        }
        try {
            computers = computerService.searchComputer(search);
            // this.getServletContext().setAttribute("searchState",
            // "searchState");
            // this.getServletContext().setAttribute("computers", computers);
            // this.getServletContext().setAttribute("nbComputers",
            // computers.size());
            request.setAttribute("searchState", "searchState");
            request.setAttribute("computers", computers);
            request.setAttribute("nbComputers", computers.size());
            System.out.println(computers.size());
            System.out.println(computers);
            // this.getServletContext().getRequestDispatcher("/" +
            // Util.DASH_REDIRECT).forward(request, response);
            response.sendRedirect(Util.DASH_REDIRECT);
        } catch (DateException | DAOException e) {
            e.getMessage();
            response.sendError(Util.ERROR_500);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendError(Util.ERROR_403);
    }

}
