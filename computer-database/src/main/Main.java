package main;

import java.sql.SQLException;

import mapper.CompanyDAO;
import model.Company;

/**
 * @author ckeita
 *
 */
public class Main {	
	
	public static void main(String[] args) {
		CompanyDAO c = new CompanyDAO();
		try {
			System.out.println(c.findById(2).toString());
			Company comp = new Company();
			comp.setName("mac in tosh");
			c.create(comp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
