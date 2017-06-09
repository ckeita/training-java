package fr.ebiz.computer_database.controller;

import fr.ebiz.computer_database.exception.ServiceException;
import fr.ebiz.computer_database.handler.PageHandler;
import fr.ebiz.computer_database.mapper.ComputerMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.service.CompanyService;
import fr.ebiz.computer_database.service.ComputerService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by ebiz on 02/06/17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/")
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

	@RequestMapping(value = "computers/{id}", method = RequestMethod.GET)
	public ResponseEntity getComputer(@PathVariable int id) {
		try {
			return new ResponseEntity(computerService.findComputerById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "computers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteComputer(@PathVariable int id) {
		try {
			computerService.deleteComputer(id);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "companies/{id}", method = RequestMethod.GET)
	public ResponseEntity getCompany(@PathVariable int id) {
		try {
			return new ResponseEntity(companyService.findCompanyById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "computers", method = RequestMethod.POST)
	public ResponseEntity addComputer(@RequestBody ComputerDTO computerDTO) {
		try {
			computerService.createComputer(computerMapper.mapToObject(computerDTO));
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "computers", method = RequestMethod.PUT)
	public ResponseEntity editComputer(@RequestBody ComputerDTO computerDTO) {
		try {
			computerService.updateComputer(computerMapper.mapToObject(computerDTO));
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = "computers", method = RequestMethod.GET)
	public ResponseEntity computersByPage(@RequestParam Map<String, String> params) {
		try {
			return new ResponseEntity(pageHandler.getPage(params), HttpStatus.OK);
		} catch (Exception  e) {
			logger.info(e.getMessage());
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
