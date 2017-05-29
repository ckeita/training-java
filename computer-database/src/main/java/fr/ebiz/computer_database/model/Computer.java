package fr.ebiz.computer_database.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.computer_database.exception.DateException;
import fr.ebiz.computer_database.validation.ComputerValidation;

import javax.persistence.*;

/**
 * @author ckeita
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;

    @ManyToOne(targetEntity = Company.class)
    private Company company;

    private static ComputerValidation computerValidation = new ComputerValidation();
    
    private static Logger logger = LoggerFactory.getLogger(Computer.class);

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
        this.company = computerBuilder.company;
    }

    public Computer() {
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


    public Company getCompany() {
        return company;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }


    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", introduced=" + introduced +
                ", discontinued=" + discontinued +
                ", company=" + company +
                '}';
    }

    public static class ComputerBuilder {
        private int id;
        private String name;
        private String introduced;
        private String discontinued;
        private Company company;

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
         * @param company to set
         * @return the builder
         */
        public ComputerBuilder company(Company company) {
            this.company = company;
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
