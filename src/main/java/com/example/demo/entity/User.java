package com.example.demo.entity;

import java.io.Serializable;

public class User implements Serializable { //对象进行redis缓存时需要实现序列化接口
    private int ID;
    private String password;
    private String name;
    private int degree;

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return ID;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDegree(int degree){
        this.degree = degree;
    }

    public int getDegree(){
        return degree;
    }
}