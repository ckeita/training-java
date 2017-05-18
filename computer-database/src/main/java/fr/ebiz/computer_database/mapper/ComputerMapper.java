package fr.ebiz.computer_database.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import org.springframework.stereotype.Component;

/**
 * @author ckeita
 */
@Component
public class ComputerMapper {

    /**
     * @param computer to translate
     * @return the string of the object
     */
    public ComputerDTO getById(Computer computer) {
        return new ComputerDTO(computer);
    }

    /**
     * @param computers the list
     * @return the list of DTOs
     */
    public List<ComputerDTO> getByPage(List<Computer> computers) {
        List<ComputerDTO> list = new ArrayList<>();

        for (Computer computer : computers) {
            list.add(new ComputerDTO(computer));
        }
        return list;
    }
}
