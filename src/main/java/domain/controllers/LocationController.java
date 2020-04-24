package domain.controllers;

import domain.model.session.Location;
import repository.LocationDao;
import repository.LocationDaoJpa;

import java.util.List;

public class LocationController {
    private LocationDao locationDao;
    private List<Location> locations;

    public LocationController() {
        locationDao = new LocationDaoJpa();
        loadLocations();
    }

    public Location getLocationById(String id){
        return locations.stream().filter(p -> p.getLocationId().equals(id)).findFirst().orElse(null);
    }
    private void loadLocations() {
        locations = locationDao.findAll();
    }
}
