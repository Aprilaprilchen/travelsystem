package com.ascending.training.april.service;


import com.ascending.training.april.model.Role;
import com.ascending.training.april.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired private RoleDao roleDao;

    public Role getRoleByName(String name)
    {
        return roleDao.getRoleByName(name);
    }
}

