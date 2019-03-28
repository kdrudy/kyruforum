package com.kyrutech.kyruforum.repository;

import com.kyrutech.kyruforum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
