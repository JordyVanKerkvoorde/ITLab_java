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
        try{
            startTransaction();
//            em.persist(announcement);
            insert(announcement);
            commitTransaction();
        }catch (Exception e){
            rollbackTransaction();
            e.printStackTrace();
        } finally {
            closePersistency();
        }
    }

    @Override
    public void updateAnnouncement(Announcement newValue) {
        try {
            startTransaction();
            update(newValue);
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        } finally {
            closePersistency();
        }
    }


}
