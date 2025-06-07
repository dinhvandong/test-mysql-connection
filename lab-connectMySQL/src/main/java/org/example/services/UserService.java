package org.example.services;

import org.example.models.User;

public interface UserService {
    boolean signUp(User user);
    boolean signIn(String email, String password);
}
