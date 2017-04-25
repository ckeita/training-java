package fr.ebiz.computer_database.model;

import java.time.LocalDate;

import fr.ebiz.computer_database.Utils.Util;

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

    /**
     * @param computerBuilder to set
     */
    private Computer(ComputerBuilder computerBuilder) {
        this.id = computerBuilder.id;
        this.name = computerBuilder.name;
        if (computerBuilder.introduced != null) {
            if (computerBuilder.introduced.length() != 0) {
                if (computerBuilder.introduced.contains(":")) {
                    this.introduced = LocalDate.parse(computerBuilder.introduced, Util.FROM_FORMATTER);
                } else {
                    this.introduced = LocalDate.parse(computerBuilder.introduced, Util.TO_FORMATTER);
                }
            }
        }
        if (computerBuilder.discontinued != null) {
            if (computerBuilder.discontinued.length() != 0) {
                if (computerBuilder.discontinued.contains(":")) {
                    this.discontinued = LocalDate.parse(computerBuilder.discontinued, Util.FROM_FORMATTER);
                } else {
                    this.discontinued = LocalDate.parse(computerBuilder.discontinued, Util.TO_FORMATTER);
                }
            }
        }
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
         */
        public Computer build() {
            return new Computer(this);
        }
    }
}
