package com.kyrutech.kyruforum.repository;

import com.kyrutech.kyruforum.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepository extends JpaRepository<Section, Integer> {

    List<Section> findByParentIsNull();
}
