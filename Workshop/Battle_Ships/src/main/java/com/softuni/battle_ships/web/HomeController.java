package com.softuni.battle_ships.web;

import com.softuni.battle_ships.models.dtos.ShipDto;
import com.softuni.battle_ships.models.dtos.StartBattleDto;
import com.softuni.battle_ships.services.ShipService;
import com.softuni.battle_ships.sesion.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HomeController {
    private final ShipService shipService;

    private LoggedUser loggedUser;

    @ModelAttribute("startBattleDto")
    public StartBattleDto initBattleForm() {
        return new StartBattleDto();
    }

    @Autowired
    public HomeController(ShipService shipService, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.loggedUser = loggedUser;
    }

    @GetMapping("/")
    public String loggedOutIndex(Model model) {
        return "index";
    }

    @GetMapping("/home")
    public String loggedInIndex(Model model) {
        Long loggedUserId = this.loggedUser.getId();

        List<ShipDto> ownShips = this.shipService.getShipsOwnedBy(loggedUserId);
        List<ShipDto> enemyShips = this.shipService.getShipsNotOwnedBy(loggedUserId);
        List<ShipDto> sortedShips = this.shipService.getAllSorted();

        model.addAttribute("ownShips", ownShips);
        model.addAttribute("enemyShips", enemyShips);
        model.addAttribute("sortedShips", sortedShips);

        return "home";
    }

}
