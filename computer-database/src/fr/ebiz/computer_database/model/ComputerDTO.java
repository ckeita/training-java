/**
 * 
 */
package fr.ebiz.computer_database.model;

import fr.ebiz.computer_database.service.CompanyService;

/**
 * @author ebiz
 *
 */
public class ComputerDTO {
    
    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String company;
    
    public ComputerDTO (Computer comp) {
        this.id = Integer.toString(comp.getId());
        this.name = comp.getName();
        if (comp.getIntroduced() != null) {
            this.introduced = comp.getIntroduced().toString();
        }
        
        if (comp.getDiscontinued() != null) {
            this.discontinued = comp.getDiscontinued().toString();
        }
        
        if (comp.getCompany_id() != 0) {
            CompanyService compServ = new CompanyService();
            CompanyDTO compDTO = compServ.findCompanyById(comp.getCompany_id());
            this.company = compDTO.getName();
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
    public String getintroduced() {
        return introduced;
    }

    /**
     * @param introduced the introduced to set
     */
    public void setintroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * @return the discontinued
     */
    public String getdiscontinued() {
        return discontinued;
    }

    /**
     * @param discontinued the discontinued to set
     */
    public void setdiscontinued(String discontinued) {
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

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ComputerDTO [name=" + name + ", introduced=" + introduced + ", discontinued="
                + discontinued + ", company=" + company + "]";
    }
    
    
}
