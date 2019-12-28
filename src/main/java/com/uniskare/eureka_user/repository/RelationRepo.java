package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.Relation;
import com.uniskare.eureka_user.entity.RelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RelationRepo extends JpaRepository<Relation, RelationPK> {
    List<Relation> findAllByFanId(String fanId);
    List<Relation> findAllByFollowId(String followId);
}
