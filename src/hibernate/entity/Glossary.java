package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="glossary")
public class Glossary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="glossary_id")
    private int id;
    @Column(name="glossary_name")
    private String glossaryName;
    @Column(name="description")
    private String description;


    public Glossary() {
    }

    public Glossary(String glossaryName, String description) {
        this.glossaryName = glossaryName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGlossaryName() {
        return glossaryName;
    }

    public void setGlossaryName(String glossaryName) {
        this.glossaryName = glossaryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Glossary{" +
                "id=" + id +
                ", glossaryName='" + glossaryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
