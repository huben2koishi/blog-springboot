package com.huben.blog.dao;

import com.huben.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author koishi
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
}
