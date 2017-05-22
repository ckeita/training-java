package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.persistence.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ckeita
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyDAO companyDAO;

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
        return companyMapper.getAll(companyDAO.findByLimit(offset, max));
    }

    /**
     * @return the list of computers
     * @exception DAOException .
     */
    public List<CompanyDTO> findAll() throws DAOException {
        return companyMapper.getAll(companyDAO.findAll());
    }
}
