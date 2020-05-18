package domain.controllers;

import domain.model.session.Guest;
import repository.GenericDao;
import repository.GenericDaoJpa;

import java.util.List;

public class GuestController {

    private List<Guest> guests;
    private static GuestController instance = new GuestController() ;
    private GenericDao<Guest> guestDao;

    private GuestController() {
        guestDao = new GenericDaoJpa<>(Guest.class);
        loadGuests();
    }

    public GuestController getInstance() {
        return instance;
    }

    private void loadGuests() {
        guests = guestDao.findAll();
    }

    public List<Guest> getGuests() {
        return this.guests;
    }

}
