package com.mybatis.demo.mapper;

import com.mybatis.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by louis on 2015/5/18.
 */
@Repository
public interface IUserDao {
    @Select("select * from user")
    public List<User> getAllUsers();

    @Select("select * from user where id = #{id}")
    public User getUserById(@Param("id") long id);

    @Insert("insert into user(name, age, date) values(#{name}, #{age}, current_date())")
    public int createUser(User user);

    @Update("update user set name = #{name}, age = #{age}, date = current_date() where id = #{id}")
    public int updateUser(User user);

    @Delete("delete from user where id = #{id}")
    public int deleteUser(User user);

}
