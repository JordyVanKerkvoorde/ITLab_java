package domain.model.session;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Locations")
public class Location {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private String locationId;
    @Column(name = "Campus")
    private CampusEnum campus;
    @Column(name = "Capacity")
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

    public static List<String> getLocationStrings() {
        List<String> enumString = new ArrayList<>();
        for (CampusEnum e : CampusEnum.values()) {
            enumString.add(e.toString());
        }
        return enumString;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId='" + locationId + '\'' +
                ", campus=" + campus +
                ", Capacity=" + Capacity +
                '}';
    }

}
