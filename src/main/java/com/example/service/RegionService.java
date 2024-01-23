package com.example.service;

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
        entity.setLanguage(dto.getLanguage());
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
        RegionEntity regionEntity=optional.get();
        regionEntity.setOrderNumber(dto.getOrder_number());
        regionEntity.setLanguage(dto.getLanguage());
        regionRepository.save(regionEntity);
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
    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setLanguage(entity.getLanguage());
        dto.setOrder_number(entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
