package repository;

import domain.model.session.Location;

public class LocationDaoJpa extends GenericDaoJpa<Location> implements LocationDao {
    public LocationDaoJpa() {
        super(Location.class);
    }
}
