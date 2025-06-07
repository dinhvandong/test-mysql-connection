package org.example.services;

import org.example.daos.UserDAO;
import org.example.daos.UserDAOImpl;
import org.example.models.User;
import org.example.utils.HashUtil;

public class UserServiceImpl implements UserService {
    UserDAO userDAO = null;
    public  UserServiceImpl (UserDAO userDAO){
        this.userDAO = userDAO;
    }
    @Override
    public boolean signUp(User user) {
        // Dang ky tai khoan
        User userFound = userDAO.getUserByEmail(user.getEmail());
        if(userFound == null){
            String hashPassword = HashUtil.hashPassword
                    (user.getPassword(),"SHA-512");
            user.setPassword(hashPassword);
            userDAO.saveUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean signIn(String email, String password)
    {
        User userFound = userDAO.getUserByEmail(email);
        if(userFound == null){
            return false;
        }
        String passwordFound = userFound.getPassword();
        String hashPassword = HashUtil.hashPassword(password,"SHA-512");

        return passwordFound.equals(hashPassword);
    }
}
