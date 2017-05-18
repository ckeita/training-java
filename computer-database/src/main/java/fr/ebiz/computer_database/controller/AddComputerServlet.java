package fr.ebiz.computer_database.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class AddComputerServlet.
 */
@WebServlet("/add_computer")
public class AddComputerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private String name;
    private String introduced;
    private String discontinued;
    private String company;

    @Autowired
    private ComputerService computerService;
    @Autowired
    private CompanyService companyService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request.
     * @param response.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CompanyDTO> companies;
        try {
            companies = companyService.findCompaniesByLimit(Util.OFFSET, Util.ALL_COMPANIES);
            request.setAttribute("companies", companies);
        } catch (DAOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.getServletContext().getRequestDispatcher(Util.ADD_COMPUTER_VIEW).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        name = request.getParameter(Util.PARAM_NAME);
        introduced = request.getParameter(Util.PARAM_INTRODUCED);
        discontinued = request.getParameter(Util.PARAM_DISCONTINUED);
        company = request.getParameter(Util.PARAM_COMPANY_ID);

        try {
            computerService.createComputer(new Computer.ComputerBuilder(name).introduced(introduced)
                    .discontinued(discontinued).companyId(Integer.parseInt(company)).build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // response.sendError(Util.ERROR_500);
            response.sendError(Util.ERROR_500);
        }
        response.sendRedirect(Util.DASH_REDIRECT);
    }

}
