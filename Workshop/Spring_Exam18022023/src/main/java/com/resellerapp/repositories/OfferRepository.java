package com.resellerapp.repositories;

import com.resellerapp.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByUserIdNot(Long id);

    List<Offer> findAllByBuyersIdIsNullAndUserIdNot(Long id);
}
