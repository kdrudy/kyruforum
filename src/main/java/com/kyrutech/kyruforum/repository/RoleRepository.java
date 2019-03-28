package com.kyrutech.kyruforum.repository;

import com.kyrutech.kyruforum.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);
}
