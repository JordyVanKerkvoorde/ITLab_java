package repository;

import domain.model.user.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public void createUser(User user) {
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User oldvalue, User newvalue) {

    }

}
