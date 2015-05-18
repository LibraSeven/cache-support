package com.mybatis.demo.mapper;

import com.mybatis.demo.entity.Email;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */
@Repository
public interface IEmailDao {

    public Email getEmailById(long id);
    
    public List<Email> getAllEmails();
}
