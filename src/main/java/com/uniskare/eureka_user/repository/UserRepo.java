package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : LK
 * @description :
 */


public interface UserRepo extends JpaRepository<User,String> {
    public User findByUniUuid(String uuid);
    public List<User> findAllByUniIsStu(int status);
}
