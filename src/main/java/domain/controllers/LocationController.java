package domain.controllers;

import domain.model.session.Location;
import repository.LocationDao;
import repository.LocationDaoJpa;

import java.util.List;

public class LocationController {
    private LocationDao locationDao;
    private List<Location> locations;

    private static final LocationController instance = new LocationController();

    private LocationController() {
        locationDao = new LocationDaoJpa();
        loadLocations();
    }

    public static LocationController getInstance() {
        return instance;
    }

    public Location getLocationById(String id){
        return locations.stream().filter(p -> p.getLocationId().equals(id)).findFirst().orElse(null);
    }

    public List<Location> getLocations() {
        return locations;
    }

    private void loadLocations() {
        locations = locationDao.findAll();
    }

    public void addLocation(Location location) {
        locationDao.createLocation(location);
    }

    public void updateLocation(Location updatedLocation) {
        Location old = getLocationById(updatedLocation.getLocationId());
        if (old == null) {
            throw new NullPointerException("Sessie niet gevonden.");
        }
        locationDao.updateLocation(updatedLocation);
    }
}
