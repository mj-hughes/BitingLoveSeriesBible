package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="organization_id")
    private int id;
    @Column(name="organization_name")
    private String organizationName;
    @Column(name="description")
    private String description;


    public Organization() {
    }

    public Organization(String organizationName, String description) {
        this.organizationName = organizationName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", organizationName='" + organizationName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
