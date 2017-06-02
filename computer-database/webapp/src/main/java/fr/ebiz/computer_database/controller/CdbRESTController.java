package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.ServiceException;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ebiz on 02/06/17.
 */
@RestController
public class CdbRESTController {

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@RequestMapping(value = "/api/companies", method = RequestMethod.GET)
	public List<CompanyDTO> getCompanies () throws ServiceException {
		return companyService.findAll();
	}

	@RequestMapping(value = "/api/computers", method = RequestMethod.GET)
	public List<ComputerDTO> getComputers () throws ServiceException {
		return computerService.findAll();
	}

	@RequestMapping(value = "/api/computer/id/{id}", method = RequestMethod.GET)
	public ComputerDTO getComputer (@PathVariable int id) throws ServiceException {
		return computerService.findComputerById(id);
	}

	@RequestMapping(value = "/api/computer/id/{id}", method = RequestMethod.DELETE)
	public void deleteComputer (@PathVariable int id) throws ServiceException {
		computerService.deleteComputer(id);
	}

}
