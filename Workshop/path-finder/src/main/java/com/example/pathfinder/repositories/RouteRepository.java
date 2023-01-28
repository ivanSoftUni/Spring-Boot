package com.example.pathfinder.repositories;

import com.example.pathfinder.models.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r order by size(r.comments) DESC")
    List<Route> findMostCommented();

}
