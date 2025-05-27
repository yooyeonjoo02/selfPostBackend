package com.yeonjooProject.selfPostProject.info.controller;

import com.yeonjooProject.selfPostProject.info.dto.InfoRequestDTO;
import com.yeonjooProject.selfPostProject.info.dto.InfoResponseDTO;
import com.yeonjooProject.selfPostProject.info.service.InfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class InfoController {

    private final InfoService infoService;

    // Create Info
    @PostMapping
    public ResponseEntity<InfoResponseDTO> createInfo(@RequestBody @Valid InfoRequestDTO requestDTO) {
        InfoResponseDTO responseDTO = infoService.createInfo(requestDTO);
        return ResponseEntity.status(201).body(responseDTO);
    }

    // Update Info
    @PutMapping("/{id}")
    public ResponseEntity<InfoResponseDTO> updateInfo(@PathVariable Long id,
                                                      @RequestBody @Valid InfoRequestDTO requestDTO) {
        InfoResponseDTO responseDTO = infoService.updateInfo(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    // Get Info by ID
    @GetMapping("/{id}")
    public ResponseEntity<InfoResponseDTO> getInfo(@PathVariable Long id) {
        InfoResponseDTO responseDTO = infoService.getInfo(id);
        return ResponseEntity.ok(responseDTO);
    }

    // Get Info by Category ID
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<InfoResponseDTO>> getInfoByCategoryId(@PathVariable Long categoryId) {
        List<InfoResponseDTO> responseDTOs = infoService.getInfoByCategoryId(categoryId);
        return ResponseEntity.ok(responseDTOs);
    }

    // Delete Info
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInfo(@PathVariable Long id) {
        infoService.deleteInfo(id);
        return ResponseEntity.noContent().build();
    }
}
