package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import fr.ebiz.computer_database.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ebiz on 22/05/17.
 */
@Controller("addcomputer")
public class AddComputerController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	ComputerService computerService;

	private static Logger logger = LoggerFactory.getLogger(AddComputerController.class);

	private static final String  COMPANIES = "companies";
	private static final String  ADD_PAGE = "add_computer";
	private static final String  DASHBOARD = "redirect:dashboard";

	//@RequestMapping(name = "addcomputer", method = RequestMethod.GET)
	@GetMapping
	public String getAddPage(Model model) {
		try {
			model.addAttribute(COMPANIES, companyService.findAll());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return ADD_PAGE;
	}

	//@RequestMapping(name = "addcomputer", method = RequestMethod.POST)
	@PostMapping
	public String addComputer(@ModelAttribute("computerToAdd") ComputerDTO computerToAdd) {
		try {
			computerService.createComputer(new Computer.ComputerBuilder(computerToAdd.getName()).introduced(computerToAdd.getIntroduced())
					.discontinued(computerToAdd.getDiscontinued()).companyId(computerToAdd.getCompanyId()).build());
		} catch (DateException | NumberFormatException e) {
			logger.info(e.getMessage());
			return Util.PAGE_500;
		}
		return DASHBOARD;
	}
}
