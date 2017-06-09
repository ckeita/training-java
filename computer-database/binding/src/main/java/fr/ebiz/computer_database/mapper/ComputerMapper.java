package fr.ebiz.computer_database.mapper;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.exception.ServiceException;
import fr.ebiz.computer_database.model.Computer;
import fr.ebiz.computer_database.model.ComputerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ckeita
 */
@Component
public class ComputerMapper {

    @Autowired
    private CompanyMapper companyMapper;

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
    public List<ComputerDTO> getAll(List<Computer> computers) {
        List<ComputerDTO> list = new ArrayList<>();

        for (Computer computer : computers) {
            list.add(new ComputerDTO(computer));
        }
        return list;
    }

    /**
     * @param computerDTO the list
     * @return the mapped object
     */
    public Computer mapToObject(ComputerDTO computerDTO) {
        int compId = 0;
        if (computerDTO.getId() != null){
            compId = Integer.parseInt(computerDTO.getId());
        }
        try {
            if (computerDTO.getCompanyDTO() != null) {
                return new Computer.ComputerBuilder(computerDTO.getName()).id(compId).introduced(computerDTO.getIntroduced())
                        .discontinued(computerDTO.getDiscontinued()).company(companyMapper.mapToObject(computerDTO.getCompanyDTO())).build();
            } else {
               return new Computer.ComputerBuilder(computerDTO.getName()).introduced(computerDTO.getIntroduced())
                        .discontinued(computerDTO.getDiscontinued()).company(null).build();
            }
        } catch (ServiceException | DateException | NumberFormatException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
