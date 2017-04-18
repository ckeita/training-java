/**
 * 
 */
package fr.ebiz.computer_database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;

/**
 * @author ckeita
 */
public class ComputerMapper {

    /**
     * @param id: the id of the computer
     * @return the string of the object
     */
    public ComputerDTO getById(ResultSet resultSet) {
        Computer comp = new Computer();

        try {
            while (resultSet.next()) {
                comp.setId(resultSet.getInt(1));
                comp.setName(resultSet.getString(2));
                if (resultSet.getString(3) != null) {
                    comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER));
                }
                if (resultSet.getString(4) != null) {
                    comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER));
                }
                comp.setCompany_id(resultSet.getInt(5));
                
                return new ComputerDTO(comp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param offset: Offset of the first row
     * @param max: number of rows
     * @return
     */
    public List<ComputerDTO> getByPage(ResultSet resultSet) {
        List<ComputerDTO> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                // create a new computer
                Computer comp = new Computer();
                // initialize the computer fields by data from database
                comp.setId(resultSet.getInt(1));
                comp.setName(resultSet.getString(2));
                if (resultSet.getString(3) != null) {
                    comp.setIntroduced(LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER));
                }
                if (resultSet.getString(4) != null) {
                    comp.setDiscontinued(LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER));
                }
                comp.setCompany_id(resultSet.getInt(5));

                // Add new computer to the list
                list.add(new ComputerDTO(comp));
            }
            return list;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
