package fr.ebiz.computer_database.model;

/**
 * @author ebiz
 */
public class ComputerDTO {
    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private CompanyDTO companyDTO;

    /**
     * the default controller
     */
    public ComputerDTO() {
    }
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
        if (comp.getCompany() != null) {
            this.companyDTO = new CompanyDTO(comp.getCompany());
            this.companyDTO.setName(comp.getCompany().getName());
            this.companyDTO.setId(String.valueOf(comp.getCompany().getId()));
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

    public CompanyDTO getCompanyDTO() {
        return companyDTO;
    }

    public void setCompanyDTO(CompanyDTO companyDTO) {
        this.companyDTO = companyDTO;
    }

    @Override
    public String toString() {
        return "ComputerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", introduced='" + introduced + '\'' +
                ", discontinued='" + discontinued + '\'' +
                ", companyDTO=" + companyDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerDTO that = (ComputerDTO) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (!getName().equals(that.getName())) return false;
        if (getIntroduced() != null ? !getIntroduced().equals(that.getIntroduced()) : that.getIntroduced() != null)
            return false;
        if (getDiscontinued() != null ? !getDiscontinued().equals(that.getDiscontinued()) : that.getDiscontinued() != null)
            return false;
        return getCompanyDTO() != null ? getCompanyDTO().equals(that.getCompanyDTO()) : that.getCompanyDTO() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getIntroduced() != null ? getIntroduced().hashCode() : 0);
        result = 31 * result + (getDiscontinued() != null ? getDiscontinued().hashCode() : 0);
        result = 31 * result + (getCompanyDTO() != null ? getCompanyDTO().hashCode() : 0);
        return result;
    }
}
