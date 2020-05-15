package domain.controllers;

import domain.Exceptions.AlreadyExistsException;
import domain.model.user.User;
import repository.UserDao;
import repository.UserDaoJpa;

import java.util.List;

public class UserController {
    private UserDao userDao;
    private List<User> users;

    private static final UserController instance = new UserController();

    private UserController() {
        this.userDao = new UserDaoJpa();
        loadUsers();
    }

    public static UserController getInstance() {
        return instance;
    }

    private void loadUsers() {
        // TODO: maybe optimise this
        users = userDao.findAll();

    }

    public User getUserByASPId(String id){
        return users.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public void addUser(User user) {
        //TODO: email already used
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))){
            throw new AlreadyExistsException("Er bestaat al een gebruiker met dit e-mailadres.");
        }
        userDao.createUser(user);
        users.add(user);
    }

    public void updateUser(User updatedUser) {
        User old = getUserByASPId(updatedUser.getId());
        if (old == null) {
            throw new NullPointerException("Gebruiker niet gevonden.");
        }
        userDao.updateUser(old, updatedUser);

    }

}
