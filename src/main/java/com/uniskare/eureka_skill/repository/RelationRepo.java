package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Relation;
import com.uniskare.eureka_skill.entity.RelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepo extends JpaRepository<Relation, RelationPK> {
    public Relation findByFollowIdAndFanId(String followId,String fanId);
}
