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

	private int id;
    private String name;
    private LocalDateTime introduced;
    private LocalDateTime discontinued;
    private int company_id;
    /**
     * @param id: the id of the computer
     * @return the string of the object
     */
    public ComputerDTO getById(ResultSet resultSet) {
    	
        try {
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                if (resultSet.getString(3) != null) {
                    introduced = LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER);
                }
                if (resultSet.getString(4) != null) {
                    discontinued = LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER);
                }
                company_id = resultSet.getInt(5);
                
                return new ComputerDTO(new Computer.ComputerBuilder(name)
						.introduced(LocalDateTime.parse(introduced+Util.STR_HOUR, Util.IN_FORMATTER))
						.discontinued(LocalDateTime.parse(discontinued+Util.STR_HOUR, Util.IN_FORMATTER))
						.company_id(company_id)
						.build());
            }
        } catch (SQLException e) {
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
                // initialize the computer fields by data from database
                id = resultSet.getInt(1);
                name = resultSet.getString(2);
                if (resultSet.getString(3) != null) {
                    introduced = LocalDateTime.parse(resultSet.getString(3), Util.FORMATTER);
                }
                if (resultSet.getString(4) != null) {
                    discontinued = LocalDateTime.parse(resultSet.getString(4), Util.FORMATTER);
                }
                company_id = resultSet.getInt(5);

                // Add new computer to the list
                list.add(new ComputerDTO(new Computer.ComputerBuilder(name)
						.introduced(LocalDateTime.parse(introduced+Util.STR_HOUR, Util.IN_FORMATTER))
						.discontinued(LocalDateTime.parse(discontinued+Util.STR_HOUR, Util.IN_FORMATTER))
						.company_id(company_id)
						.build()));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
