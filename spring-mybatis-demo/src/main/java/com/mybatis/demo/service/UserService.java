package com.mybatis.demo.service;

import com.mybatis.demo.entity.User;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(long id);

    public int createUser(User user);

    public int updateUser(User user);

    public int deleteUser(User user);
}
