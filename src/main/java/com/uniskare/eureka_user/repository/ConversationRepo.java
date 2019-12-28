package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepo extends JpaRepository<Conversation,Integer> {
}
