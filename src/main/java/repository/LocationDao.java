package repository;

import domain.model.session.Location;
import domain.model.session.Session;

public interface LocationDao extends GenericDao<Location>{

    void createLocation(Location location);
    void updateLocation(Location newValue);
}
