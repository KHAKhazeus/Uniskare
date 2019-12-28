package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.UserPic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPicRepo extends JpaRepository<UserPic,String>{
    List<UserPic> findAllByUserId(String userId);
    void deleteAllByUserId(String userId);
}
