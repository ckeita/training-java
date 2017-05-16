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
 * Servlet implementation class DashboardServlet.
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ComputerService computerService = new ComputerService();
    private int nbComputers;
    private int nbPages;
    private int nbLinks;
    private int limit = 10;
    private String search;
    private String order = Util.ASC;
    private boolean searchState;
    private boolean orderby;
    private boolean asc = true;
    private int curLimit;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ComputerDTO> computers = null;
        List<ComputerDTO> computersOrdered = null;
        int offset = 1;
        int curPage = 1;

        if (request.getParameter(Util.CURRENT_PAGE) == null && request.getParameter(Util.PARAM_SEARCH) == null
                && request.getParameter(Util.PAGE_LIMIT) == null && request.getParameter(Util.ORDER_COLUMN) == null) {
            searchState = false;
        }
        if (request.getParameter(Util.CURRENT_PAGE) == null && request.getParameter(Util.PARAM_SEARCH) == null
                && request.getParameter(Util.PAGE_LIMIT) == null) {
            searchState = false;
            orderby = false;
        }
        if (request.getParameter(Util.CURRENT_PAGE) != null) {
            offset = Integer.parseInt(request.getParameter(Util.CURRENT_PAGE));
            curPage = offset;
        }
        if (request.getParameter(Util.PAGE_LIMIT) != null) {
            limit = Integer.parseInt(request.getParameter(Util.PAGE_LIMIT));
        }
        if (request.getParameter(Util.PARAM_SEARCH) != null) {
            search = request.getParameter(Util.PARAM_SEARCH);
            this.getServletContext().setAttribute(Util.PARAM_SEARCH, search.trim());
            searchState = true;
        }
        if (request.getParameter(Util.ORDER_COLUMN) != null) {
            orderby = true;

            if (request.getParameter(Util.SORT_ORDER) != null) {
                try {
                    computersOrdered = computerService.findComputersByOrder(request.getParameter(Util.ORDER_COLUMN),
                            request.getParameter(Util.SORT_ORDER));
                    this.getServletContext().setAttribute("computersOrdered", computersOrdered);
                } catch (DateException | DAOException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                    response.sendError(Util.ERROR_500);
                }
                if (request.getParameter(Util.SORT_ORDER).equals(Util.ASC)) {
                    order = Util.DESC;
                } else {
                    order = Util.ASC;
                }
            }
        }
        try {
            if (!searchState && !orderby) {
                nbComputers = computerService.getNumberOfComputers();
                computers = computerService.findComputersByLimit((offset - 1) * limit, limit);
            } else if (searchState) {
                computers = computerService.searchComputers(
                        (String) this.getServletContext().getAttribute(Util.PARAM_SEARCH), (offset - 1) * limit, limit);
                nbComputers = computerService.getNumberOfSearchedComputers(
                        (String) this.getServletContext().getAttribute(Util.PARAM_SEARCH));
            } else if (orderby) {
                List<ComputerDTO> ordered = (List<ComputerDTO>) this.getServletContext()
                        .getAttribute("computersOrdered");
                offset = (offset - 1);
                curLimit = offset + limit;
                if (curLimit > nbComputers) {
                    curLimit = ordered.size();
                }
                computers = ordered.subList(offset, curLimit);
            }

            nbPages = nbComputers / limit;
            nbLinks = nbPages / limit;

            request.setAttribute("computers", computers);
            this.getServletContext().setAttribute("computers", computers);
            request.setAttribute("nbComputers", nbComputers);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("nbLinks", nbLinks);
            request.setAttribute("curPage", curPage);
            request.setAttribute(Util.SORT_ORDER, order);

            this.getServletContext().getRequestDispatcher(Util.DASHBOARD_VIEW).forward(request, response);
        } catch (DateException | DAOException | NumberFormatException e) {
            System.out.println(e.getMessage());
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
        try {
            String[] listId = request.getParameterValues("selection");
            for (String id : listId) {
                computerService.deleteComputer(Integer.parseInt(id));
            }
            // computerService.deleteComputer(Integer.parseInt(request.getParameter("selection")));
            response.sendRedirect(Util.DASH_REDIRECT);
        } catch (DAOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            response.sendError(Util.ERROR_500);
        }
    }
}
