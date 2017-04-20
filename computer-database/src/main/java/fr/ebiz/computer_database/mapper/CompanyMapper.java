/**
 * 
 */
package fr.ebiz.computer_database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.ebiz.computer_database.model.Company;
import fr.ebiz.computer_database.model.CompanyDTO;

/**
 * @author ckeita
 */
public class CompanyMapper {
    
    /**
     * @param id: the id of the computer
     * @return the string of the object
     */
    public CompanyDTO getById(ResultSet resultSet) {
        Company comp = new Company();

        try {
            while (resultSet.next()) {
                comp.setId(resultSet.getInt(1));
                comp.setName(resultSet.getString(2));
            }
            return new CompanyDTO(comp);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @return the list of companies
     */
    public List<CompanyDTO> getByPage(ResultSet resultSet) {
        List<CompanyDTO> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Company comp = new Company();
                comp.setId(resultSet.getInt(1));
                comp.setName(resultSet.getString(2));
                list.add(new CompanyDTO(comp));
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
