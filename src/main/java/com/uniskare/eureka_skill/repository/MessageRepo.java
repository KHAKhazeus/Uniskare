package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : SCH001
 * @description :
 */
public interface MessageRepo extends JpaRepository<Message, Integer> {
    Message findByConversationId(int id);
}
