package com.mybatis.demo.service;

import com.mybatis.demo.entity.Email;
import com.mybatis.demo.mapper.IEmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private IEmailDao iEmailDao;
    @Override
    public Email getEmailById(long id) {
        return iEmailDao.getEmailById(id);
    }

    @Override
    public List<Email> getAllEmails() {
        return iEmailDao.getAllEmails();
    }
}
