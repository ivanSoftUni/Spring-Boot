package com.softuni.battle_ships.web;

import com.softuni.battle_ships.models.dtos.CreateShipDto;
import com.softuni.battle_ships.repositories.ShipRepository;
import com.softuni.battle_ships.services.ShipService;
import com.softuni.battle_ships.sesion.LoggedUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShipController {

    private ShipService shipService;
    private final ShipRepository shipRepository;
    private LoggedUser loggedUser;

    @Autowired
    public ShipController(ShipService shipService, ShipRepository shipRepository, LoggedUser loggedUser) {
        this.shipService = shipService;
        this.shipRepository = shipRepository;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("createShipDto")
    public CreateShipDto initCreateShipDto() {
        return new CreateShipDto();
    }


    @GetMapping("/ships/add")
    public String ships() {
        if (loggedUser.getId() == null) {
            return "redirect:/";
        }
        return "ship-add";
    }

    @PostMapping("/ships/add")
    public String ships(@Valid CreateShipDto createShipDto,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !this.shipService.create(createShipDto)) {
            redirectAttributes.addFlashAttribute("createShipDto", createShipDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDto", bindingResult);

            return "redirect:/ships/add";
        }


        return "redirect:/home";
    }


}
