package fr.ebiz.computer_database.validation;

import java.time.LocalDate;

import fr.ebiz.computer_database.model.Computer;

/**
 * @author ebiz
 */
public class ComputerValidation {

    /**
     * @param comp to valid
     * @return if dates are valid or not
     */
    public boolean datesValidation(Computer comp) {
        // Accept the dates are not set
        if (comp.getIntroduced().toString().length() == 0 || comp.getDiscontinued().toString().length() == 0) {
            return true;
            // Check if the dates valid if they are set
        } else if (comp.getIntroduced().toString().length() != 0 && comp.getDiscontinued().toString().length() != 0) {
            return checkDates(comp.getIntroduced(), comp.getDiscontinued());
        }
        return true;
    }

    /**
     * @param intrDate The introduced date
     * @param discDate the discontinued date
     * @return 'true' if discontinued date is greater than The introduced date
     *         and 'false' if not
     */
    private boolean checkDates(LocalDate intrDate, LocalDate discDate) {

        if (intrDate != null && discDate != null) {
            if (!intrDate.isBefore(discDate)) {
                System.out.println("The discontinued date must be greater than the introduced date");
            }
            return intrDate.isBefore(discDate);
        }
        return false;
    }
}
