package com.example.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.demo.entity.User;
import com.example.demo.enums.ExceptionEnum;
import com.example.demo.exception.BusinessException;
import com.example.demo.response.ResultResponse;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello track";
    }

    @GetMapping("/login")
    public ResultResponse login(@RequestParam int ID){
        User user = userService.login(ID);
        if(user == null){
            throw new BusinessException(ExceptionEnum.VALIDATE_ERROR);
        }
        else{
            return ResultResponse.success(user);
        }

    }
    @GetMapping("/findAll")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/findUser")
    public ResultResponse findUser(@RequestParam int ID){
        User user = userService.findUser(ID);
        if(user == null){
            throw new BusinessException(ExceptionEnum.VALIDATE_ERROR);
        }
        else {
            return ResultResponse.success(user);
        }
    }

    @GetMapping("/deleteUser")
    public ResultResponse deleteUser(@RequestParam int ID){
        int result = userService.deleteUser(ID);
        if(result != 0){
            return ResultResponse.success("delete user with ID: " + ID);
        }
        else{
            throw new BusinessException(ExceptionEnum.VALIDATE_ERROR);
        }
    }

    @GetMapping("/updateUser")
    public ResultResponse updateUser(@RequestParam int ID){
        User user = new User();
        user.setID(ID);
        user.setName("Amy");
        user.setPassword("123456abcd");
        user.setDegree(3);
        int result = userService.updateUser(user);
        if(result != 0){
            return ResultResponse.success(user);
        }
        else{
            throw new BusinessException(ExceptionEnum.VALIDATE_ERROR);
        }
    }

}
