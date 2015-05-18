package com.mybatis.demo.service;

import com.mybatis.demo.entity.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void testGetEmailById() throws Exception {
        Email email = emailService.getEmailById(1);
        assertNotNull(email);
    }

    @Test
    public void testGetAllEmails() throws Exception {
        List<Email> list = emailService.getAllEmails();
        assertNotNull(list);

    }
}