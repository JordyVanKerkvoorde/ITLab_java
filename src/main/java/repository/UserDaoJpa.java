package repository;

import domain.model.user.User;

public class UserDaoJpa extends GenericDaoJpa<User> implements UserDao {

    public UserDaoJpa() {
        super(User.class);
    }

    @Override
    public void createUser(User user) {
        try{
            startTransaction();
            insert(user);
            commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
            closePersistency();
        }
    }

    @Override
    public void updateUser(User newvalue) {
        try{
            startTransaction();
            update(newvalue);
            commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
            closePersistency();
        }

    }

}
