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
    private int i;
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
     * @return companyId
     */
    public int getcompanyId() {
        return companyId;
    }

    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", companyId=" + companyId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + companyId;
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + i;
        result = prime * result + id;
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Computer other = (Computer) obj;
        if (companyId != other.companyId) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (i != other.i) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (introduced == null) {
            if (other.introduced != null) {
                return false;
            }
        } else if (!introduced.equals(other.introduced)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    /**
     * @param companyName to add to string object
     * @return the string of the computer
     */
    public String toString(String companyName) {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", companyId=" + companyId + ", companyName=" + companyName + "]";
    }

    public static class ComputerBuilder {
        private int id;
        private String name;
        private String introduced;
        private String discontinued;
        private int companyId;

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
