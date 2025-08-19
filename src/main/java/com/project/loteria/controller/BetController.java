package com.project.loteria.controller;

import com.project.loteria.dtos.BetDTO;
import com.project.loteria.entities.Bet;
import com.project.loteria.exceptions.handler.RestErrorMensage;
import com.project.loteria.service.BetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/bet")
@Tag(name= "Bet", description = "Endpoints for managing bets")
public class BetController {
    @Autowired
    private BetService betService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{poolId}/")
    @Operation(
            summary = "Find bets by pool, return with pagination",
            tags = {"Bet"},
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
    public ResponseEntity<Page<Bet>> findByPool(@PathVariable Long poolId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Bet> bets = betService.findBetsByPool(poolId, pageable);
        return ResponseEntity.ok().body(bets);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/{poolId}")
    @Operation(
            summary = "Add new bet to pool",
            tags = {"Bet"},
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
            })
    public ResponseEntity<BetDTO> create(@PathVariable Long poolId, @RequestBody BetDTO obj){
        Bet bet = new Bet(obj);
        Integer mostRepeatNumber = betService.addBetToPool(poolId, bet);
        BetDTO betDTO = new BetDTO(bet.getBetNumbersArray(), bet.getGameType(), mostRepeatNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(betDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{betId}")
    @Operation(
            summary = "Remove a bet",
            tags = {"Bet"},
            responses = {
                @ApiResponse(description = "Not Content", responseCode = "204", content = @Content),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = {
                        @Content(
                                array = @ArraySchema(schema = @Schema(implementation = RestErrorMensage.class))
                        )
                })
            }
    )
    public ResponseEntity<Void> remove(@PathVariable UUID betId){
        betService.delete(betId);
        return ResponseEntity.noContent().build();
    }
}
