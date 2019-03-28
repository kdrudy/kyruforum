package com.kyrutech.kyruforum.repository;

import com.kyrutech.kyruforum.entities.Discussion;
import com.kyrutech.kyruforum.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Integer> {
    List<Discussion> findBySection(Section section);
}
