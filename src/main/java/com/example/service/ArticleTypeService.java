package com.example.service;

import com.example.dto.ArticleTypeDTO;
import com.example.entity.ArticleTypeEntity;
import com.example.exp.AppBadException;
import com.example.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_en(dto.getName_en());
        entity.setName_ru(dto.getName_ru());
        entity.setName_uz(dto.getName_uz());

       Optional<ArticleTypeEntity> optional= Optional.of(articleTypeRepository.save(entity));
         if (optional.isEmpty()){
             throw new AppBadException("error");
         }
         dto.setId(entity.getId());
         dto.setCreatedDate(entity.getCreatedDate());
         dto.setVisible(entity.getVisible());
         return dto;
    }

    public Boolean update(Integer id, ArticleTypeDTO dto) {
        Optional<ArticleTypeEntity> optional=articleTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        ArticleTypeEntity entity=optional.get();
        entity.setOrderNumber(dto.getOrder_number());
        entity.setName_uz(dto.getName_uz());
        entity.setName_ru(dto.getName_ru());
        entity.setName_en(dto.getName_en());
        articleTypeRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
     Optional<ArticleTypeEntity>optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("not found");
        }
        articleTypeRepository.deleteById(id);
        return true;
    }

    public PageImpl getAllByPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ArticleTypeEntity> studentPage = articleTypeRepository.findAll(paging);

        List<ArticleTypeEntity> entityList = studentPage.getContent();
        Long totalElements = studentPage.getTotalElements();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity articleType : entityList) {
            dtoList.add(toDTO(articleType));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public Optional<ArticleTypeDTO> getByLanguage (Integer id, String language) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppBadException("language not found");
        }
        ArticleTypeEntity articleType = optional.get();
        ArticleTypeDTO articleTypeDTO = new ArticleTypeDTO();
        articleTypeDTO.setId(articleType.getId());

        switch (language) {
            case "uz":
                articleTypeDTO.setName_uz(articleType.getName_uz());
                break;
            case "ru":
                articleTypeDTO.setName_ru(articleType.getName_ru());
                break;
            case "en":
                articleTypeDTO.setName_en(articleType.getName_en());
                break;
            default:
                throw new AppBadException("Invalid language: " + language);
        }
        return Optional.of(articleTypeDTO);
    }
    public ArticleTypeDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
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
