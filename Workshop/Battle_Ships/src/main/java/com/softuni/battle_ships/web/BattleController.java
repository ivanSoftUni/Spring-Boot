package com.softuni.battle_ships.web;

import com.softuni.battle_ships.models.dtos.StartBattleDto;
import com.softuni.battle_ships.services.BattleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BattleController {


    private BattleService battleService;

    @Autowired
    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/battle")
    public String battle(@Valid StartBattleDto startBattleDto) {

        System.out.println(startBattleDto.getAttackerId() + " " + startBattleDto.getDefenderId());


        this.battleService.attack(startBattleDto);

        return "redirect:/home";
    }
}
