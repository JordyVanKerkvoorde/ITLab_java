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
            em.getTransaction().begin();
            em.persist(announcement);
            em.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateAnnouncement(Announcement oldValue, Announcement newValue) {

    }


}
