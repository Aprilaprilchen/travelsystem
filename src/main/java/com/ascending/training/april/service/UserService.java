package com.ascending.training.april.service;

import com.ascending.training.april.model.User;
import com.ascending.training.april.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired private UserDao userDao;
    public boolean save(User user) {
        return userDao.save(user);
    }
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
    public User getUserByCredentials(String email, String password) {
        return userDao.getUserByCredentials(email, password);
    }
}