package com.coopezz.cpzedit.repositories;

import com.coopezz.cpzedit.models.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findPostsByUserId(Long id);
}
