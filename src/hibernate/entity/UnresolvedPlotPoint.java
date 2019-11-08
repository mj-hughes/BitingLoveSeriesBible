package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name="unresolved_plot_point")
public class UnresolvedPlotPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="unresolved_plot_point_id")
    private int id;
    @Column(name="unresolved_plot_point_name")
    private String unresolvedPlotPointName;
    @Column(name="unresolved_plot_point_resolved")
    private String unresolvedPlotPointResolved;
    @Column(name="description")
    private String description;


    public UnresolvedPlotPoint() {
    }

    public UnresolvedPlotPoint(String unresolvedPlotPointName, String unresolvedPlotPointResolved, String description) {
        this.unresolvedPlotPointName = unresolvedPlotPointName;
        this.unresolvedPlotPointResolved = unresolvedPlotPointResolved;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnresolvedPlotPointName() {
        return unresolvedPlotPointName;
    }

    public void setUnresolvedPlotPointName(String unresolvedPlotPointName) {
        this.unresolvedPlotPointName = unresolvedPlotPointName;
    }

    public String getUnresolvedPlotPointResolved() {
        return unresolvedPlotPointResolved;
    }

    public void setUnresolvedPlotPointResolved(String unresolvedPlotPointResolved) {
        this.unresolvedPlotPointResolved = unresolvedPlotPointResolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "UnresolvedPlotPoint{" +
                "id=" + id +
                ", unresolvedPlotPointName='" + unresolvedPlotPointName + '\'' +
                ", unresolvedPlotPointResolved='" + unresolvedPlotPointResolved + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
