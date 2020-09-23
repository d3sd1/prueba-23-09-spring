package com.asaitec.rest.repository;

import com.asaitec.rest.model.Operator;
import com.asaitec.rest.model.OperatorStatus;
import com.asaitec.rest.service.Randomizer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperatorRepository {
    private Randomizer randomizer;
    private List<Operator> operators;

    public OperatorRepository(Randomizer randomizer) {
        this.randomizer = randomizer;
        this.generateInitialData();
    }

    private void generateInitialData() {
        this.operators = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            this.operators.add(new Operator(
                    this.randomizer.randomString(),
                    this.randomizer.randomOperatorStatus()
            ));
        }
    }

    public List<Operator> getOperatorsByStatus(OperatorStatus operatorStatus) {
        return this.operators.stream().
                filter(p -> p.getStatus().equals(operatorStatus))
                .collect(Collectors.toList());
    }

    public Optional<Operator> findOneById(long operatorId) {
        return this.operators.stream().
                filter(p -> p.getId() == operatorId)
                .findFirst();
    }
}
