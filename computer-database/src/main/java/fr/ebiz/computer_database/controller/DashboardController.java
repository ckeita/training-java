package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.ComputerService;
import fr.ebiz.computer_database.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by ebiz on 23/05/17.
 */
@Controller
public class DashboardController {
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

	private static  final String CURRENT_PAGE = "current";
	private static  final String PAGE_LIMIT = "limit";
	private static  final String ORDER_COLUMN = "column";
	private static  final String SORT_ORDER = "order";
	private static final String PARAM_SEARCH = "search";
	private static final String COMPUTERS_ORDERED = "computersOrdered";
	private static final String COMPUTERS = "computers";
	private static final String NB_COMPUTERS = "nbComputers";
	private static final String NB_PAGES = "nbPages";
	private static final String NB_LINKS = "nbLinks";
	private static final String CUR_PAGE = "curPage";
	private static final String SELECTION = "selection";

	private static final String DASHBOARD_VIEW = "dashboard";
	private static final String REDIRECT = "redirect:";

	private static Logger logger = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	ServletContext servletContext;

	@Autowired
	ComputerService computerService;

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String dashboard(@RequestParam(value = CURRENT_PAGE, required = false) String current, @RequestParam(value = PAGE_LIMIT, required = false) String limit,
	                        @RequestParam(value = ORDER_COLUMN, required = false) String column, @RequestParam(value = SORT_ORDER, required = false) String order,
	                        @RequestParam(value = PARAM_SEARCH, required = false) String search, Model model) {
		List<ComputerDTO> computers = null;
		List<ComputerDTO> computersOrdered = null;
		int offset = 1;
		int curPage = 1;

		if (current == null && search == null && limit == null && column == null) {
			searchState = false;
		}
		if (current == null && search == null && limit == null) {
			searchState = false;
			orderby = false;
		}
		if (current != null) {
			offset = Integer.parseInt(current);
			curPage = offset;
		}
		if (limit != null) {
			this.limit = Integer.parseInt(limit);
		}
		if (search != null) {
			this.search = search;
			this.servletContext.setAttribute(Util.PARAM_SEARCH, search.trim());
			searchState = true;
		}
		if (column != null) {
			orderby = true;
			if (order != null) {
				try {
					computersOrdered = computerService.findComputersByOrder(column,	order);
					this.servletContext.setAttribute(COMPUTERS_ORDERED, computersOrdered);
				} catch (DateException | DAOException | NumberFormatException e) {
					logger.info(e.getMessage());
					return Util.PAGE_500;
				}
				if (order.equals(Util.ASC)) {
					this.order = Util.DESC;
				} else {
					this.order = Util.ASC;
				}
			}
		}
		try {
			if (!searchState && !orderby) {
				nbComputers = computerService.getNumberOfComputers();
				computers = computerService.findComputersByLimit((offset - 1) * this.limit, this.limit);
			} else if (searchState) {
				computers = computerService.searchComputers(
						(String) this.servletContext.getAttribute(Util.PARAM_SEARCH), (offset - 1) * this.limit, this.limit);
				nbComputers = computerService.getNumberOfSearchedComputers(
						(String) this.servletContext.getAttribute(Util.PARAM_SEARCH));
			} else if (orderby) {
				List<ComputerDTO> ordered = (List<ComputerDTO>) this.servletContext.getAttribute(COMPUTERS_ORDERED);
				offset = (offset - 1);
				curLimit = offset + this.limit;
				if (curLimit > nbComputers) {
					curLimit = ordered.size();
				}
				computers = ordered.subList(offset, curLimit);
			}

			nbPages = nbComputers / this.limit;
			nbLinks = nbPages / this.limit;

			model.addAttribute(COMPUTERS, computers);
			this.servletContext.setAttribute(COMPUTERS, computers);
			model.addAttribute(NB_COMPUTERS, nbComputers);
			model.addAttribute(NB_PAGES, nbPages);
			model.addAttribute(NB_LINKS, nbLinks);
			model.addAttribute(CUR_PAGE, curPage);
			model.addAttribute(SORT_ORDER, this.order);

			return DASHBOARD_VIEW;
		} catch (DateException | DAOException | NumberFormatException e) {
			logger.info(e.getMessage());
			return Util.PAGE_500;
		}
	}

	@RequestMapping(value = "dashboard", method = RequestMethod.POST)
	public String deleteComputer(@RequestParam(value = SELECTION) String[] selection) {
		try {
			for (String id : selection) {
			  computerService.deleteComputer(Integer.parseInt(id));
			}
			return REDIRECT+DASHBOARD_VIEW;
		} catch (NumberFormatException e) {
			logger.info(e.getMessage());
			return Util.PAGE_500;
		}
	}

}
