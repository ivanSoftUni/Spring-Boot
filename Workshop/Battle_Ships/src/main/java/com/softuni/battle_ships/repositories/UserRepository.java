package com.softuni.battle_ships.repositories;

import com.softuni.battle_ships.models.dtos.ShipDto;
import com.softuni.battle_ships.models.entities.Ship;
import com.softuni.battle_ships.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);



}
