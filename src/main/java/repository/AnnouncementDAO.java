package repository;

import domain.model.session.Announcement;

public interface AnnouncementDAO extends GenericDao<Announcement> {

    void createAnnouncement(Announcement announcement);
    void updateAnnouncement(Announcement newValue);
}
