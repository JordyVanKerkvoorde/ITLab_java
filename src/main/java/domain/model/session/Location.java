package domain.model.session;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Locations")
public class Location implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private String locationId;
    @Column(name="Campus")
    private CampusEnum campus;
    @Column(name="Capacity")
    private int Capacity;

    public Location(String locationId, CampusEnum campus, int capacity) {
        this.locationId = locationId;
        this.campus = campus;
        Capacity = capacity;
    }

    public Location() {
    }

    public String getLocationId() {
        return locationId;
    }

    public CampusEnum getCampus() {
        return campus;
    }

    public void setCampus(CampusEnum campus) {
        this.campus = campus;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }
}
