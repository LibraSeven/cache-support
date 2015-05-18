package com.mybatis.demo.service;

import com.mybatis.demo.entity.User;
import com.mybatis.demo.mapper.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public List<User> getAllUsers() {
        return iUserDao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return iUserDao.getUserById(id);
    }

    @Override
    public int createUser(User user) {
        return iUserDao.createUser(user);
    }

    @Override
    public int updateUser(User user) {
        return iUserDao.updateUser(user);
    }

    @Override
    public int deleteUser(User user) {
        return iUserDao.deleteUser(user);
    }
}
