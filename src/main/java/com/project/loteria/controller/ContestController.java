package com.project.loteria.controller;

import com.project.loteria.dtos.ContestDTO;
import com.project.loteria.entities.Contest;
import com.project.loteria.exceptions.handler.RestErrorMensage;
import com.project.loteria.service.ContestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/contest")
@Tag(name = "Contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    @Operation(
            summary = "Add contest to pool",
            tags = {"Contest"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {
                        @Content(
                                array = @ArraySchema(schema = @Schema(implementation = RestErrorMensage.class))
                        )
                }),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<ContestDTO> create(@PathVariable String poolId, @RequestBody ContestDTO obj){
        Contest contest = new Contest(obj);
        contestService.insertContestInPool(poolId, contest);
        ContestDTO contestDTO = new ContestDTO(contest.getCodeContest(), contest.getDrawnNumbers());
        return ResponseEntity.status(HttpStatus.CREATED).body(contestDTO);
    }
}
