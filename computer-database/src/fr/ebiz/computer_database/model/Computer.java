package fr.ebiz.computer_database.model;

import java.time.LocalDateTime;

/**
 * @author ckeita
 */
public class Computer {

    private int id;
    private final String name;
    private LocalDateTime introduced;
    private LocalDateTime discontinued;
    private int company_id;

    private  Computer(ComputerBuilder computerBuilder) {
        this.name = computerBuilder.name;
        this.introduced = computerBuilder.introduced;
        this.discontinued = computerBuilder.discontinued;
        this.company_id = computerBuilder.company_id;
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
    public LocalDateTime getIntroduced() {
        return introduced;
    }

    /**
     * @return the discontinued
     */
    public LocalDateTime getDiscontinued() {
        return discontinued;
    }

    /**
     * @return the company_id
     */
    public int getCompany_id() {
        return company_id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company_id=" + company_id + "]";
    }

    public String toString(String company_name) {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company_id=" + company_id + ", company_name=" + company_name + "]";
    }
    
    public static class ComputerBuilder {
        private int id;
        private String name;
        private LocalDateTime introduced;
        private LocalDateTime discontinued;
        private int company_id;
        
        public ComputerBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public ComputerBuilder introduced(LocalDateTime introduced) {
            this.introduced = introduced;
            return this;
        }
        
        public ComputerBuilder discontinued(LocalDateTime discontinued) {
            this.introduced = discontinued;
            return this;
        }
        
        public ComputerBuilder company_id(int id) {
            this.company_id = id;
            return this;
        }
        
        public Computer build () {
            return new Computer(this);
        }
    }
}
