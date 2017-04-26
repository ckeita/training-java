package fr.ebiz.computer_database.validation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import fr.ebiz.computer_database.Utils.Util;
import fr.ebiz.computer_database.exceptions.DateException;

/**
 * @author ebiz
 */
public class ComputerValidation {

    /**
     * @param date to valid
     * @return if dates are valid or not
     * @exception DateException .
     */
    public LocalDate dateValidation(String date) throws DateException {
        if (date.length() != 0) {
            try {
                if (date.contains(":")) {
                    return LocalDate.parse(date, Util.FROM_FORMATTER);
                } else {
                    return LocalDate.parse(date, Util.TO_FORMATTER);
                }
            } catch (DateTimeParseException e) {
                throw new DateException(Util.DATE_FORMAT_EXCEPTION);
            }
        }
        return null;
    }

    /**
     * @param intrDate The introduced date
     * @param discDate the discontinued date
     * @throws DateException .
     */
    public void checkDates(LocalDate intrDate, LocalDate discDate) throws DateException {

        if (intrDate != null && discDate != null) {
            if (!intrDate.isBefore(discDate)) {
                throw new DateException(Util.GREATER_THAN);
            }
        }
    }

    /**
     * @param name to check
     */
    public void checkName(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException(Util.NAME_EXCEPTION);
        }
    }
}
