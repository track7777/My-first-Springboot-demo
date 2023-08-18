package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    public RedisTemplate redisTemplate;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public User login(int ID){
        User user = findUser(ID);
        return user;
    }

    public User findUser(int ID){
        String key = Integer.toString(ID);
        boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        User user;
        if(hasKey){
            user = operations.get(key);
            if(user != null){
                System.out.println("从缓存中获取数据：ID = "+ user.getID());
                System.out.println("-----------------------------------------");
            }
        }
        else{
            user = userDao.findUser(ID);
            if(user != null){
                System.out.println("从数据库中获取数据：ID = "+ user.getID());
                Random rand = new Random(); //缓存时间使用随机数，防止缓存雪崩
                operations.set(key,user,rand.nextInt(5)+1, TimeUnit.HOURS);
                System.out.println("写入缓存");
                System.out.println("-----------------------------------------");
            }
        }
        return user;
    }

    public int deleteUser(int ID){
        int result = userDao.deleteUser(ID);
        if(result != 0){
            String key = Integer.toString(ID);
            boolean hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                redisTemplate.delete(key);
                System.out.println("从缓存中删除数据：ID = " + key);
                System.out.println("-----------------------------------------");
            }
        }
        return result;
    }

    public int updateUser(User user){
        int result = userDao.updateUser(user);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        if(result != 0){
            String key = Integer.toString(user.getID());
            boolean hasKey = redisTemplate.hasKey(key);
            if(hasKey){
                redisTemplate.delete(key);
            }
            User newUser = userDao.findUser(user.getID());
            if(newUser != null){
                Random rand = new Random();
                operations.set(key,newUser,rand.nextInt(5) + 1,TimeUnit.HOURS);
                System.out.println("在缓存中更新数据：ID = " + key);
                System.out.println("-----------------------------------------");
            }
        }
        return result;
    }
}
