package com.project.loteria.dtos;

import java.util.List;

public record BetDTO(Long id, String code, List<Integer> bet, Integer quantityNumbers) {
}
