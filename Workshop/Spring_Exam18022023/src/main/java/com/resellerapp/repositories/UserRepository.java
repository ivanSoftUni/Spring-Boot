package com.resellerapp.repositories;


import com.resellerapp.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameAndPassword(String username, String password);


}
