package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Relation;
import com.uniskare.eureka_skill.entity.RelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepo extends JpaRepository<Relation, RelationPK> {
    void deleteByFollowIdAndFanId(String followId, String fanId);

    List<Relation> findByFanId(String fan_id);
    List<Relation> findByFollowId(String follow_id);
}
