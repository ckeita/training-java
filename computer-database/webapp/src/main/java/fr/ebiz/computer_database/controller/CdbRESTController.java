package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.ServiceException;
import fr.ebiz.computer_database.handler.PageHandler;
import fr.ebiz.computer_database.mapper.ComputerMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ebiz on 02/06/17.
 */
@RestController
public class CdbRESTController {

	@Autowired
	private ComputerService computerService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ComputerMapper computerMapper;

	@Autowired
	private PageHandler pageHandler;

	private static Logger logger = LoggerFactory.getLogger(CdbRESTController.class);

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

	@RequestMapping(value = "/api/computer/add", method = RequestMethod.POST)
	public void addComputer (@RequestBody ComputerDTO computerDTO) {
		computerService.createComputer(computerMapper.mapToObject(computerDTO));
	}

	@RequestMapping(value = "/api/computer/edit", method = RequestMethod.PUT)
	public void editComputer (@RequestBody ComputerDTO computerDTO) {
		computerService.createComputer(computerMapper.mapToObject(computerDTO));
	}

	@RequestMapping(value = "/api/computers/paging", method = RequestMethod.GET)
	public List<ComputerDTO> computersByPage (@RequestParam Map<String, String> params) {
		try {
			return pageHandler.getPage(params);
		} catch (ServiceException  e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/api/computers/search", method = RequestMethod.GET)
	public List<ComputerDTO> searchComputers (@RequestParam Map<String, String> params) {
		try {
			return pageHandler.getPage(params);
		} catch (ServiceException  e) {
			logger.info(e.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/api/computers/order", method = RequestMethod.GET)
	public List<ComputerDTO> orderComputers (@RequestParam Map<String, String> params) {
		try {
			return pageHandler.getPage(params);
		} catch (ServiceException  e) {
			logger.info(e.getMessage());
			return null;
		}
	}
}
