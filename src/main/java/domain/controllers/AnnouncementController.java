package domain.controllers;

import domain.model.session.Announcement;
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

    public void addAnnouncement(Announcement announcement) {
        announcementDAO.createAnnouncement(announcement);
    }

    public void updateAnnouncement(Announcement oldValue, Announcement newValue) {
        announcementDAO.updateAnnouncement(oldValue, newValue);
    }
}
