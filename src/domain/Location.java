package domain;

public class Location {

    private String locationId;
    private CampusEnum campus;
    private int Capacity;

    public Location(CampusEnum campus, int capacity) {
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
