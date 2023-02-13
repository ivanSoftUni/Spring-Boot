package com.softuni.battle_ships.repositories;

import com.softuni.battle_ships.models.entities.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findByUserId(Long ownerId);

    List<Ship> findByUserIdNot(Long ownerId);

    List<Ship> findAllByOrderByHealthAscNameDescPower();
}
