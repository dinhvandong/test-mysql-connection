package org.example.daos;

import org.example.models.User;

import java.util.List;

public interface UserDAO {

    User getUserById(int id);
    List<User> getAllUsers();
    void saveUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
    User getUserByEmail (String email);

}
