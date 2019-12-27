package com.uniskare.eureka_skill;

import com.uniskare.eureka_skill.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public interface TestUserRepo extends JpaRepository<User, String> {
    List<User> findAllByUniUuid(String uniUuid);
}
