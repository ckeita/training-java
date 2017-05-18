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
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet implementation class EditComputerServlet.
 */
@WebServlet("/edit_computer")
public class EditComputerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String company;

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCompt = Integer.parseInt(request.getParameter(Util.PARAM_ID));
        try {
            ComputerDTO computer = computerService.findComputerById(idCompt);
            request.setAttribute("computer", computer);
            List<CompanyDTO> companies;
            companies = companyService.findCompaniesByLimit(Util.OFFSET, Util.ALL_COMPANIES);
            request.setAttribute("companies", companies);
            this.getServletContext().getRequestDispatcher(Util.EDIT_COMPUTER_VIEW).forward(request, response);

        } catch (DateException | DAOException | NumberFormatException e) {
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
        id = request.getParameter(Util.PARAM_ID);
        name = request.getParameter(Util.PARAM_NAME);
        introduced = request.getParameter(Util.PARAM_INTRODUCED);
        discontinued = request.getParameter(Util.PARAM_DISCONTINUED);
        company = request.getParameter(Util.PARAM_COMPANY_ID);

        try {
            computerService.updateComputer(new Computer.ComputerBuilder(name).id(Integer.parseInt(id))
                    .introduced(introduced).discontinued(discontinued).companyId(Integer.parseInt(company)).build());
        } catch (DateException | DAOException | NumberFormatException e) {
            e.getMessage();
            response.sendError(Util.ERROR_500);
        }
        response.sendRedirect(Util.DASH_REDIRECT);
    }

}
