package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.persistence.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public CompanyDTO findCompanyById(int id) throws DAOException {
        return companyMapper.getById(companyDAO.findById(id));
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers
     * @exception DAOException .
     */
    @Transactional
    public List<CompanyDTO> findCompaniesByLimit(int offset, int max) throws DAOException {
        return companyMapper.getByPage(companyDAO.findByLimit(offset, max));
    }
}
