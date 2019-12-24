package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.UserLikeMoment;
import com.uniskare.eureka_skill.entity.UserLikeMomentPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLikeMomRepo extends JpaRepository<UserLikeMoment, UserLikeMomentPK> {
    List<UserLikeMoment> findAllByMomentId(int mom_id);
    UserLikeMoment findByUserIdAndMomentId(String stargazer, int mom_id);
}
