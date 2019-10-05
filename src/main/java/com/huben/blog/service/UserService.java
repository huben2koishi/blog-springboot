package com.huben.blog.service;

import com.huben.blog.entity.User;

/**
 * @author koishi
 */
public interface UserService {

    User checkUser(String username, String password);

}
