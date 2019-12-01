package hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="persona_id")
    private int id;

    @NotNull(message = "required")
    @Size(min = 1, max = 30, message = "1-30 characters")
    @Column(name="name", unique=true, nullable = false)
    private String name;

    @Column(name="age_year_zero")
    private Integer ageYearZero;

    @Size(min=0, max=10, message="0-10 characters")
    @Column(name="height")
    private String height;
    @Column(name="eye_color")
    private String eyeColor;
    @Size(min=0, max=20, message="0-20 characters")
    @Column(name="hair_color")
    private String hairColor;
    @Size(min=0, max=50, message="0-50 characters")
    @Column(name="body_type")
    private String bodyType;
    @Column(name="picture_link")
    private String pictureLink;
    @Column(name="notes")
    private String notes;

    // M-to-M
    // Cascade everything but REMOVE.
    // Fetch type lazy, which is fine. Convenience method for first add.
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="persona_book",
            joinColumns = @JoinColumn(name="persona_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> books;

    // M-to-M
    // Cascade everything but REMOVE.
    // Fetch type lazy, which is fine. Convenience method for first add.
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="persona_weapon",
            joinColumns = @JoinColumn(name="persona_id"),
            inverseJoinColumns=@JoinColumn(name="weapon_id"))
    @OrderBy("name ASC")
    private List<Weapon> weapons;

    // 1-to-M unidirectional (we won't be backtracking from a GMC to a persona).
    // Cascade ALL, including remove--when we delete a persona, all the persona's GMCs go
    // Fetch type lazy, which is fine. Convenience method for first add.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id")  // FK in GMC table
    private List<Gmc> gmcs;

    // 1-to-M unidirectional (we won't be backtracking from a backstory to a persona)
    // Cascade ALL, including remove--when we delete a persona, all the persona's backstories go
    // Fetch type lazy, which is fine. Convenience method for first add.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="persona_id") // FK in backstory table
    private List<Backstory> backstories;


    public Persona() {
    }

    public Persona(String name, Integer ageYearZero, String height, String eyeColor, String hairColor, String bodyType, String pictureLink, String notes) {
        this.name = name;
        this.ageYearZero = ageYearZero;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.bodyType = bodyType;
        this.pictureLink = pictureLink;
        this.notes = notes;
    }

    // Book convenience method to compensate for lazy load
    public void add(Book tempBook) {
        // Because of lazy loading, we might not have a list yet.
        if (books==null) {
            books = new ArrayList<>();
        }
        books.add(tempBook);
    }

    // Weapon convenience method to compensate for lazy load
    public void add(Weapon tempWeapon) {
        // Because of lazy loading, we might not have a list yet.
        if (weapons==null) {
            weapons = new ArrayList<>();
        }
        weapons.add(tempWeapon);
    }
    // GMC convenience method
    public void add(Gmc tempGmc) {
        if (gmcs==null) {
            gmcs=new ArrayList<>();
        }
        gmcs.add(tempGmc);
    }
    // Backstory convenience method
    public void add(Backstory tempBackstory) {
        if (backstories==null) {
            backstories=new ArrayList<>();
        }
        backstories.add(tempBackstory);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAgeYearZero() {
        return ageYearZero;
    }

    public void setAgeYearZero(Integer ageYearZero) {
        this.ageYearZero = ageYearZero;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImagePath() {
        if (pictureLink==null) {
            return "pictureNotFound.jpg";
        }
        return pictureLink;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ageYearZero=" + ageYearZero +
                ", height='" + height + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
