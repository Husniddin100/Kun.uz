package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.dto.PaginationResultDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.entity.ProfileEntity;
import com.example.exp.AppBadException;
import com.example.repository.ProfileCustomRepository;
import com.example.repository.ProfileRepository;
import com.example.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCustomRepository profileCustomRepository;

    public ProfileDTO create(ProfileDTO dto) {
       Optional<ProfileEntity> optional= profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()){
            throw new AppBadException("Email already exist");
        }
        ProfileEntity entity=new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        profileRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public Boolean updateAdmin(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity>optional=profileRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("profile not found ");
        }
        ProfileEntity entity=optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setVisible(dto.getVisible());
        profileRepository.save(entity);
        return true;
    }
    public Boolean updateUser(Integer id, ProfileDTO dto) {
        Optional<ProfileEntity>optional=profileRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("profile not found ");
        }
        ProfileEntity entity=optional.get();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        profileRepository.save(entity);
        return true;
    }

    public PageImpl getAll(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ProfileEntity> studentPage = profileRepository.findAll(paging);

        List<ProfileEntity> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            if (entity.getVisible().equals(Boolean.TRUE)) {
                dtoList.add(toDTO(entity));
            }
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }


    public Boolean delete(Integer id) {
        Optional<ProfileEntity>optional=profileRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("profile not found");
        }
        profileRepository.deleteById(id);
        return true;
    }
    public PageImpl<ProfileDTO> filter(ProfileFilterDTO filter, int page, int size) {
        PaginationResultDTO<ProfileEntity> paginationResult=profileCustomRepository.filter(filter,page,size);

        List<ProfileDTO>dtoList=new LinkedList<>();
        for (ProfileEntity entity:paginationResult.getList()){
            dtoList.add(toDTO(entity));
        }
        Pageable paging=PageRequest.of(page-1,size);
        return new PageImpl<>(dtoList,paging,paginationResult.getTotalSize());
    }
    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setVisible(entity.getVisible());
        return dto;
    }

}
