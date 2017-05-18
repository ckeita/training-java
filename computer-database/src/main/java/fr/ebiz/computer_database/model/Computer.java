package fr.ebiz.computer_database.model;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.validation.ComputerValidation;

/**
 * @author ckeita
 */
public class Computer {

    private int id;
    private final String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private int companyId;
    private String companyName;

    private ComputerValidation computerValidation = new ComputerValidation();
    
    private static Logger LOGGER = LoggerFactory.getLogger(Computer.class);

    /**
     * @param computerBuilder to set
     * @throws DateException .
     */
    private Computer(ComputerBuilder computerBuilder) throws DateException {
        this.id = computerBuilder.id;
        this.name = computerBuilder.name;
        computerValidation.checkName(this.name);
        this.introduced = computerValidation.dateValidation(computerBuilder.introduced);
        this.discontinued = computerValidation.dateValidation(computerBuilder.discontinued);
        //LOGGER.info("intro "+this.introduced+" disc "+this.discontinued);
        computerValidation.checkDates(this.introduced, this.discontinued);
        this.companyId = computerBuilder.companyId;
        this.companyName = computerBuilder.companyName;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the introduced
     */
    public LocalDate getIntroduced() {
        return introduced;
    }

    /**
     * @return the discontinued
     */
    public LocalDate getDiscontinued() {
        return discontinued;
    }

    /**
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @return companyId
     */
    public int getCompanyId() {
        return companyId;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", companyName=" + companyName + "]";
    }

    public static class ComputerBuilder {
        private int id;
        private String name;
        private String introduced;
        private String discontinued;
        private int companyId;
        private String companyName;

        /**
         * the default constructor.
         */
        public ComputerBuilder() {
        }

        /**
         * @param name to set
         */
        public ComputerBuilder(String name) {
            this.name = name;
        }

        /**
         * @param introduced to set
         * @return the builder
         */
        public ComputerBuilder introduced(String introduced) {
            this.introduced = introduced;
            return this;
        }

        /**
         * @param discontinued to set
         * @return the builder
         */
        public ComputerBuilder discontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        /**
         * @param companyName to set
         * @return the builder
         */
        public ComputerBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        /**
         * @param companyId to set
         * @return the builder
         */
        public ComputerBuilder companyId(int companyId) {
            this.companyId = companyId;
            return this;
        }

        /**
         * @param id to set
         * @return the builder
         */
        public ComputerBuilder id(int id) {
            this.id = id;
            return this;
        }

        /**
         * @return the new build computer
         * @throws DateException .
         */
        public Computer build() throws DateException {
            return new Computer(this);
        }
    }
}
