package com.project.loteria.controller;

import com.project.loteria.dtos.PoolDTO;
import com.project.loteria.entities.Pool;
import com.project.loteria.exceptions.handler.RestErrorMensage;
import com.project.loteria.service.PoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pool")
@Tag(name = "Pool", description = "Endpoints for managing pools")
public class PoolController {

    @Autowired
    private PoolService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/create")
    @Operation(
            summary = "Adds a new Pool",
            tags = {"Pool"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<Pool> create(@RequestBody PoolDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPool(dto));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{poolId}")
    @Operation(
            summary = "Finds a Pool",
            tags = {"Pool"},
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
    public ResponseEntity<Pool> findPoolById(@PathVariable UUID poolId) {
        return ResponseEntity.ok().body(service.findById(poolId));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    @Operation(
            summary = "Finds all Pools",
            tags = {"Pool"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            }
    )
    public ResponseEntity<List<Pool>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{poolId}")
    @Operation(
            summary= "Remove Pool",
            tags = {"Pool"},
            responses = {
                    @ApiResponse(description = "Not Content", responseCode = "204", content = @Content),
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
    public ResponseEntity<Void> remove(@PathVariable UUID poolId){
        service.deletePool(poolId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/proof")
    public ResponseEntity<?> uploadProof(@PathVariable UUID id,
                                               @RequestParam("file") MultipartFile file) {
        try {
            Pool pool = service.uploadProof(id, file);
            return ResponseEntity.ok(pool);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/proof/{filename}")
    public ResponseEntity<?> downloadProof(@PathVariable String filename) {
        try {
            InputStream inputStream = service.getProof(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Comprovante não encontrado: " + e.getMessage());
        }
    }
}
