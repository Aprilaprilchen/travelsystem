package com.ascending.training.april.repository;

import com.ascending.training.april.model.Role;
public interface RoleDao {
    Role getRoleByName(String name);
}