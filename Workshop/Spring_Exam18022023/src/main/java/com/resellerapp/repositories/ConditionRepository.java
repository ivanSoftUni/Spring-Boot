package com.resellerapp.repositories;

import com.resellerapp.models.entities.Condition;
import com.resellerapp.models.enums.ConditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    Condition findByName(ConditionType name);

}
