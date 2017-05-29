package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.validation.ComputerDTOValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ebiz on 22/05/17.
 */
@Controller("editcomputer")
public class EditComputerController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	ComputerService computerService;
	@Autowired
	private ComputerDTOValidation computerDTOValidation;
	@Autowired
	CompanyMapper companyMapper;

	private static Logger logger = LoggerFactory.getLogger(EditComputerController.class);

	private static final String COMPUTER = "computer";
	private static final String  COMPANIES = "companies";
	private static final String  EDIT_PAGE = "edit_computer";
	private static final String  DASHBOARD = "redirect:dashboard";

	/**
	 * @param binder
	 */
	@InitBinder
	public  void initBinder(WebDataBinder binder) {
		binder.addValidators(computerDTOValidation);
	}

	@RequestMapping(value = "editcomputer", method = RequestMethod.GET)
	public String getEditPage(@RequestParam(value = "id", required = true) int id, Model model) {
		try {
			model.addAttribute(COMPUTER, computerService.findComputerById(id));
			model.addAttribute(COMPANIES, companyService.findAll());

		} catch (DateException | DAOException | NumberFormatException e) {
			return Util.PAGE_500;
		}
		return EDIT_PAGE;
	}

	@RequestMapping(value = "editcomputer", method = RequestMethod.POST)
	public String editComputer(ComputerDTO computerToEdit) {
		try {
			CompanyDTO companyDTO = companyService.findCompanyById(Integer.parseInt(computerToEdit.getCompanyDTO().getId()));
			computerService.updateComputer(new Computer.ComputerBuilder(computerToEdit.getName()).id(Integer.parseInt(computerToEdit.getId())).introduced(computerToEdit.getIntroduced())
					.discontinued(computerToEdit.getDiscontinued()).company(companyMapper.mapToObject(companyDTO)).build());
		} catch (DAOException | DateException | NumberFormatException e) {
			logger.info(e.getMessage());
			return Util.PAGE_500;
		}
		return DASHBOARD;
	}
}
