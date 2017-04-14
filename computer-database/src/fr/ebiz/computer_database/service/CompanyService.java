/**
 * 
 */
package fr.ebiz.computer_database.service;

import java.util.List;

import fr.ebiz.computer_database.mapper.CompanyMapper;

/**
 * @author ckeita
 */
public class CompanyService {

    CompanyMapper companyMapper;

    public CompanyService() {
        companyMapper = new CompanyMapper();
    }

    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return the list of computers
     */
    public List<String> findCompaniesByLimit(int offset, int max) {
        return companyMapper.getByPage(offset, max);
    }
}