package com.uniskare.eureka_skill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : SCH001
 * @description :
 */
public interface UserRepo extends JpaRepository<User, String> {
    User findByUniUuid(String id);
}
