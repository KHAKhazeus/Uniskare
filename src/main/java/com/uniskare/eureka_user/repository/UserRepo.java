package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : LK
 * @description :
 */


public interface UserRepo extends JpaRepository<User,String> {
    public User findByUniUuid(String uuid);
}
