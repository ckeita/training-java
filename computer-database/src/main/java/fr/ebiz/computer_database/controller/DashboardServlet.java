package fr.ebiz.computer_database.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
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
    private boolean searchState;
    private boolean orderby;
    private boolean asc = true;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ComputerDTO> computers = null;
        int offset = 1;
        int curPage = 1;

        if (request.getParameter("current") == null && request.getParameter("search") == null
                && request.getParameter("limit") == null) {
            searchState = false;
        }
        if (request.getParameter("current") != null) {
            offset = Integer.parseInt(request.getParameter("current"));
            curPage = offset;
        }
        if (request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }
        if ((search = request.getParameter("search")) != null) {
            this.getServletContext().setAttribute("search", search.trim());
            searchState = true;
        }
        if (request.getParameter("orderby") != null) {
            orderby = true;
            switch (request.getParameter("orderby")) {
            case "name":
                computers = (List<ComputerDTO>) this.getServletContext().getAttribute("computers");
                System.out.println(computers);
                if (asc) {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = false;
                            return o1.getName().compareToIgnoreCase(o2.getName());
                        }
                    });
                } else {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = true;
                            return o2.getName().compareToIgnoreCase(o1.getName());
                        }
                    });
                }

                System.out.println(computers);
                break;
            case "introduced":
                computers = (List<ComputerDTO>) this.getServletContext().getAttribute("computers");
                System.out.println(computers);
                if (asc) {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = false;
                            if (o1.getIntroduced() != null && o2.getIntroduced() != null) {
                                return LocalDate.parse(o1.getIntroduced(), Util.TO_FORMATTER)
                                        .compareTo(LocalDate.parse(o2.getIntroduced(), Util.TO_FORMATTER));
                            } else {
                                return 1;
                            }
                        }
                    });
                } else {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = true;
                            if (o1.getIntroduced() != null && o2.getIntroduced() != null) {
                                return LocalDate.parse(o2.getIntroduced(), Util.TO_FORMATTER)
                                        .compareTo(LocalDate.parse(o1.getIntroduced(), Util.TO_FORMATTER));
                            } else {
                                return 1;
                            }
                        }
                    });
                }

                System.out.println(computers);
                break;
            case "discontinued":
                computers = (List<ComputerDTO>) this.getServletContext().getAttribute("computers");
                System.out.println(computers);
                if (asc) {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = false;
                            if (o1.getDiscontinued() != null && o2.getDiscontinued() != null) {
                                return LocalDate.parse(o1.getDiscontinued(), Util.TO_FORMATTER)
                                        .compareTo(LocalDate.parse(o2.getDiscontinued(), Util.TO_FORMATTER));
                            } else {
                                return 1;
                            }
                        }
                    });
                } else {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            asc = true;
                            if (o1.getDiscontinued() != null && o2.getDiscontinued() != null) {
                                return LocalDate.parse(o2.getDiscontinued(), Util.TO_FORMATTER)
                                        .compareTo(LocalDate.parse(o1.getDiscontinued(), Util.TO_FORMATTER));
                            } else {
                                return 1;
                            }
                        }
                    });
                }
                System.out.println(computers);
                break;
            case "company":
                computers = (List<ComputerDTO>) this.getServletContext().getAttribute("computers");
                System.out.println(computers);
                if (asc) {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            if (o1.getCompany() != null && o2.getCompany() != null) {
                                asc = false;
                                return o1.getCompany().compareToIgnoreCase(o2.getCompany());
                            } else {
                                return 1;
                            }
                        }
                    });
                } else {
                    Collections.sort(computers, new Comparator<ComputerDTO>() {
                        @Override
                        public int compare(ComputerDTO o1, ComputerDTO o2) {
                            if (o1.getCompany() != null && o2.getCompany() != null) {
                                asc = true;
                                return o2.getCompany().compareToIgnoreCase(o1.getCompany());
                            } else {
                                return 1;
                            }
                        }
                    });
                }
                System.out.println(computers);
                break;
            default:
                break;
            }
        } else {
            orderby = false;
        }
        try {
            if (!searchState && !orderby) {
                nbComputers = computerService.getNumberOfComputers();
                computers = computerService.findComputersByLimit((offset - 1) * limit, limit);
                // this.getServletContext().setAttribute("computers",
                // computers);
            } else if (searchState) {
                computers = computerService.searchComputers((String) this.getServletContext().getAttribute("search"),
                        (offset - 1) * limit, limit);
                nbComputers = computerService
                        .getNumberOfSearchedComputers((String) this.getServletContext().getAttribute("search"));
            }

            nbPages = nbComputers / limit;
            nbLinks = nbPages / limit;

            request.setAttribute("computers", computers);
            this.getServletContext().setAttribute("computers", computers);
            request.setAttribute("nbComputers", nbComputers);
            request.setAttribute("nbPages", nbPages);
            request.setAttribute("nbLinks", nbLinks);
            request.setAttribute("curPage", curPage);

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
            computerService.deleteComputer(Integer.parseInt(request.getParameter("selection")));
            response.sendRedirect(Util.DASH_REDIRECT);
        } catch (DAOException | NumberFormatException e) {
            System.out.println(e.getMessage());
            response.sendError(Util.ERROR_500);
        }
    }
}
