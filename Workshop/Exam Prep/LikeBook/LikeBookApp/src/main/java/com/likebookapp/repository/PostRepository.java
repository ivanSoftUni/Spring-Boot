package com.likebookapp.repository;

import com.likebookapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Set<Post> findALlByUserId(Long id);

    Set<Post> findAllByUserIdNot(Long id);

    Optional<Post> findById(Long id);
}
