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
    
    private String name;
    private String introducedDate;
    private String discontinuedDate;
    private String company;
    
    public ComputerDTO (Computer comp) {
        
        name = comp.getName();
        if (comp.getIntroduced() != null) {
            introducedDate = comp.getIntroduced().toString();
        }
        
        if (comp.getDiscontinued() != null) {
            discontinuedDate = comp.getDiscontinued().toString();
        }
        
        if (comp.getCompany_id() != 0) {
            CompanyService compServ = new CompanyService();
            CompanyDTO compDTO = compServ.findCompanyById(comp.getCompany_id());
            company = compDTO.getName();
        }
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
     * @return the introducedDate
     */
    public String getIntroducedDate() {
        return introducedDate;
    }

    /**
     * @param introducedDate the introducedDate to set
     */
    public void setIntroducedDate(String introducedDate) {
        this.introducedDate = introducedDate;
    }

    /**
     * @return the discontinuedDate
     */
    public String getDiscontinuedDate() {
        return discontinuedDate;
    }

    /**
     * @param discontinuedDate the discontinuedDate to set
     */
    public void setDiscontinuedDate(String discontinuedDate) {
        this.discontinuedDate = discontinuedDate;
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
        return "ComputerDTO [name=" + name + ", introducedDate=" + introducedDate + ", discontinuedDate="
                + discontinuedDate + ", company=" + company + "]";
    }
    
    
}
