package com.softuni.battle_ships.services;

import com.softuni.battle_ships.models.dtos.CreateShipDto;
import com.softuni.battle_ships.models.entities.Category;
import com.softuni.battle_ships.models.entities.Ship;
import com.softuni.battle_ships.models.entities.User;
import com.softuni.battle_ships.models.enums.CategoryName;
import com.softuni.battle_ships.repositories.CategoryRepository;
import com.softuni.battle_ships.repositories.ShipRepository;
import com.softuni.battle_ships.repositories.UserRepository;
import com.softuni.battle_ships.sesion.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipService {

    private ShipRepository shipRepository;
    private CategoryRepository categoryRepository;
    private LoggedUser loggedUser;
    private UserRepository userRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository,
                       LoggedUser loggedUser,
                       CategoryRepository categoryRepository, UserRepository userRepository) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public boolean create(CreateShipDto createShipDto) {

        Optional<Ship> byName = this.shipRepository.findByName(createShipDto.getName());
        if (byName.isPresent()) {
            return false;
        }

        CategoryName type = switch (createShipDto.getCategory()) {

            case 0 -> CategoryName.BATTLE;
            case 1 -> CategoryName.CARGO;
            case 2 -> CategoryName.PATROL;
            default -> CategoryName.BATTLE;
        };

        Category category = this.categoryRepository.findByName(type);
        Optional<User> owner = this.userRepository.findById(this.loggedUser.getId());

        Ship ship = new Ship();
        ship.setName(createShipDto.getName());
        ship.setPower(createShipDto.getPower());
        ship.setHealth(createShipDto.getHealth());
        ship.setCreated(createShipDto.getCreated());
        ship.setCategory(category);
        ship.setUser(owner.get());


        this.shipRepository.save(ship);

        return true;
    }
}
