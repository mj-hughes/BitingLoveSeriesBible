package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="persona_backstory")
public class Backstory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="backstory_id")
    private int backstoryId;
    // M-to-1 unidirectional (we won't be backtracking from a backstory to a persona).
    // we don't use the persona_id column here because it's set up in the Persona's 1-to-M unidirectional join
    //    @Column(name="persona_id")
    //    private int personaId;
    @Column(name="backstory_name")
    private String backstoryName;
    @Column(name="description")
    private String description;


    public Backstory() {
    }

    public Backstory(String backstoryName, String description) {
        this.backstoryName = backstoryName;
        this.description = description;
    }

    public int getBackstoryId() {
        return backstoryId;
    }

    public void setBackstoryId(int backstoryId) {
        this.backstoryId = backstoryId;
    }

    public String getBackstoryName() {
        return backstoryName;
    }

    public void setBackstoryName(String backstoryName) {
        this.backstoryName = backstoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Backstory{" +
                "backstoryId=" + backstoryId +
                ", backstoryName='" + backstoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
