package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.UserLikeMoment;
import com.uniskare.eureka_user.entity.UserLikeMomentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeRepo extends JpaRepository<UserLikeMoment, UserLikeMomentPK> {
    List<UserLikeMoment> findAllByUserId(String userId);
}
