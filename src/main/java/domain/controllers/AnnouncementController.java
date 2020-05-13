package domain.controllers;

import domain.model.session.Announcement;
import domain.model.user.User;
import repository.AnnouncementDAO;
import repository.AnnouncementDAOJpa;

import java.util.List;

public class AnnouncementController {

    private AnnouncementDAO announcementDAO;
    private List<Announcement> announcements;

    public AnnouncementController() {
        announcementDAO = new AnnouncementDAOJpa();
        loadAnnouncements();
    }

    public void loadAnnouncements() {
        announcements = announcementDAO.findAll();
    }

    public Announcement getAnnouncementById(int id){
        return announcements.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }

    public void addAnnouncement(Announcement announcement) {
        announcementDAO.createAnnouncement(announcement);
    }

    public void updateAnnouncement(Announcement newValue) {
        Announcement old = getAnnouncementById(newValue.getId());
        if (old == null) {
            throw new NullPointerException("Aankondiging niet gevonden");
        }
        announcementDAO.updateAnnouncement(old, newValue);
    }
}
