package fr.ebiz.computer_database.validation;

import fr.ebiz.computer_database.model.ComputerDTO;
import fr.ebiz.computer_database.util.Util;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

/**
 * Created by ebiz on 24/05/17.
 */
@Component
public class ComputerDTOValidation implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return ComputerDTO.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ComputerDTO computerDTO = (ComputerDTO) object;
		String intrDate = computerDTO.getIntroduced();
		String discDate = computerDTO.getDiscontinued();
		LocalDate introduced;
		LocalDate discontinued;

		//Name validation
		if (computerDTO.getName() != null) {
			if (computerDTO.getName().length() == 0) {
				errors.reject(Util.NAME_ERROR);
			}
		}
		//Date validation
		if (intrDate != null && discDate != null) {
			if (intrDate.length() != 0 && discDate.length() != 0) {
				if (intrDate.contains(":")) {
					introduced = LocalDate.parse(intrDate, Util.FROM_FORMATTER);
				} else {
					introduced = LocalDate.parse(intrDate, Util.TO_FORMATTER);
				}
				if (discDate.contains(":")) {
					discontinued = LocalDate.parse(discDate, Util.FROM_FORMATTER);
				} else {
					discontinued = LocalDate.parse(discDate, Util.TO_FORMATTER);
				}
				if (!introduced.isBefore(discontinued) && !introduced.isEqual(discontinued)) {
					errors.reject(Util.GREATER_THAN);
				}
			}
		}
	}
}
