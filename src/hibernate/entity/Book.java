package hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private int id;
    @NotNull (message="required")
    @Size(min=1, max=100, message="1-100 characters")
    @Column(name="title",  unique=true, nullable = false)
    private String title;

    @NotNull (message="required")
    @Min(value=1, message="must be greater than 0")
    @Column(name="book_year")
    private int bookYear;

    @NotNull (message="required")
    @Min(value=1, message="must be 1-12")
    @Max(value=12, message="must be 1-12")
    @Column(name="book_month")
    private int bookMonth;
    // M-to-M
    // Cascade everything but REMOVE.
    // Fetch type lazy, which is fine. Convenience method for first add.
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="persona_book",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="persona_id"))
    private List<Persona> personas;

    public Book() {
    }

    public Book(String title, int bookYear, int bookMonth) {
        this.title = title;
        this.bookYear = bookYear;
        this.bookMonth = bookMonth;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBookYear() {
        return bookYear;
    }

    public void setBookYear(int bookYear) {
        this.bookYear = bookYear;
    }

    public int getBookMonth() {
        return bookMonth;
    }

    public void setBookMonth(int bookMonth) {
        this.bookMonth = bookMonth;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public String getFormattedMonth() {
        String[] months = {"Unk","Jan","Feb","Mar","Apr","May","Jun", "Jul","Aug","Sep","Oct","Nov","Dec"};
        return months[bookMonth];
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", bookYear=" + bookYear +
                ", bookMonth=" + bookMonth +
                '}';
    }
}
