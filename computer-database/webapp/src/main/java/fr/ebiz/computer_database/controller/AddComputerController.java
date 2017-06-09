package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.exception.ServiceException;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ebiz on 22/05/17.
 */
@Controller()
public class AddComputerController {

	@Autowired
	private CompanyService companyService;
	@Autowired
	private ComputerService computerService;
	@Autowired
	private ComputerDTOValidation computerDTOValidation;
	@Autowired
	CompanyMapper companyMapper;

	private static Logger logger = LoggerFactory.getLogger(AddComputerController.class);

	private static final String  COMPANIES = "companies";
	private static final String  ADD_PAGE = "add_computer";
	private static final String  DASHBOARD = "redirect:dashboard";

	/**
	 * @param binder
	 */
	@InitBinder
	public  void initBinder(WebDataBinder binder) {
		binder.addValidators(computerDTOValidation);
	}

	/**
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "addcomputer", method = RequestMethod.GET)
	public String getAddPage(Model model) {
		try {
			model.addAttribute(COMPANIES, companyService.findAll());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return ADD_PAGE;
	}

	/**
	 *
	 * @param computerToAdd
	 * @return
	 */
	@RequestMapping(value = "addcomputer", method = RequestMethod.POST)
	public String addComputer(@Validated ComputerDTO computerToAdd) {
		try {
			logger.info("[CREATE] computerDTO " + computerToAdd);
			logger.info("-------------------------computerDTO----------------------- " + computerToAdd.getCompanyId());
			if (computerToAdd.getCompanyId() != 0) {
				CompanyDTO companyDTO = companyService.findCompanyById(computerToAdd.getCompanyId());
				computerService.createComputer(new Computer.ComputerBuilder(computerToAdd.getName()).introduced(computerToAdd.getIntroduced())
						.discontinued(computerToAdd.getDiscontinued()).company(companyMapper.mapToObject(companyDTO)).build());
			} else {
				computerService.createComputer(new Computer.ComputerBuilder(computerToAdd.getName()).introduced(computerToAdd.getIntroduced())
						.discontinued(computerToAdd.getDiscontinued()).company(null).build());
			}

		} catch (ServiceException | DateException | NumberFormatException e) {
			logger.info(e.getMessage());
			return Util.PAGE_500;
		}
		return DASHBOARD;
	}
}
