package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="vpower")
public class VPower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="power_id")
    private int id;
    @Column(name="power_name")
    private String powerName;
    @Column(name="description")
    private String description;
    @Column(name="mechanism")
    private String mechanism;


    public VPower() {
    }

    public VPower(String powerName, String description, String mechanism) {
        this.powerName = powerName;
        this.description = description;
        this.mechanism = mechanism;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    @Override
    public String toString() {
        return "VPower{" +
                "id=" + id +
                ", powerName='" + powerName + '\'' +
                ", description='" + description + '\'' +
                ", mechanism='" + mechanism + '\'' +
                '}';
    }
}
