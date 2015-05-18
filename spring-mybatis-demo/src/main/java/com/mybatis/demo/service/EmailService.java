package com.mybatis.demo.service;

import com.mybatis.demo.entity.Email;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */
public interface EmailService {
    public Email getEmailById(long id);

    public List<Email> getAllEmails();
}
