package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="research")
public class Research {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="research_id")
    private int id;
    @Column(name="research_name")
    private String researchName;
    @Column(name="description")
    private String description;


    public Research() {

    }

    public Research(String researchName, String description) {
        this.researchName = researchName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResearchName() {
        return researchName;
    }

    public void setResearchName(String researchName) {
        this.researchName = researchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Research{" +
                "id=" + id +
                ", researchName='" + researchName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
