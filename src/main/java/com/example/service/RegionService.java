package com.example.service;

import com.example.controller.ExeptionHandlerController;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.RegionDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.entity.RegionEntity;
import com.example.enums.LangEnum;
import com.example.exp.AppBadException;
import com.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        Optional<RegionEntity> optional = Optional.of(regionRepository.save(entity));
        if (optional.isEmpty()) {
            throw new AppBadException("error");
        }
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("not found");
        }
        RegionEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        regionRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("not found");
        }
        regionRepository.delete(id);
        return true;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> regionList = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : regionList) {
            if (entity.getVisible().equals(Boolean.TRUE)){
                dtoList.add(toDTO(entity));
            }
        }
        return dtoList;
    }

    public List<RegionDTO> getByLang(LangEnum language) {
        Iterable<RegionEntity> entityList = regionRepository.findAll();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : entityList) {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(entity.getId());
            switch (language) {
                case en:
                    regionDTO.setName(entity.getName_en());
                    break;
                case ru:
                    regionDTO.setName(entity.getName_ru());
                    break;
                default:
                    regionDTO.setName(entity.getName_uz());
            }
            if (entity.getVisible().equals(Boolean.TRUE)){
                dtoList.add(regionDTO);
            }
        }
        return dtoList;
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
