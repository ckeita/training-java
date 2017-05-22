package fr.ebiz.computer_database.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.computer_database.model.Company;
import fr.ebiz.computer_database.model.CompanyDTO;
import org.springframework.stereotype.Component;

/**
 * @author ckeita
 */
@Component
public class CompanyMapper {
    /**
     * @param comp to translate
     * @return the string of the object
     */
    public CompanyDTO getById(Company comp) {
        return new CompanyDTO(comp);
    }

    /**
     * @param companies to translate
     * @return the list of companies
     */
    public List<CompanyDTO> getAll(List<Company> companies) {
        List<CompanyDTO> list = new ArrayList<>();
        for (Company company : companies) {
            list.add(new CompanyDTO(company));
        }
        return list;
    }
}
