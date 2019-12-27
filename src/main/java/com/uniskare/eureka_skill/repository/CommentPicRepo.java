package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.CommentPic;
import com.uniskare.eureka_skill.entity.CommentPicPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentPicRepo extends JpaRepository<CommentPic, CommentPicPK> {
}
