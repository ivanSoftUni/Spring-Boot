package com.resellerapp.services;

import com.resellerapp.models.dtos.AddOfferDto;
import com.resellerapp.models.entities.Condition;
import com.resellerapp.models.entities.Offer;
import com.resellerapp.models.entities.User;
import com.resellerapp.models.enums.ConditionType;
import com.resellerapp.repositories.ConditionRepository;
import com.resellerapp.repositories.OfferRepository;
import com.resellerapp.repositories.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private UserRepository userRepository;

    private ConditionRepository conditionRepository;

    private OfferRepository offerRepository;
    private LoggedUser loggedUser;
    private UserService userService;


    @Autowired
    public OfferService(UserRepository userRepository, ConditionRepository conditionRepository, OfferRepository offerRepository, LoggedUser loggedUser, UserService userService) {
        this.userRepository = userRepository;
        this.conditionRepository = conditionRepository;
        this.offerRepository = offerRepository;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    public boolean registerOffer(AddOfferDto addOfferDto) {
        User user = this.userRepository.findById(loggedUser.getId()).get();

        ConditionType conditionType = ConditionType.valueOf(addOfferDto.getCondition().toUpperCase());
        Condition condition = this.conditionRepository.findByName(conditionType);

        Offer offer = new Offer();
        offer.setDescription(addOfferDto.getDescription());
        offer.setPrice(addOfferDto.getPrice());
        offer.setCondition(condition);
        offer.setUser(user);

        this.offerRepository.saveAndFlush(offer);

        this.userService.addOfferToUser(user, offer);

        return true;
    }

    public List<Offer> getOthersUsersOffers() {

        return this.offerRepository.findAllByUserIdNot(loggedUser.getId());
    }

    public void removeOffer(Long id) {

        Offer offer = this.offerRepository.findById(id).get();
        offer.setBuyers(null);
        this.offerRepository.save(offer);
        this.offerRepository.deleteById(id);

    }

    public void buyOffer(Long id) {
        User user = this.userService.getLoginUser(loggedUser.getId());
        Optional<Offer> offer = this.offerRepository.findById(id);
        user.getBoughtOffers().add(offer.get());
        offer.get().setBuyers(user);

        this.offerRepository.saveAndFlush(offer.get());
        this.userRepository.save(user);
    }

    public List<Offer> getOfferwithOutBuyer(Long id) {
        return this.offerRepository.findAllByBuyersIdIsNullAndUserIdNot(id);
    }
}
