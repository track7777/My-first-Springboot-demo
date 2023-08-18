package com.example.demo.dao;


import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {
    @Select("select * from user")
    List<User> findAll();
    @Select("select * from user where ID = #{ID}")
    User findUser(int ID);
    @Delete("delete from user where ID = #{ID}")
    int deleteUser(int ID);
    @Update("update user set password = #{password}, name =#{name}, degree = #{degree} where ID = #{ID}")
    int updateUser(User user);
}
