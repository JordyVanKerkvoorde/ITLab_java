package domain.controllers;

import domain.model.user.User;
import repository.UserDao;
import repository.UserDaoJpa;

import java.util.List;

public class UserController {
    private UserDao userDao;
    private List<User> users;
    public UserController() {
        this.userDao = new UserDaoJpa();
        loadUsers();
    }

    private void loadUsers() {
        // TODO: maybe optimise this
        users = userDao.findAll();

    }

    public User getUserByASPId(String id){
        return users.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
}
