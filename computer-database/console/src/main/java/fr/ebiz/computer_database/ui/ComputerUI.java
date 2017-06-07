package fr.ebiz.computer_database.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.ebiz.computer_database.mapper.CompanyMapper;
import fr.ebiz.computer_database.model.Company;
import fr.ebiz.computer_database.util.Page;
import fr.ebiz.computer_database.util.Util;
import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.ComputerDTO;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
/**
 * @author ebiz
 */
@Component
public class ComputerUI {
    private int id;
    private String name;
    private String introduced;
    private String discontinued;
    private Company company;

    private static HttpAuthenticationFeature httpAuthenticationFeature = HttpAuthenticationFeature.basic("ckeita", "pass");
    private static Client client = ClientBuilder.newClient();

    /**
     * @throws DAOException .
     */
    public void viewComputer() throws DAOException {
        List<ComputerDTO> list = new ArrayList<>();
        // To check instance of list elements
        boolean finish = false;
        String choice;
        Scanner input = new Scanner(System.in);
        while (!finish) {
            System.out.println("***Choose the number of elements by page***");
            choice = input.next();
            try {
                // Need at least one element by page
                if (Integer.parseInt(choice) > 0) {
                    // process the paging
                    new Page().paging(Integer.parseInt(choice), true);
                    finish = true;
                } else {
                    System.out.println("Please choose a valid number");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Please choose a valid number");
            }
        }
    }

    /**
     * @param update or not
     * @throws DAOException .
     */
    public void createOrUpdateComputer(boolean update) throws DAOException {
        client.register(httpAuthenticationFeature);
        Scanner input = new Scanner(System.in);
        boolean checkDates = false, intrValid = false, discValid = false;
        String name, intrDate = null, discDate = null, companyId, computerId = null;

        // Choose if we create or update
        if (update) {
            System.out.println("***Update Computer***");
            do {
                System.out.println("Choose The id of the computer to update");
                computerId = input.nextLine();
            } while (computerId.length() == 0);
            this.id = Integer.parseInt(computerId);
        } else {
            System.out.println("***Create new Computer***");
        }

        // Make sure that the name is set because it's mandatory
        do {
            System.out.println("Set name");
            name = input.nextLine();
        } while (!update && name.length() == 0);
        this.name = name;

        // Handle the dates properly
        while (!checkDates) {

            /**
             * Check if the introduced date is well set and formated
             */
            while (!intrValid) {
                System.out.println("Set introduced date");
                intrDate = input.nextLine();
                // introduced date is set
                if (intrDate.length() != 0) {
                    // introduced date is valid
                    if (isValidDate(intrDate)) {
                        this.introduced = intrDate;
                        intrValid = true;
                    } else {
                        // introduced date is not valid
                        intrValid = false;
                    }
                } else {
                    // introduced date is not set
                    intrValid = true;
                }
            }
            /**
             * Check if the discontinued date is well set and formated
             */
            while (!discValid) {
                System.out.println("Set discontinued date");
                discDate = input.nextLine();
                if (discDate.length() != 0) {
                    // discontinued date is set
                    if (isValidDate(discDate)) {
                        // discontinued date is valid
                        this.discontinued = discDate;
                        discValid = true;
                    } else {
                        // discontinued date is not valid
                        discValid = false;
                    }
                } else {
                    // discontinued date is not set
                    discValid = true;
                }
            }

            if (intrDate.length() == 0 || discDate.length() == 0) {
                // Accept the dates are not set
                checkDates = true;
            } else if (intrDate.length() != 0 && discDate.length() != 0) {
                // Check if the dates valid if they are set
                checkDates = checkDates(LocalDate.parse(introduced, Util.TO_FORMATTER),
                        LocalDate.parse(discontinued, Util.TO_FORMATTER));
                if (!checkDates) {
                    // Do not Accept the dates if they are not valid
                    intrValid = false;
                    discValid = false;
                }
            }
        }
        System.out.println("Set company ID");
        companyId = input.nextLine();
        if (companyId.length() != 0) {
            company = client.target(Util.REST_URI)
                    .path(Util.ONE_COMPANY + companyId)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Company.class);
            System.out.println(company);
        }

        // Process Update or Create
        ComputerDTO computerDTO = new ComputerDTO();
        if (update) {
            computerDTO.setId(computerId);
            setDTO(computerDTO);
            client.target(Util.REST_URI)
                    .path(Util.COMPUTER_EDIT)
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.json(computerDTO));
        } else {
            setDTO(computerDTO);
            client.target(Util.REST_URI)
                    .path(Util.COMPUTER_ADD)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(computerDTO));
        }
    }

    /**
     * Delete a computer.
     */
    public void deleteComputer() {
        client.register(httpAuthenticationFeature);
        Scanner input = new Scanner(System.in);
        String computId;

        System.out.println("***Delete Computer***");

        do {
            System.out.println("Choose The id of the computer to delete");
            computId = input.nextLine();
        } while (computId.length() == 0);
        this.id = Integer.parseInt(computId);
        client.target(Util.REST_URI)
                .path(Util.ONE_COMPUTER + id)
                .request(MediaType.APPLICATION_JSON)
                .delete();
    }

    /**
     * @throws NumberFormatException .
     * @throws DAOException .
     */
    public void showComputerDetails() throws NumberFormatException, DAOException {
        client.register(httpAuthenticationFeature);
        String computId;
        Scanner input = new Scanner(System.in);

        System.out.println("***Show Computer Details***");

        do {
            System.out.println("Choose The id of the computer to show");
            computId = input.nextLine();
        } while (computId.length() == 0);

        System.out.println(client.target(Util.REST_URI)
                .path(Util.ONE_COMPUTER + computId)
                .request(MediaType.APPLICATION_JSON)
                .get(ComputerDTO.class));
        System.out.println();
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

    /**
     * @param input the string of the date to check the format
     * @return 'true' if it's well formatted and 'false' if not
     */
    private boolean isValidDate(String input) {
        SimpleDateFormat format = new SimpleDateFormat(Util.IN_FORMAT);
        try {
            format.parse(input);
            return true;
        } catch (ParseException e) {
            System.out.println("Set a valid date: the format is " + Util.IN_FORMAT);
            return false;
        }
    }

    /**
     * @param computerDTO to set
     */
    private void setDTO(ComputerDTO computerDTO) {
        computerDTO.setName(name);
        computerDTO.setIntroduced(introduced);
        computerDTO.setDiscontinued(discontinued);
        if (company != null) {
            computerDTO.setCompanyDTO(new CompanyMapper().mapToDTO(company));
        }
    }
}
