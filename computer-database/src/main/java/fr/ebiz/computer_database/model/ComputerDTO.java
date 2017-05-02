package fr.ebiz.computer_database.model;

import fr.ebiz.computer_database.exceptions.DAOException;
import fr.ebiz.computer_database.service.CompanyService;

/**
 * @author ebiz
 */
public class ComputerDTO {
    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String company;

    /**
     * @param comp to set
     */
    public ComputerDTO(Computer comp) {
        this.id = Integer.toString(comp.getId());
        this.name = comp.getName();
        if (comp.getIntroduced() != null) {
            this.introduced = comp.getIntroduced().toString();
        }
        if (comp.getDiscontinued() != null) {
            this.discontinued = comp.getDiscontinued().toString();
        }
        if (comp.getcompanyId() != 0) {
            CompanyService compServ = new CompanyService();
            CompanyDTO compDTO;
            try {
                compDTO = compServ.findCompanyById(comp.getcompanyId());
                this.company = compDTO.getName();
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the introduced
     */
    public String getIntroduced() {
        return introduced;
    }

    /**
     * @param introduced the introduced to set
     */
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * @return the discontinued
     */
    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued the discontinued to set
     */
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "ComputerDTO [name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
                + ", company=" + company + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((company == null) ? 0 : company.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ComputerDTO other = (ComputerDTO) obj;
        if (company == null) {
            if (other.company != null) {
                return false;
            }
        } else if (!company.equals(other.company)) {
            return false;
        }
        if (discontinued == null) {
            if (other.discontinued != null) {
                return false;
            }
        } else if (!discontinued.equals(other.discontinued)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

}
