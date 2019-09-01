package com.ascending.training.april.repository;

import com.ascending.training.april.model.User;

public interface UserDao {
    boolean save(User user);
    User getUserByEmail(String email);
    User getUserByCredentials(String email, String password);
}
