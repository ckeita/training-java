package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.persistence.CompanyDAO;

/**
 * @author ckeita
 */
public class CompanyService {

    private static CompanyMapper companyMapper;
    private static CompanyDAO companyDAO;

    public void setCompanyMapper(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    public void setCompanyDAO(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;
    }

    /**
     * @param id of the computer
     * @return the string of the object
     * @exception DAOException .
     */
    public CompanyDTO findCompanyById(int id) throws DAOException {
        return companyMapper.getById(companyDAO.findById(id));
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers
     * @exception DAOException .
     */
    public List<CompanyDTO> findCompaniesByLimit(int offset, int max) throws DAOException {
        return companyMapper.getByPage(companyDAO.findByLimit(offset, max));
    }
}
