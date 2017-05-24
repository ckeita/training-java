package fr.ebiz.computer_database.model;

/**
 * @author ebiz
 */
public class ComputerDTO {
    private String id;
    private String name;
    private String introduced;
    private String discontinued;
    private String company;
    private int companyId;

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
        if (comp.getCompanyName() != null) {
            this.company = comp.getCompanyName();
        }
        this.companyId = comp.getCompanyId();
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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "ComputerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", introduced='" + introduced + '\'' +
                ", discontinued='" + discontinued + '\'' +
                ", company='" + company + '\'' +
                ", companyId=" + companyId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComputerDTO that = (ComputerDTO) o;

        if (getCompanyId() != that.getCompanyId()) return false;
        if (!getId().equals(that.getId())) return false;
        if (!getName().equals(that.getName())) return false;
        if (getIntroduced() != null ? !getIntroduced().equals(that.getIntroduced()) : that.getIntroduced() != null)
            return false;
        if (getDiscontinued() != null ? !getDiscontinued().equals(that.getDiscontinued()) : that.getDiscontinued() != null)
            return false;
        return getCompany() != null ? getCompany().equals(that.getCompany()) : that.getCompany() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getIntroduced() != null ? getIntroduced().hashCode() : 0);
        result = 31 * result + (getDiscontinued() != null ? getDiscontinued().hashCode() : 0);
        result = 31 * result + (getCompany() != null ? getCompany().hashCode() : 0);
        result = 31 * result + getCompanyId();
        return result;
    }
}
