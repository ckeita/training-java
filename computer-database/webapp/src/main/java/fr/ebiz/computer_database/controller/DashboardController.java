package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.handler.PageHandler;
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

import java.util.Map;

/**
 * Created by ebiz on 23/05/17.
 */
@Controller
public class DashboardController {

	private static  final String SORT_ORDER = "order";
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
	ComputerService computerService;

	@Autowired
	PageHandler pageHandler;

	@RequestMapping(value = "dashboard", method = RequestMethod.GET)
	public String dashboard(@RequestParam(required = false) Map<String, String> params, Model model) {

		logger.info("[PARAM_SIZE]", params.size());
		model.addAttribute(COMPUTERS, pageHandler.getPage(params));
		model.addAttribute(NB_COMPUTERS, pageHandler.getNbComputers());
		model.addAttribute(NB_PAGES, pageHandler.getNbPages());
		model.addAttribute(NB_LINKS, pageHandler.getNbLinks());
		model.addAttribute(CUR_PAGE, pageHandler.getCurPage());
		model.addAttribute(SORT_ORDER, pageHandler.getSortPage());
		return DASHBOARD_VIEW;
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
