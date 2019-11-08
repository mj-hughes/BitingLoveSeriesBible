package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="custom")
public class Custom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="custom_id")
    private int id;
    @Column(name="custom_name")
    private String customName;
    @Column(name="description")
    private String description;


    public Custom() {
    }

    public Custom(String customName, String description) {
        this.customName = customName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "id=" + id +
                ", customName='" + customName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
