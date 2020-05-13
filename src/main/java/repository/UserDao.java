package repository;

import domain.model.session.Session;
import domain.model.user.User;

public interface UserDao extends GenericDao<User>{

    void createUser(User user);
    void updateUser(User oldvalue, User newvalue);

}
