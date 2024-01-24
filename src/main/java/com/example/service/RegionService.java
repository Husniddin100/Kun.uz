package com.example.service;

import com.example.controller.ExeptionHandlerController;
import com.example.dto.ArticleTypeDTO;
import com.example.dto.RegionDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.entity.RegionEntity;
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
        Optional<RegionEntity> optional= Optional.of(regionRepository.save(entity));
        if (optional.isEmpty()){
            throw new AppBadException("error");
        }
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public Boolean update(Integer id, RegionDTO dto) {
        Optional<RegionEntity>optional=regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        RegionEntity entity=optional.get();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        regionRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<RegionEntity>optional=regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        regionRepository.deleteById(id);
        return true;
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> regionList=regionRepository.findAll();
        List<RegionDTO>dtoList=new LinkedList<>();
        for (RegionEntity entity:regionList){
           dtoList.add(toDTO(entity));
        }
        return dtoList;
    }
    public Optional<RegionDTO> getByLang(Integer id, String language) {
        Optional<RegionEntity> optional = regionRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        RegionEntity regionEntity = optional.get();
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(regionEntity.getId());

        switch (language) {
            case "uz":
                regionDTO.setName_uz(regionEntity.getName_uz());
                break;
            case "ru":
                regionDTO.setName_ru(regionEntity.getName_ru());
                break;
            case "en":
                regionDTO.setName_en(regionEntity.getName_en());
                break;
            default:
                throw new AppBadException("Invalid language: "+language);
        }
        return Optional.of(regionDTO);
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setName_uz(entity.getName_uz());
        dto.setName_ru(entity.getName_ru());
        dto.setName_en(entity.getName_en());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
