package com.yeonjooProject.selfPostProject.info.service;

import com.yeonjooProject.selfPostProject.info.dto.InfoRequestDTO;
import com.yeonjooProject.selfPostProject.info.dto.InfoResponseDTO;
import com.yeonjooProject.selfPostProject.info.entity.Info;
import com.yeonjooProject.selfPostProject.info.repository.InfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InfoService {

    private final InfoRepository infoRepository;

    // Create Info
    public InfoResponseDTO createInfo(InfoRequestDTO requestDTO) {
        Info info = Info.builder()
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .categoryId(requestDTO.getCategoryId())
                .build();

        Info savedInfo = infoRepository.save(info);
        return toResponseDTO(savedInfo);
    }

    // Update Info
    public InfoResponseDTO updateInfo(Long id, InfoRequestDTO requestDTO) {
        Info info = infoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Info not found"));

        info = Info.builder()
                .id(info.getId())
                .title(requestDTO.getTitle())
                .content(requestDTO.getContent())
                .categoryId(requestDTO.getCategoryId())
                .build();

        Info updatedInfo = infoRepository.save(info);
        return toResponseDTO(updatedInfo);
    }

    // Get Info by ID
    public InfoResponseDTO getInfo(Long id) {
        Info info = infoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Info not found"));

        return toResponseDTO(info);
    }

    // Get Info by Category ID
    public List<InfoResponseDTO> getInfoByCategoryId(Long categoryId) {
        List<Info> infos = infoRepository.findByCategoryId(categoryId);
        return infos.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Delete Info
    public void deleteInfo(Long id) {
        if (!infoRepository.existsById(id)) {
            throw new IllegalArgumentException("Info not found");
        }
        infoRepository.deleteById(id);
    }

    // Helper method to convert Info to InfoResponseDTO
    private InfoResponseDTO toResponseDTO(Info info) {
        return InfoResponseDTO.builder()
                .id(info.getId())
                .title(info.getTitle())
                .content(info.getContent())
                .categoryId(info.getCategoryId())
                .build();
    }
}
