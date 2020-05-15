package repository;

import domain.model.session.Location;

public class LocationDaoJpa extends GenericDaoJpa<Location> implements LocationDao {
    public LocationDaoJpa() {
        super(Location.class);
    }

    @Override
    public void createLocation(Location location) {
        try{
            startTransaction();
            update(location);
            commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
            closePersistency();
        }
    }

    @Override
    public void updateLocation(Location newValue) {
        try{
            startTransaction();
            update(newValue);
            commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
            closePersistency();
        }

    }
}
