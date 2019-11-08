package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="gmc")
public class Gmc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GMC_id")
    private int id;
    // M-to-1 unidirectional (we won't be backtracking from a GMC to a persona).
    // we don't use the persona_id column here because it's set up in the Persona's 1-to-M unidirectional join
    //    @Column(name="persona_id")
    //    private int personaId;
    @Column(name="GMC_type")
    private String gmcType;
    @Column(name="goal")
    private String goal;
    @Column(name="motivation")
    private String motivation;
    @Column(name="conflict")
    private String conflict;


    public Gmc() {
    }

    public Gmc(String gmcType, String goal, String motivation, String conflict) {
        this.gmcType = gmcType;
        this.goal = goal;
        this.motivation = motivation;
        this.conflict = conflict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGmcType() {
        return gmcType;
    }

    public void setGmcType(String gmcType) {
        this.gmcType = gmcType;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getConflict() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict = conflict;
    }

    @Override
    public String toString() {
        return "Gmc{" +
                "id=" + id +
                ", gmcType='" + gmcType + '\'' +
                ", goal='" + goal + '\'' +
                ", motivation='" + motivation + '\'' +
                ", conflict='" + conflict + '\'' +
                '}';
    }

}
