package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="setting")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="setting_id")
    private int id;
    @Column(name="setting_name")
    private String settingName;
    @Column(name="description")
    private String description;


    public Setting() {
    }

    public Setting(String settingName, String description) {
        this.settingName = settingName;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", settingName='" + settingName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
