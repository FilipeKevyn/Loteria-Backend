package com.project.loteria.service.strategy;

import com.project.loteria.interfaces.GameTypeStrategy;
import com.project.loteria.service.MathService;

public class MegaSenaStrategy implements GameTypeStrategy {
    @Override
    public double calculateValueInvested(int quantityNumbers) {
        return MathService.combination(quantityNumbers, 6) * 5;
    }
}
