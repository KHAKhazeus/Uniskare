package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Moment;
import com.uniskare.eureka_skill.entity.MomentPic;
import com.uniskare.eureka_skill.entity.MomentPicPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public interface MomentPicRepo extends JpaRepository<MomentPic, MomentPicPK> {
    void deleteAllByMomentId(int mom_id);

    List<MomentPic> findAllByMomentId(int mom_id);
}
