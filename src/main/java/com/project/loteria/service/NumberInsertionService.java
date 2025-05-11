package com.project.loteria.service;

import com.project.loteria.entities.Number;
import com.project.loteria.service.resolver.NumberResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberInsertionService {
    private static final Logger logger = LoggerFactory.getLogger(NumberInsertionService.class);
    private final NumberResolver resolver;

    public NumberInsertionService(NumberResolver resolver) {
        this.resolver = resolver;
    }

    public Set<Number> insertNumbers(List<Integer> numbers){
        logger.info("Iniciando inserção de {}", numbers.toString());
        Set<Number> numberSet = new HashSet<>();
        for (int num : numbers) {
            logger.debug("Processando número: {}", num);
            numberSet.add(resolver.resolve(num));
        }

        logger.info("Inserção concluída. Total inserido: {}", numbers.size());
        return numberSet;
    }
}
