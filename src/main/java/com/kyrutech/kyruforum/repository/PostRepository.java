package com.kyrutech.kyruforum.repository;

import com.kyrutech.kyruforum.entities.Discussion;
import com.kyrutech.kyruforum.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Page<Post> findByDiscussion(Discussion discussion, Pageable pageable);
    List<Post> findByDiscussion(Discussion discussion);
    Integer countByDiscussion(Discussion discussion);
}
