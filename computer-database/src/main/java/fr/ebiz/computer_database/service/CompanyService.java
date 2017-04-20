/**
 * 
 */
package fr.ebiz.computer_database.service;

import java.sql.ResultSet;
import java.util.List;

import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.persistence.CompanyDAO;

/**
 * @author ckeita
 */
public class CompanyService {

    CompanyMapper companyMapper;
    CompanyDAO companyDAO;

    public CompanyService() {
        companyMapper = new CompanyMapper();
        companyDAO = new CompanyDAO();
    }
    
    /**
     * @param the id of the computer
     * @return the string of the object
     */
    public CompanyDTO findCompanyById(int id) {
        ResultSet resultSet = companyDAO.findById(id);
        return companyMapper.getById(resultSet);
    }
    
    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return the list of computers
     */
    public List<CompanyDTO> findCompaniesByLimit(int offset, int max) {
        ResultSet resultSet = companyDAO.findByLimit(offset, max);
        return companyMapper.getByPage(resultSet);
    }
}
