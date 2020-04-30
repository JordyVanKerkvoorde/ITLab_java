package repository;

import domain.model.session.Announcement;

public class AnnouncementDAOJpa extends GenericDaoJpa<Announcement> implements AnnouncementDAO {

    public AnnouncementDAOJpa(Class<Announcement> type) {
        super(type);
    }

    public AnnouncementDAOJpa() {
        super(Announcement.class);
    }

    @Override
    public void createAnnouncement(Announcement announcement) {

    }

    @Override
    public void updateAnnouncement(Announcement oldValue, Announcement newValue) {

    }


}
