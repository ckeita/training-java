package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.exception.ServiceException;
import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.persistence.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ckeita
 */
@Service
@Transactional
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
    public CompanyDTO findCompanyById(int id) throws ServiceException {
        try {
            return companyMapper.mapToDTO(companyDAO.findById(id));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param offset of the first row
     * @param max number of rows
     * @return the list of computers
     * @exception DAOException .
     */
    public List<CompanyDTO> findCompaniesByLimit(int offset, int max) throws ServiceException {
        try {
            return companyMapper.getAll(companyDAO.findByLimit(offset, max));
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @return the list of computers
     * @exception DAOException .
     */
    public List<CompanyDTO> findAll() throws ServiceException {
        try {
            return companyMapper.getAll(companyDAO.findAll());
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
