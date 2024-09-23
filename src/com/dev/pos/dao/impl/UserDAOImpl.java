package com.dev.pos.dao.impl;

import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.UserDAO;
import com.dev.pos.entity.User;
import com.dev.pos.util.security.PasswordManager;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User user) throws Exception {
        String sql = "INSERT INTO user VALUES (?,?)";
        return CrudUtil.execute(sql, user.getEmail(), PasswordManager.encrypt(user.getPassword()));
    }

    @Override
    public boolean update(User user) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public User find(String email) throws Exception {
        String sql = "SELECT * FROM user WHERE email = ?";

        ResultSet resultSet = CrudUtil.execute(sql, email);

        if (resultSet.next()) {
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
        }
        return null;
    }

    @Override
    public List<User> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public List<User> search(String s) throws Exception {
        return Collections.emptyList();
    }
}
