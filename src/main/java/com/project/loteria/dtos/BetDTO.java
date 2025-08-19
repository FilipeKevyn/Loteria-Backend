package com.project.loteria.dtos;

import java.util.List;

public record BetDTO(List<Integer> betNumbers, String gameType, Integer mostRepeatedNumber) {
}
