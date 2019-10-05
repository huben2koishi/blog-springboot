package com.huben.blog.service;

import com.huben.blog.dao.UserRepository;
import com.huben.blog.entity.User;
import com.huben.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author koishi
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, MD5Utils.MD5(password));
    }
}
