package com.uniskare.eureka_skill.repository;

import com.uniskare.eureka_skill.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public interface ConversationRepo extends JpaRepository<Conversation, Integer> {
    Conversation findByUserIdAndOtherId(String user_id, String other_id);
    List<Conversation> findByUserId(String id);

    List<Conversation> findByUserIdOrOtherId(String userId, String otherId);

//    List<Conversation> findByOtherId(String otherId);
}
