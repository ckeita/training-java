/**
 * 
 */
package service;

import java.util.List;

import mapper.CompanyDAO;
import model.Company;

/**
 * @author ckeita
 *
 */
public class CompanyService {
	
	CompanyDAO companyDAO;
	
	public CompanyService () {
		companyDAO = new CompanyDAO();
	}
	
	/**
	 * @param id
	 * @return the company found
	 */
	public Company findCompanyById (int id) {
		return companyDAO.findById(id);
	}
	
	/**
	 * @return the list af all companies
	 */
	public List<Company> findAllCompanies () {
		return companyDAO.findAll();
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return the list of computers
	 */
	public List<Company>  findCompaniesByLimit (int offset, int max) {
		return companyDAO.findByLimit(offset, max);
	}
}
