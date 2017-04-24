package fr.ebiz.computer_database.service;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;

public class ComputerServiceTest {
	
	private final ComputerService cs = new ComputerService();
	private final int nbOfComputers = 575;  
	
	@Test
	public void testFindComputerById() {
		ComputerDTO comp = new ComputerDTO(new Computer.ComputerBuilder("MacBook Pro 15.4 inch")
									.id(1)
									.company_id(1)
									.build());

		try {
			assertEquals(cs.findComputerById(1), comp);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetNumberOfComputers() {
		try {
			assertEquals(cs.getNumberOfComputers(), nbOfComputers);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testFindComputersByLimit() {
		assertTrue(true);
	}

	@Test
	public void testCreateComputer() {
		assertTrue(true);
	}

	@Test
	public void testDeleteComputer() {
		assertTrue(true);
	}

	@Test
	public void testUpdateComputer() {
		assertTrue(true);
	}

}
