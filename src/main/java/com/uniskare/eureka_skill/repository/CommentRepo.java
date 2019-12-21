package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Bhy
 * @description ：
 */
public interface CommentRepo extends JpaRepository<Comment,Integer> {
    List<Comment> findBySkillId(int skillId);
}
