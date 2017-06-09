package fr.ebiz.computer_database.model;

/**
 * @author ckeita
 */
public class CompanyDTO {
    private String id;
    private String name;

    public CompanyDTO() {
    }
    /**
     * @param comp the company to translate
     */
    public CompanyDTO(Company comp) {
        id = String.valueOf(comp.getId());
        name = comp.getName();
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

    @Override
    public String toString() {
        return "CompanyDTO [id=" + id + ", name=" + name + "]";
    }
}
