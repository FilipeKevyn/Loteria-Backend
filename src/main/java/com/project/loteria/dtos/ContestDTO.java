package com.project.loteria.dtos;

import java.util.List;

public record ContestDTO(Long id, List<Integer> drawNumbers, String codeContest){
}
