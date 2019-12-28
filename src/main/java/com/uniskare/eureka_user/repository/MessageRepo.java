package com.uniskare.eureka_user.repository;

import com.uniskare.eureka_user.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message,Integer> {
}
