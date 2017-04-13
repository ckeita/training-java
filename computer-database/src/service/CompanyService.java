/**
 * 
 */
package service;

import java.util.List;

import mapper.CompanyMapper;
import model.Company;
import persistence.CompanyDAO;

/**
 * @author ckeita
 *
 */
public class CompanyService {
	
	CompanyDAO companyDAO;
	CompanyMapper companyMapper;
	
	public CompanyService () {
		companyDAO = new CompanyDAO();
		companyMapper = new CompanyMapper();
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
	public List<String> findAllCompanies () {
		return companyMapper.getAll();
	}
	
	/**
	 * @param offset: Offset of the first row
	 * @param max: number of rows
	 * @return the list of computers
	 */
	public List<String>  findCompaniesByLimit (int offset, int max) {
		return companyMapper.getByPage(offset, max);
	}
}
