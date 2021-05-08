package com.cogu.shop.service.serviceImpl;

import com.cogu.shop.dao.UserMapper;
import com.cogu.shop.entity.vo.User;
import com.cogu.shop.service.UserService;
import com.cogu.shop.tools.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Cogu on 2018/6/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUid(Integer uid) {
        return userMapper.findUserByUid(uid);
    }

    @Override
    public Integer findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public Integer saveUser(User user) {
        Integer result = 0;
        try {
            user.setPassword(MD5Utils.EncoderByMd5(user.getPassword()));
            result = userMapper.saveUser(user);
        } catch (RuntimeException e1) {
            System.out.println(e1.getMessage());
        }
        return result;
    }

    @Override
    public User findUser(String username, String password) {
        String md5 = MD5Utils.EncoderByMd5(password);
        User user = userMapper.findUser(username, md5);
        if (user != null) {
            try {
                userMapper.updateUserLog(user.getUid());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        return userMapper.findUser(username, md5);
    }

    @Override
    @Transactional
    public Integer updateUser(User user) {
        Integer result = userMapper.updateUser(user);
        if (result == 1) {
            userMapper.updateUserLog(user.getUid());
        }
        return result;
    }

    @Override
    public Integer findEmailCount(String email) {
        return userMapper.findEmailCount(email);
    }

    @Override
    public Integer updatePassword(String email, String password) {
        String new_password = MD5Utils.EncoderByMd5(password);
        return userMapper.updateUserPassword(email, new_password);
    }
}
