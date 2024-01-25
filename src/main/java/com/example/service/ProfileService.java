package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.entity.ProfileEntity;
import com.example.exp.AppBadException;
import com.example.repository.ProfileRepository;
import com.example.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

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
}
