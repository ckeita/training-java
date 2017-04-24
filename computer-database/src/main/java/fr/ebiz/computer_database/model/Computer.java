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
    private int company_id;
    private int i;

    private  Computer(ComputerBuilder computerBuilder) {
        this.id = computerBuilder.id;
        this.name = computerBuilder.name;
        
        if (computerBuilder.introduced != null) {
            //if(computerBuilder.introduced.length() != 0) {
                if (computerBuilder.introduced.contains(":")) {
                    this.introduced = LocalDate.parse(computerBuilder.introduced, Util.FROM_FORMATTER);
                } else {
                    this.introduced = LocalDate.parse(computerBuilder.introduced, Util.TO_FORMATTER);
                }
           // }
        }
        if(computerBuilder.discontinued != null) {
           // if(computerBuilder.discontinued.length() != 0) {
                if (computerBuilder.discontinued.contains(":")) {
                    this.discontinued = LocalDate.parse(computerBuilder.discontinued, Util.FROM_FORMATTER);
                } else {
                    this.discontinued = LocalDate.parse(computerBuilder.discontinued, Util.TO_FORMATTER);
                }
           // }
        }
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

    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + company_id;
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + i;
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company_id != other.company_id)
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (i != other.i)
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String toString(String company_name) {
        return "Computer [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company_id=" + company_id + ", company_name=" + company_name + "]";
    }
    
    public static class ComputerBuilder {
        private int id;
        private String name;
        private String introduced;
        private String discontinued;
        private int company_id;
        
        public ComputerBuilder () {
        }
        
        public ComputerBuilder (String name) {
            this.name = name;
        }
        
        public ComputerBuilder introduced(String introduced) {
            this.introduced = introduced;
            return this;
        }
        
        public ComputerBuilder discontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }
        
        public ComputerBuilder company_id(int id) {
            this.company_id = id;
            return this;
        }
        
        public ComputerBuilder id(int id) {
        	this.id = id;
        	return this;
        }
        
        public Computer build () {
            return new Computer(this);
        }
    }
}
