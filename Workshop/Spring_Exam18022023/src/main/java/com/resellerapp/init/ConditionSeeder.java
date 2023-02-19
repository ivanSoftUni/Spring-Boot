package com.resellerapp.init;

import com.resellerapp.models.entities.Condition;
import com.resellerapp.models.enums.ConditionType;
import com.resellerapp.repositories.ConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConditionSeeder implements CommandLineRunner {
    private ConditionRepository conditionRepository;

    @Autowired
    public ConditionSeeder(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (conditionRepository.count() == 0) {

            List<Condition> conditions = Arrays.stream(ConditionType.values())
                    .map(Condition::new).toList();
            for (Condition condition : conditions) {
                switch (condition.getName()) {

                    case GOOD -> condition.setDescription("Some signs of wear and tear or minor defects");
                    case EXCELLENT -> condition.setDescription("In perfect condition");
                    case ACCEPTABLE ->
                            condition.setDescription("The item is fairly worn but continues to function properly");
                }
            }
            this.conditionRepository.saveAll(conditions);

        }


    }
}
