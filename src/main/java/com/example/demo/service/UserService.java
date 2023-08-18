package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.dao.UserDao;
import com.example.demo.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    public List<User> findAll();

    public User login(int ID);

    public User findUser(int ID);

    public int deleteUser(int ID);

    public int updateUser(User user);

}
