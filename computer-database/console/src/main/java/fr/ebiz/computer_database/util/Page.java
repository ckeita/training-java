package fr.ebiz.computer_database.util;

import java.util.List;
import java.util.Scanner;

import fr.ebiz.computer_database.exception.DAOException;
import fr.ebiz.computer_database.model.CompanyDTO;
import fr.ebiz.computer_database.model.ComputerDTO;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * @author ckeita
 */
public class Page {
    private static Logger logger = LoggerFactory.getLogger(Page.class);
    /**
     * @param instanceOfComputer the instance of the computer
     * @param numberOfRow the number of elements by one page
     * @throws DAOException .
     */
    public void paging(int numberOfRow, boolean instanceOfComputer) throws DAOException {
        int offset = 0, current = 0;
        boolean finish = false;
        List<?> list;

        HttpAuthenticationFeature httpAuthenticationFeature = HttpAuthenticationFeature.basic("ckeita", "pass");
        Client client = ClientBuilder.newClient();
        client.register(httpAuthenticationFeature);

        Scanner input = new Scanner(System.in);
        String choice;
        boolean fromNext = false;
        while (!finish) {
            System.out.println("0 => Next>> " + numberOfRow + " Elements\n1 => Back<<\n2 => Return to previous menu");
            choice = input.next();
            switch (choice) {
            case "0":
                /** List next numberOfRow Computers */
                // check instance of elements in list
                current = offset / numberOfRow;
                if (instanceOfComputer) {
                    list = client.target(Util.REST_URI)
                            .path(Util.COMPUTERS_PAGING)
                            .queryParam("current", current)
                            .queryParam("limit", numberOfRow)
                            .request(MediaType.APPLICATION_JSON)
                            .get(new GenericType<List<ComputerDTO>>(){ });
                } else {
                    list = client.target(Util.REST_URI)
                            .path(Util.COMPANIES_PAGING)
                            .queryParam("current", current)
                            .queryParam("limit", numberOfRow)
                            .request(MediaType.APPLICATION_JSON)
                            .get(new GenericType<List<CompanyDTO>>(){ });
                }
                // Set offset to next page
                offset += numberOfRow;

                if (list.size() != 0) {
                    // show the current page
                    printElements(list);
                    fromNext = true;
                } else { // the end is reached
                    System.out.println("*You reach the end*\n");
                }
                break;
            case "1":
                current = offset / numberOfRow;
                /** List previous numberOfRow Computers */
                // Set offset to previous page
                if (offset > 0) {
                    if (offset - numberOfRow >= 0) {
                        // offset -= numberOfRow;
                        if (fromNext) {
                            offset -= 2 * numberOfRow;
                            fromNext = false;
                        } else {
                            offset -= numberOfRow;
                        }
                    } else {
                        offset = 0;
                    }
                    if (instanceOfComputer) {
                        list = client.target(Util.REST_URI)
                                .path(Util.COMPUTERS_PAGING)
                                .queryParam("current", current)
                                .queryParam("limit", numberOfRow)
                                .request(MediaType.APPLICATION_JSON)
                                .get(new GenericType<List<ComputerDTO>>(){ });
                    } else {
                        list = client.target(Util.REST_URI)
                                .path(Util.COMPANIES_PAGING)
                                .queryParam("current", current)
                                .queryParam("limit", numberOfRow)
                                .request(MediaType.APPLICATION_JSON)
                                .get(new GenericType<List<CompanyDTO>>(){ });
                    }
                    // show the current page
                    printElements(list);
                } else { // the end is reached
                    System.out.println("*You reach the top*\n");
                }
                break;
            case "2":
                // Set 'finish' to go back to main menu
                finish = true;
                break;
            default:
                System.out.println("Please choose a number between 0 and 2\n");
                break;
            }
        }
    }

    /**
     * @param list of computers or companies
     */
    private void printElements(List<?> list) {

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
    }

}
