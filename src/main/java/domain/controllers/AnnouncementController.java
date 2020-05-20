package domain.controllers;

import domain.model.session.Announcement;
import repository.AnnouncementDAO;
import repository.AnnouncementDAOJpa;

import java.util.List;

public class AnnouncementController {

    private AnnouncementDAO announcementDAO;
    private List<Announcement> announcements;

    private static final AnnouncementController instance = new AnnouncementController();

    private AnnouncementController() {
        announcementDAO = new AnnouncementDAOJpa();
        loadAnnouncements();
    }

    public static AnnouncementController getInstance() {
        return instance;
    }

    public void loadAnnouncements() {
        announcements = announcementDAO.findAll();
    }

    public List<Announcement> getAnnouncements() {
        return this.announcements;
    }

    public Announcement getAnnouncementById(int id){
        return announcements.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public void addAnnouncement(Announcement announcement) {
        announcementDAO.createAnnouncement(announcement);
        announcements.add(announcement);
    }

    public void updateAnnouncement(Announcement newValue) {
        Announcement old = getAnnouncementById(newValue.getId());
        if (old == null) {
            throw new NullPointerException("Aankondiging niet gevonden");
        }
        announcementDAO.updateAnnouncement(newValue);
    }
}
