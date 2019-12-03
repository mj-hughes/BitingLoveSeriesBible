package hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="weapon")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weapon_id")
    private int id;
    @Column(name="weapon_name",  unique=true, nullable = false)
    private String weaponName;
    @Column(name="description")
    private String description;
    // M-to-M
    // Cascade everything but REMOVE.
    // Fetch type lazy, which is fine. Convenience method for first add.
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="persona_weapon",
            joinColumns = @JoinColumn(name="weapon_id"),
            inverseJoinColumns=@JoinColumn(name="persona_id"))
    @OrderBy("name ASC")
    private List<Persona> personas;

    public Weapon() {
    }

    public Weapon(String weaponName, String description) {
        this.weaponName = weaponName;
        this.description = description;
    }

    // Persona convenience method to compensate for lazy load
    public void add(Persona tempPersona) {
        // Because of lazy loading, we might not have a list yet.
        if (personas==null) {
            personas = new ArrayList<>();
        }
        personas.add(tempPersona);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "id=" + id +
                ", weaponName='" + weaponName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
