package com.example.pathfinder.services.impl;

import com.example.pathfinder.models.entities.Route;
import com.example.pathfinder.repositories.RouteRepository;
import com.example.pathfinder.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Route> getMostCommented() {
        return routeRepository.findMostCommented();
    }
}
